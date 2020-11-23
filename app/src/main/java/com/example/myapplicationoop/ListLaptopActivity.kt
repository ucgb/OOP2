package com.example.myapplicationoop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_laptop.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListLaptopActivity : AppCompatActivity() {
    private lateinit var adapter: RVAdapter
    private var laptop = ArrayList<Laptop>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_laptop)

        adapter = RVAdapter(this, laptop)
        recylerView.adapter = adapter

        getData()
        recylerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            laptop.clear()
            var result = select(Laptop.TABLE_LAPTOP)
            var dataLaptop = result.parseList(classParser<Laptop>())
            laptop.addAll(dataLaptop)
            adapter.notifyDataSetChanged()
        }
    }
}