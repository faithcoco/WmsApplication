package com.lkrh.storescontrol.view.packinginspetion;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.bean.PackingInspetionBean;
import com.lkrh.storescontrol.databinding.ActivityPackingInspetionBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.url.iUrl;
import com.lkrh.storescontrol.view.BaseActivity;
import com.lkrh.storescontrol.view.ClaimMaterialActivity;
import com.lkrh.storescontrol.view.ScanActivity;
import com.lkrh.storescontrol.view.declaration.ReportActivity;
import com.lkrh.storescontrol.view.declaration.ReportprintActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PackingInspetionActivity extends BaseActivity {
    ActivityPackingInspetionBinding binding;
    int type;
    LoginBean.Menu menuBean;
    private SpeechSynthesizer mTts;
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    public static String voicerCloud="xiaoyan";
    int count=0;
    List<String> listCode=new ArrayList<>();
    Handler handler;
    Boolean isStart=false;
    String boxcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(PackingInspetionActivity.this,R.layout.activity_packing_inspetion);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);

        requestPermissions();

        mTts = SpeechSynthesizer.createSynthesizer(this, mTtsInitListener);
        if(company.equals("强田")){
            binding.etHscode.setVisibility(View.GONE);
        }
       binding.etBoxcode.setOnKeyListener(onKeyListener);
       binding.etCode.setOnKeyListener(onKeyListener);
       binding.etHscode.setOnKeyListener(onKeyListener);

       binding.ivBoxcode.setOnClickListener(onClickListener);
       binding.ivCode.setOnClickListener(onClickListener);
       binding.ivHscode.setOnClickListener(onClickListener);
       binding.bEnd.setOnClickListener(onClickListener);
       binding.bSearch.setOnClickListener(onClickListener);


        setParam();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTts!=null){
            mTts.stopSpeaking();
        }

        isStart=false;
    }


    private void setParam(){
        if(mTts==null){
            Log.i("isrun","null");
            return;
        }
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        //设置合成
        if(mEngineType.equals(SpeechConstant.TYPE_CLOUD))
        {
            //设置使用云端引擎
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME,voicerCloud);

        }
        //mTts.setParameter(SpeechConstant.TTS_DATA_NOTIFY,"1");//支持实时音频流抛出，仅在synthesizeToUri条件下支持
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE,"3");
        //	mTts.setParameter(SpeechConstant.STREAM_TYPE, AudioManager.STREAM_MUSIC+"");

        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");

        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.wav");


    }
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            //showTip("开始播放");


        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
            // 合成进度

        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度

        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {

            } else if (error != null) {

            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = obj.getString(SpeechEvent.KEY_EVENT_AUDIO_URL);

            }

            //实时音频流输出参考
			/*if (SpeechEvent.EVENT_TTS_BUFFER == eventType) {
				byte[] buf = obj.getByteArray(SpeechEvent.KEY_EVENT_TTS_BUFFER);
				Log.e("MscSpeechLog", "buf is =" + buf);
			}*/
        }
    };
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {

            if (code != ErrorCode.SUCCESS) {
                if (code != ErrorCode.SUCCESS) {


                } else {
                    // 初始化成功，之后可以调用startSpeaking方法
                    // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                    // 正确的做法是将onCreate中的startSpeaking调用移至这里
                }

            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };
    private void requestPermissions(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS},0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           switch (view.getId()){
               case R.id.iv_boxcode:
                   type=0;
                   openScan();
                   break;
               case R.id.iv_code:
                   type=1;
                   openScan();
                   break;
               case R.id.iv_hscode:
                   type=2;
                   openScan();

                   break;
               case R.id.b_end:

                   if(binding.etBoxcode.getText().toString().isEmpty()){

                       new AlertDialog.Builder(PackingInspetionActivity.this).setMessage("客户箱码不能为空")
                               .setNegativeButton("确定", null).setPositiveButton("取消", null).show();
                       mTts.startSpeaking("客户箱码不能为空", mTtsListener);
                       return;
                   }


                   updateDocVouch();
                   break;
               case R.id.b_search:
                   Intent intent=new Intent(PackingInspetionActivity.this,PackingInspetionListActivity.class);
                   intent.putExtra("menuname",getIntent().getStringExtra("menuname"));


                   intent.putExtra("listCode",listCode.toString());
                   startActivity(intent);
                   break;

           }
        }
    };
    private void openScan() {

        new IntentIntegrator(PackingInspetionActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //扫码
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                String code=result.getContents();
                Log.i("scan",code);

                switch (type){
                    case 0://客户箱码
                        binding.etBoxcode.setText(code);
                        break;
                    case 1://条码
                       binding.etCode.setText(code);
                        break;
                    case 2://hs箱码
                        binding.etHscode.setText(code);
                        break;
                }

                getBarcodeValues(code,type);


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
    PackingInspetionBean bean;
    View.OnKeyListener onKeyListener=new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (v.getId()) {
                    case R.id.et_boxcode:
                         getBarcodeValues(binding.etBoxcode.getText().toString(),0);
                        break;
                    case R.id.et_code:

                        getBarcodeValues(binding.etCode.getText().toString(),1);
                        break;
                    case R.id.et_hscode:
                        getBarcodeValues(binding.etCode.getText().toString(),2);
                        break;

                }
            }

            return false;
        }
    };

    /**
     *
     * @param code
     * @param type 0客户箱码 1条码 2 瀚氏箱码
     */
    private void getBarcodeValues(final String code, final int type) {
        if(type==1 && listCode.contains(binding.etCode.getText().toString())){

            binding.etCode.setText("");
            new AlertDialog.Builder(PackingInspetionActivity.this).setMessage("此单件已装箱！")
                    .setNegativeButton("确定", null).setPositiveButton("取消", null).show();
            mTts.startSpeaking("此单件已装箱！", mTtsListener);

            return;
        }
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            if(type==2){
                jsonObject.put("layout","2");
            }else {
                jsonObject.put("layout","1");
            }

            jsonObject.put("barcode",code);

            jsonObject.put("ccode",binding.tvCusinvcode.getText().toString());


            jsonObject.put("irowno","");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("request-->",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    bean=new Gson().fromJson(response.body().string(),PackingInspetionBean.class);
                      switch (type){
                          case 0:
                              if(bean.getResultcode().equals("200")){
                                  binding.tvCusinvcode.setText(bean.getFormdata().getCusinvcode());
                                  binding.tvPackqty.setText(bean.getFormdata().getPackqty());
                                  packqty=Integer.parseInt(bean.getFormdata().getPackqty());
                                  boxcode=code;
                              }else {
                                  new AlertDialog.Builder(PackingInspetionActivity.this).setMessage(bean.getResultMessage())
                                          .setNegativeButton("确定", null).setPositiveButton("取消", null).show();
                                  mTts.startSpeaking(bean.getResultMessage(), mTtsListener);
                                  binding.etBoxcode.setText(boxcode);

                              }

                              break;
                          case 1:
                             if(bean.getResultcode().equals("200")){
                                 if(count<packqty) {
                                     count++;
                                     listCode.add(binding.etCode.getText().toString());
                                     binding.tvCount.setText(count+"");
                                     binding.etCode.setText("");
                                     binding.etCode.requestFocus();
                                 }else {
                                     Toast.makeText(PackingInspetionActivity.this,"已满足装箱数！", Toast.LENGTH_LONG).show();
                                 }

                             }else {
                                 new AlertDialog.Builder(PackingInspetionActivity.this).setMessage(bean.getResultMessage())
                                         .setNegativeButton("确定", new AlertDialog.OnClickListener() {
                                             @Override
                                             public void onClick(DialogInterface dialogInterface, int i) {

                                             }
                                         }).setPositiveButton("取消", null).show();
                                 mTts.startSpeaking(bean.getResultMessage(), mTtsListener);

                             }
                              break;
                          case 2:
                              if(!bean.getResultcode().equals("200")){
                                  binding.etHscode.setText("");

                                  new AlertDialog.Builder(PackingInspetionActivity.this).setMessage(bean.getResultMessage())
                                          .setNegativeButton("确定", null).setPositiveButton("取消", null).show();
                                  mTts.startSpeaking(bean.getResultMessage(), mTtsListener);
                              }

                              break;
                      }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    int packqty;
    private void updateDocVouch() {
        if(listCode.size()!=packqty){
            new AlertDialog.Builder(PackingInspetionActivity.this).setMessage("已扫描数必须等于装箱数")
                    .setNegativeButton("确定", null).setPositiveButton("取消", null).show();
            mTts.startSpeaking("已扫描数必须等于装箱数", mTtsListener);

            return;
        }

        dialog.show();

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            jsonObject.put("button","结束");
            JSONObject jsonObjectCondition=new JSONObject();
            jsonObjectCondition.put("cusqrcode",binding.etBoxcode.getText().toString());
            jsonObjectCondition.put("hsqrcode",binding.etHscode.getText().toString());
            jsonObject.put("condition",new Gson().toJson(jsonObjectCondition));

            JSONArray jsonArray=new JSONArray();

            for (int i = 0; i <listCode.size() ; i++) {
                JSONObject object=new JSONObject();
                object.put("barcode",listCode.get(i));
                jsonArray.put(object);
            }
            jsonObject.put("formdata",jsonArray.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String obj=jsonObject.toString();
        Log.i("json object",obj);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Request.URL).build();
        final RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);

        iUrl login = retrofit.create(iUrl.class);
        retrofit2.Call<ResponseBody> data = login.getMessage(body);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    bean=new Gson().fromJson(response.body().string(),PackingInspetionBean.class);

                   binding.etBoxcode.setText("");
                   binding.etHscode.setText("");
                   binding.tvCusinvcode.setText("");
                   binding.tvPackqty.setText("");
                   binding.etCode.setText("");
                   binding.tvCount.setText("");
                   listCode.clear();
                   binding.etBoxcode.requestFocus();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(bean.getResultcode().equals("200")){
                    Toast.makeText(PackingInspetionActivity.this, bean.getResultMessage(), Toast.LENGTH_LONG).show();
                }else {
                    new AlertDialog.Builder(PackingInspetionActivity.this).setMessage(bean.getResultMessage())
                            .setNegativeButton("确定", null).setPositiveButton("取消", null).show();
                    mTts.startSpeaking(bean.getResultMessage(), mTtsListener);
                }


            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();

                new AlertDialog.Builder(PackingInspetionActivity.this).setMessage(t.getMessage())
                        .setNegativeButton("确定", null).setPositiveButton("取消", null).show();
                mTts.startSpeaking(t.getMessage(), mTtsListener);
            } });
    }
}
