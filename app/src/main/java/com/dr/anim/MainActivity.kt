package com.dr.anim

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.view.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: MutableList<Bean> = ArrayList()
        list.add(Bean("Transition", TransitionActivity::class.java))
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = MyAdapter(list, object : MyAdapter.OnItemClickListener {
            override fun onItemClick(item: Bean) {
                startActivity(Intent(this@MainActivity, item.dest))
            }

        })


    }
}

class MyAdapter(private val datas: MutableList<Bean>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.title.text = datas[position].title
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(datas[position])
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface OnItemClickListener {
        fun onItemClick(item: Bean)
    }

}

data class Bean(
        val title: String,
        val dest: Class<out Activity>
)
