package com.lkrh.storescontrol.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.GxbgBean
import com.lkrh.storescontrol.bean.LoginBean
import com.lkrh.storescontrol.bean.ProcessBean
import com.lkrh.storescontrol.untils.Untils
import com.lkrh.storescontrol.untils.iToast
import com.lkrh.storescontrol.url.Request
import kotlinx.android.synthetic.main.activity_gxbg.*
import kotlinx.android.synthetic.main.item_gxbg.*
import kotlinx.android.synthetic.main.item_process.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response

class GxbgActivity : BaseActivity() {
    var menuBean: LoginBean.Menu? = null
    var gxbgBean:GxbgBean?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gxbg)

        menuBean = intent.getParcelableExtra("menubean")
        Untils.initTitle(menuBean!!.menushowname, this)
        val sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE)
        tv_user.setText(sharedPreferences.getString("user", ""))
        iv_scan.setOnClickListener(onClickListener)
        tv_inventory.setOnClickListener(onClickListener)
        tv_process.setOnClickListener(onClickListener)
        b_start.setOnClickListener(onClickListener)
        b_ok.setOnClickListener(onClickListener)
       et_user.addTextChangedListener(object : TextWatcher {
           override fun beforeTextChanged(
               s: CharSequence,
               start: Int,
               count: Int,
               after: Int
           ) {
           }

           override fun onTextChanged(
               s: CharSequence,
               start: Int,
               before: Int,
               count: Int
           ) {
           }

           override fun afterTextChanged(s: Editable) {
               if (handler.hasMessages(0)) {
                   handler.removeMessages(0)
               }
               handler.sendEmptyMessageDelayed(0, 1000)
           }
       })
        et_code.setOnKeyListener(View.OnKeyListener { view, i, event ->
            if (i == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                getData()
            }
            if (event.action == KeyEvent.ACTION_UP) {
                true
            } else {
                false
            }
        })

    }
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    et_user.setText(
                        et_user.getText().toString().toString() + ","
                    )
                    this.removeMessages(0)
                    et_user.requestFocus()
                    et_user.setSelection(et_user.text.length)
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(inventoryData!=null){
            l_gxbg_info.visibility=View.VISIBLE
            setInfo(inventoryData)
        }
        if(processData!=null){
            l_gxbg_process.visibility=View.VISIBLE
            setProcess(processData)

        }else{

            et_count.setText("")
            et_user.setText("")
        }
    }

    private fun setProcess(processData: ProcessBean.Data) {
        tv_process_rowno.setText("工序行号：" + processData.rowno)
        tv_process_ccode.setText("单号：" + processData.ccode)
        tv_process_name.setText("工序描述：" + processData.invname)
        tv_process_count.setText("数量：" + processData.iQuantity)
        tv_done.setText("已完工：" + processData.quantitysBG)
        tv_undone.setText("未完工：" + processData.quantitysWBG)
    }

    var onClickListener =
        View.OnClickListener { view ->
            when (view.id) {
                iv_scan.id -> {
                    Untils.openScan(this)
                }
                tv_inventory.id -> {
                    if (gxbgBean != null) {
                        intent = Intent(
                            this,
                            InventoryActivity::class.java
                        )
                        intent.putExtra("data", gxbgBean)
                        startActivity(intent)
                    } else {
                        iToast.showToast(this, "请先进行条码扫描", 1000)

                    }
                }
                tv_process.id -> {
                    if (inventoryData != null) {
                        intent = Intent(
                            this,
                            ProcessActivity::class.java
                        )
                        intent.putExtra("condition", inventoryData.rowno)
                        intent.putExtra("menubean", menuBean)
                        startActivity(intent)
                    } else {
                        iToast.showToast(this, "请先选择订单信息", 1000)
                    }
                }
                b_start.id -> {
                    putdata("1")
                }
                b_ok.id -> {
                    if (processData != null) {

                        if (processData.quantitysWBG.toDouble() < et_count.text.toString()
                                .toDouble()
                        ) {
                            iToast.showToast(this, "当前完工数量不能大于未完工数量", 1000)
                            return@OnClickListener
                        }
                    }
                    putdata("2")
                }

                else -> {
                }
            }
        }
    private  fun setInfo(inventoryData: GxbgBean.Data){
        tv_rowno.setText("单号：" + inventoryData.rowno)
        tv_iquantity.setText("数量：" + inventoryData.iquantity)
        tv_invCode.setText("存货编码：" + inventoryData.InvCode)
        tv_invName.setText("存货名称：" + inventoryData.InvName)
        tv_invStd.setText("规格型号：" + inventoryData.InvStd)
    }
    private fun getData() {
        val jsonObject = JSONObject()
        try {

            jsonObject.put("methodname", "getMenuFieldAndValue")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("menucode", menuBean!!.menucode)
            jsonObject.put("layout", "1")
            jsonObject.put("condition", et_code.text.toString())
            jsonObject.put("barcode", "")
            jsonObject.put("formdata", "")
            jsonObject.put("acccode", BaseActivity.acccode)


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

                        gxbgBean = Gson().fromJson(
                            response.body()!!.string(),
                            GxbgBean::class.java
                        )
                        if (gxbgBean!!.Resultcode.equals("0")) {
                            iToast.showToast(this@GxbgActivity, gxbgBean!!.ResultMessage, 1000)
                        } else {
                            tv_cStatus.setText(gxbgBean!!.cStatus)
                            if (gxbgBean!!.cStatus.equals("未开工")) {
                                b_start.visibility = View.VISIBLE
                            }
                        }


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
            jsonObject.put("menucode", menuBean!!.menucode)
            jsonObject.put("layout", "1")
            jsonObject.put("button", tag)
            jsonObject.put("condition", et_code.text.toString())
            if(tag=="2"){
                var formdata=JSONObject()
                formdata.put("ccode", processData.ccode)
                formdata.put("ProcessNo", processData.ProcessNo)
                formdata.put("iquantity", et_count.text.toString())
                formdata.put("Operator", tv_user.text.toString())
                jsonObject.put("formdata", formdata.toString())
            }else{
                jsonObject.put("formdata", "")
            }
            jsonObject.put("acccode", BaseActivity.acccode)


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

                            if (tag.equals("2")) {
                                et_count.setText("")
                                et_user.setText("")
                                processData = ProcessBean.Data(
                                    "", "", "",
                                    "", "", "", "", "", "", ""
                                )

                                inventoryData = GxbgBean.Data("","","","","")
                                setProcess(processData)
                                setInfo(inventoryData)
                            } else {
                                b_start.setVisibility(View.GONE)
                                getData()
                            }
                        }



                        iToast.showToast(this@GxbgActivity, gxbgBean!!.ResultMessage, 1000)


                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {

            }
        })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        //扫码
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val code = result.contents
                Log.i("scan", code)
                et_code.setText(code)
                getData()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
}