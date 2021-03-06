package com.lkrh.storescontrol.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.*
import com.lkrh.storescontrol.databinding.ItemPackingInspetionBinding
import com.lkrh.storescontrol.untils.Untils
import com.lkrh.storescontrol.url.Request
import kotlinx.android.synthetic.main.activity_scan_check.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HsScancheckActivity :BaseActivity() {

    internal var scanCheckBeanList:ArrayList<ScanCheckBean>?=ArrayList()
    internal var scanList:ArrayList<String>?= ArrayList()
    private var functionAdapter: FunctionAdapter? = null
    internal var menuBean: LoginBean.Menu?=null
    var confirmlistBean: ConfirmlistBean? = null
    var gxbgBean:GxbgBean?=null
    var tag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_check)

        menuBean = intent.getParcelableExtra("menubean")
        Untils.initTitle(menuBean!!.menushowname, this)
        rv_list.setLayoutManager(LinearLayoutManager(this))
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        et_code.setOnKeyListener(View.OnKeyListener { _, i, event ->
            if (i == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
               check()
            }
            event.action == KeyEvent.ACTION_UP
        })
        b_search.setOnClickListener(View.OnClickListener {
            check()
        })
        iv_code.setOnClickListener(View.OnClickListener {
            openScan()
        })
        b_submit.setOnClickListener(View.OnClickListener {

            putData()
        })


        functionAdapter=FunctionAdapter(scanCheckBeanList!!)
        rv_list.adapter=functionAdapter
        functionAdapter!!.notifyDataSetChanged()



    }
    private fun openScan() {

        IntentIntegrator(this)
            .setCaptureActivity(ScanActivity::class.java)
            .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
            .setPrompt("请对准二维码")// 设置提示语
            .setCameraId(0)// 选择摄像头,可使用前置或者后置
            .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
            .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
            .initiateScan()// 初始化扫码

    }

    private  fun  check(){

        if(scanList!!.contains(et_code.text.toString())){
            Toast.makeText(this, "条码重复", Toast.LENGTH_LONG).show()
            return
        }
        Log.i("text-->",et_code.text.toString())
        if(et_code.text.toString().length != 21){
            Toast.makeText(this, "条码格式错误", Toast.LENGTH_LONG).show()

            return
        }else{
            scanCheckBeanList!!.add(ScanCheckBean(et_code.text.toString(),false))
            functionAdapter!!.notifyDataSetChanged()
            scanList!!.add(et_code.text.toString())
            et_code.setText("")
        }





    }

    inner class FunctionAdapter(private val mDatas:ArrayList<ScanCheckBean>) : RecyclerView.Adapter<FunctionAdapter.VH>() {



        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FunctionAdapter.VH {
            val binding = DataBindingUtil.inflate<ItemPackingInspetionBinding>(LayoutInflater.from(viewGroup.context), R.layout.item_packing_inspetion, viewGroup, false)
            return VH(binding.root)
        }

        override fun onBindViewHolder(vh: FunctionAdapter.VH, i: Int) {
            val binding = DataBindingUtil.getBinding<ItemPackingInspetionBinding>(vh.itemView)
            if(mDatas!![i].chccked){
                binding!!.tvCode.setTextColor(resources.getColor(R.color.blue))
            }else{
                binding!!.tvCode.setTextColor(resources.getColor(R.color.black))
            }
            binding!!.tvCode.text = (i+1).toString()+"/"+mDatas!![i].code
            binding!!.lItem.setOnClickListener(View.OnClickListener {
                scanList!!.remove(mDatas[i].code);
                scanCheckBeanList!!.removeAt(i);


                functionAdapter!!.notifyDataSetChanged()
            })
        }

        override fun getItemCount(): Int {
            return mDatas!!.size
        }

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        //扫码
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val code = result.contents

                et_code.setText(code)
                check()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
    private fun putData() {

        dialog.show()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")// HH:mm:ss
        //获取当前时间
        val date = Date(System.currentTimeMillis())
        val strings = simpleDateFormat.format(date)


        val jsonObject = JSONObject()
        try {
            jsonObject.put("methodname", "updateDocVouch")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("acccode", BaseActivity.acccode)
            jsonObject.put("menucode", menuBean!!.getMenucode())
            jsonObject.put("layout", "1")
            jsonObject.put("button", b_submit.getText().toString())
            jsonObject.put("condition","")

            val jsonArray = JSONArray()
            for (i in scanList!!.indices) {
                val `object` = JSONObject()
                `object`.put("barcode", scanList!!.get(i))
                jsonArray.put(`object`)
            }
            jsonObject.put("formdata",jsonArray.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val obj = jsonObject.toString()
        Log.i("requestbody-->", obj)
        val data = Request.getRequestbody(obj)
        data.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
                dialog.dismiss()
                var bean: ConfirmBean? = null
                try {
                    bean = Gson().fromJson(response.body()!!.string(), ConfirmBean::class.java)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
                Toast.makeText(this@HsScancheckActivity, bean!!.resultMessage, Toast.LENGTH_LONG).show()
                finish()

            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@HsScancheckActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
