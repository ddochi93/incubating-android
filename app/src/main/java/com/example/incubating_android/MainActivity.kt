package com.example.incubating_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.incubating_android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private val recyclerView by lazy {
        mainBinding.rv
    }
    private lateinit var myAdapter: MyAdapter
    private lateinit var imageList: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        initView()
    }

    private fun setUpDataBinding() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.activity = this
    }

    private fun initView() {
        imageList = ArrayList()
        for (index in 0 until 10) {
            val name = String.format("sample_%02d", index)
            val resourceId = this.resources.getIdentifier(name, "drawable", this.packageName)
            val title: String = "${index + 1} 번째 사진"
            imageList.add(Item(resourceId, title))
        }
        myAdapter = MyAdapter(imageList, longClicked)
        recyclerView.adapter = myAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.v("onScrolled", "dx:$dx dy:$dy")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    refreshView()
                }

            }
        })

    }

    private fun refreshView() {
        imageList.clear()
        for (index in 0 until 10) {
            val name = String.format("sample_%02d", (Math.random() * 10).toInt())
            val resourceId = this.resources.getIdentifier(name, "drawable", this.packageName)
            val title = "${index + 1} 번째 사진"
            imageList.add(Item(resourceId, title))
        }
        myAdapter = MyAdapter(imageList, longClicked)
        myAdapter.notifyDataSetChanged()
    }

    private val longClicked = { resourceId: Int ->
        createDialogWithTitle(resources.getResourceName(resourceId), this)
    }

}
