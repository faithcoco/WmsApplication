package com.lkrh.storescontrol.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.AssemblyBean
import com.lkrh.storescontrol.bean.GxbgBean
import com.lkrh.storescontrol.bean.LoginBean
import com.lkrh.storescontrol.bean.ProcessBean
import com.lkrh.storescontrol.untils.Untils
import com.lkrh.storescontrol.untils.iToast
import com.lkrh.storescontrol.url.Request
import kotlinx.android.synthetic.main.activity_assembly.*
import kotlinx.android.synthetic.main.activity_gxbg.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.GenericSignatureFormatError

class AssemblyActivity : BaseActivity() {
    var menuBean: LoginBean.Menu? = null
    var assemblyBean:AssemblyBean?=null
    var assemblyList: ArrayList<AssemblyBean.Data>? = null
    var sharedPreferences:SharedPreferences?=null
    var tag=0;
    var ccode="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assembly)
        sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE)
        assemblyList=ArrayList()
        menuBean = intent.getParcelableExtra("menubean")
        Untils.initTitle(menuBean!!.menushowname, this)
        iv_boxcode.setOnClickListener(onClickListener)
        iv_code.setOnClickListener(onClickListener)
        b_search.setOnClickListener(onClickListener)
        b_end.setOnClickListener(onClickListener)
        et_boxcode.setOnKeyListener(View.OnKeyListener { view, i, event ->
            if (i == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                tag = 1
                getData(et_boxcode.text.toString())
                et_code_assembly.requestFocus()
            }
            if (event.action == KeyEvent.ACTION_UP) {
                true
            } else {
                false
            }
        })
        et_code_assembly.setOnKeyListener(View.OnKeyListener { view, i, event ->
            if (i == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                tag = 2
                getData(et_code_assembly.text.toString())
            }
            if (event.action == KeyEvent.ACTION_UP) {
                true
            } else {
                false
            }
        })
    }
    var onClickListener=View.OnClickListener{ view ->
        when(view.id){
            iv_boxcode.id -> {
                tag = 1
                Untils.openScan(this)
            }
           iv_code.id -> {
                tag = 2;
                Untils.openScan(this)
            }
            b_search.id -> {
                val intent = Intent(
                    this,
                    AssemblyListActivity::class.java
                )
                intent.putExtra("menubean", menuBean)
                startActivity(intent)
            }
            b_end.id -> {
               putdata(b_end.text.toString())
            }
        }
    }
    override fun onStart() {
        super.onStart()

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        //扫码
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val code = result.contents
                Log.i("scan", code)
                when(tag){
                    1 -> {
                        et_boxcode.setText(code)
                    }
                    2 -> {
                        et_code.setText(code)
                    }
                }

                getData(code)

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
    private fun getData(barcode: String) {
        val jsonObject = JSONObject()
        try {

            jsonObject.put("methodname", "getBarcodeValues")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("acccode", BaseActivity.acccode)
            jsonObject.put("menucode", menuBean!!.menucode)
            jsonObject.put("layout", tag.toString())
            jsonObject.put("barcode", barcode)
            jsonObject.put("ccode", ccode)
            jsonObject.put("irowno", "")



        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val obj = jsonObject.toString()
        Log.i("json object", obj)

        val data = Request.getRequestbody(obj)
        data.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                try {
                    if (response.code() == 200) {
                        assemblyBean = Gson().fromJson(
                            response.body()!!.string(),
                            AssemblyBean::class.java
                        )
                        ccode=assemblyBean!!.formdata.ccode
                        Log.i("bean-->", tag.toString())
                        when (tag) {
                            1 -> {
                                tv_ccode.setText(assemblyBean!!.formdata.ccode)
                                tv_cInvName.setText(assemblyBean!!.formdata.cInvName)
                            }
                            2 -> {

                                tv_component_ccode.setText(assemblyBean!!.formdata.ccode)
                                tv_component_cInvName.setText(assemblyBean!!.formdata.cInvName)
                                et_code_assembly.setText("")
                                if(assemblyList!!.contains(assemblyBean!!.formdata)){
                                    iToast.showToast(this@AssemblyActivity,"此件已经扫描", 1000)
                                }else{
                                    assemblyList!!.add(assemblyBean!!.formdata)


                                    sharedPreferences!!.edit()
                                        .putString(
                                            menuBean!!.menucode + "List",
                                            Gson().toJson(assemblyList)
                                        ).commit()
                                }

                            }
                        }
                    }else{
                        iToast.showToast(this@AssemblyActivity,assemblyBean!!.ResultMessage, 1000)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {

            }
        })
    }
    private fun putdata(tag: String) {
        val jsonObject = JSONObject()
        try {

            jsonObject.put("methodname", "updateDocVouch")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("acccode", BaseActivity.acccode)
            jsonObject.put("menucode", menuBean!!.menucode)
            jsonObject.put("layout", "1")
            jsonObject.put("button", tag)
            jsonObject.put("condition", et_boxcode.text.toString())
            jsonObject.put("formdata", Gson().toJson(assemblyList))



        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val obj = jsonObject.toString()
        Log.i("json object", obj)

        val data = Request.getRequestbody(obj)
        data.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                try {
                    if (response.code() == 200) {

                        var gxbgBean = Gson().fromJson(
                            response.body()!!.string(),
                            GxbgBean::class.java
                        )

                        if (gxbgBean!!.Resultcode == "200") {

                            } else {

                            }
                        iToast.showToast(this@AssemblyActivity, gxbgBean!!.ResultMessage, 1000)

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {

            }
        })
    }
}