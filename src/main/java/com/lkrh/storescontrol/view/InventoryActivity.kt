package com.lkrh.storescontrol.view

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
import com.lkrh.storescontrol.bean.GxbgBean
import com.lkrh.storescontrol.databinding.ItemGxbgBinding
import com.lkrh.storescontrol.untils.Untils
import kotlinx.android.synthetic.main.activity_inventory.*
import kotlinx.android.synthetic.main.item_gxbg.*

class InventoryActivity : BaseActivity() {
    var gxbgBean: GxbgBean?=null
    internal lateinit var adapter: FunctionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        Untils.initTitle("订单信息", this)
        gxbgBean = intent.getParcelableExtra("data")

        adapter = FunctionAdapter(gxbgBean!!.data)
        rv_gxbg_inventory.layoutManager = LinearLayoutManager(this)
        rv_gxbg_inventory.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_gxbg_inventory.adapter = adapter
    }
    inner class FunctionAdapter(private val mDatas:ArrayList<GxbgBean.Data>) : RecyclerView.Adapter<FunctionAdapter.VH>() {
        internal var binding: ItemGxbgBinding? = null

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FunctionAdapter.VH {
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_gxbg, viewGroup, false)
            return VH(binding!!.root)



        }


        override fun onBindViewHolder(vh: FunctionAdapter.VH, i: Int) {
            binding = DataBindingUtil.getBinding(vh.itemView)
            binding!!.tvTag.visibility=View.VISIBLE
            binding!!.tvTag.text = (i + 1).toString() + ""
            val data = mDatas[i]

            binding!!.tvRowno.setText("单号："+data.rowno)
            binding!!.tvIquantity.setText("数量："+data.iquantity)
            binding!!.tvInvCode.setText("存货编码："+data.InvCode)
            binding!!.tvInvName.setText("存货名称："+data.InvName)
            binding!!.tvInvStd.setText("规格型号："+data.InvStd)


            binding!!.rlLayout.setOnClickListener(View.OnClickListener {
                inventoryData=data
                processData=null
                finish()
            })



        }

        override fun getItemCount(): Int {
            return mDatas.size
        }

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}