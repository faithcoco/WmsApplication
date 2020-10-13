package com.lkrh.storescontrol.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.GuideBean
import com.lkrh.storescontrol.bean.LoginBean
import com.lkrh.storescontrol.bean.ProcessBean
import com.lkrh.storescontrol.databinding.ItemHwydBinding
import com.lkrh.storescontrol.databinding.ItemProcessBinding
import com.lkrh.storescontrol.untils.Untils
import com.lkrh.storescontrol.url.Request
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response

class ProcessActivity : BaseActivity() {

    internal var menuBean: LoginBean.Menu?=null
    internal var list: List<GuideBean.Data>? = null
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var adapter: Confirm2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
        Untils.initTitle("订单工序", this)
        menuBean = intent.getParcelableExtra("menubean")
        recyclerView = findViewById(R.id.rv_list)
        getData()


    }

    private fun getData() {
        val jsonObject = JSONObject()
        try {

            jsonObject.put("methodname", "getMenuFieldAndValue")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("menucode", menuBean!!.menucode)
            jsonObject.put("layout", "2")
            jsonObject.put("condition", intent.getStringExtra("condition"))
           // jsonObject.put("condition", "1000029419")
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
            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {

                try {
                    if (response.code() == 200) {

                        var processBean = Gson().fromJson(response.body()!!.string(), ProcessBean::class.java)

                        adapter = Confirm2Adapter(processBean.data, this@ProcessActivity)

                        recyclerView.layoutManager = LinearLayoutManager(this@ProcessActivity)
                        recyclerView.addItemDecoration(DividerItemDecoration(this@ProcessActivity, DividerItemDecoration.VERTICAL))
                        recyclerView.adapter = adapter


                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    inner class Confirm2Adapter(private val mDatas: List<ProcessBean.Data>, private val context: Context) : RecyclerView.Adapter<Confirm2Adapter.VH>() {
        internal var binding: ItemProcessBinding? = null

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Confirm2Adapter.VH {
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_process, viewGroup, false)
            return VH(binding!!.root)



        }


        override fun onBindViewHolder(vh: Confirm2Adapter.VH, i: Int) {
            binding = DataBindingUtil.getBinding(vh.itemView)
            binding!!.tvTag.visibility=View.VISIBLE
            binding!!.tvTag.text = (i + 1).toString() + ""
            binding!!.rlLayout.setOnClickListener {
                processData=mDatas[i]
                finish()
            }
            binding!!.tvProcessRowno.setText("工序行号："+mDatas[i].ProcessNo)
            binding!!.tvProcessCcode.setText("单号："+mDatas[i].ccode)
            binding!!.tvProcessName.setText("工序描述："+mDatas[i].ProcessDes)

            binding!!.tvProcessCount.setText("数量："+mDatas[i].iQuantity)
            binding!!.tvDone.setText("已完工："+mDatas[i].quantitysBG)
            binding!!.tvUndone.setText("未完工："+mDatas[i].quantitysWBG)


        }

        override fun getItemCount(): Int {
            return mDatas.size
        }

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}