package com.lkrh.storescontrol.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.AssemblyBean
import com.lkrh.storescontrol.bean.LoginBean
import com.lkrh.storescontrol.databinding.ItemAssemblyBinding
import com.lkrh.storescontrol.untils.Untils

class AssemblyListActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var stringList: List<String>? = null
    var assemblyList:ArrayList<AssemblyBean.Data>? = null
    var sharedPreferences: SharedPreferences?=null
    private var functionAdapter: AssemblyAdapter? = null
    var menuBean: LoginBean.Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val v = layoutInflater.inflate(R.layout.activity_assembly_list, null, false)
        setContentView(v)
        menuBean = intent.getParcelableExtra("menubean")
        Untils.initTitle(menuBean!!.menushowname, this)

        sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE)
        var data = ""
         Log.i("test-->",menuBean!!.getMenucode())
        assemblyList= ArrayList()
        data = sharedPreferences!!.getString(menuBean!!.getMenucode() + "List", "")?:"null"

        if (data != "") {
            try {
                val gson = Gson()
                val arry = JsonParser().parse(data).asJsonArray
                for (jsonElement in arry) {

                    assemblyList!!.add(gson.fromJson(jsonElement, AssemblyBean.Data::class.java))
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        recyclerView = findViewById(R.id.rv_list)

        functionAdapter =AssemblyAdapter(assemblyList!!, this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView!!.setAdapter(functionAdapter)

    }
    inner class AssemblyAdapter(
        private val mDatas: ArrayList<AssemblyBean.Data>,
        private val context: Context
    ) : RecyclerView.Adapter<AssemblyAdapter.VH>() {
        internal var binding: ItemAssemblyBinding? = null

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AssemblyAdapter.VH {
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_assembly,
                viewGroup,
                false
            )
            return VH(binding!!.root)



        }


        override fun onBindViewHolder(vh: AssemblyAdapter.VH, i: Int) {
            binding = DataBindingUtil.getBinding(vh.itemView)
            binding!!.tvTag.visibility=View.VISIBLE
            binding!!.tvTag.text = (i + 1).toString() + ""
            binding!!.tvCcode.setText(mDatas[i].ccode)
            binding!!.tvCInvName.setText(mDatas[i].cInvName)
            binding!!.rlLayout.setOnClickListener {
                val builder = AlertDialog.Builder(this@AssemblyListActivity)
                builder.setTitle("提示").setMessage("删除此条数据").setPositiveButton(
                    "确定"
                ) { dialog, which ->
                    assemblyList!!.removeAt(i)
                    sharedPreferences!!.edit()
                        .putString(
                            menuBean!!.menucode + "List",
                            Gson().toJson(assemblyList)
                        ).commit()
                    functionAdapter!!.notifyDataSetChanged()
                }.setNegativeButton(
                    "取消"
                ) { dialog, which -> dialog.dismiss() }.create().show()


            }



        }

        override fun getItemCount(): Int {
            return mDatas.size
        }

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }



}