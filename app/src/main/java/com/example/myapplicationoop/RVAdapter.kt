package com.example.myapplicationoop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RVAdapter(val context: Context, val items: ArrayList<Laptop>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(items: Laptop){
            itemView.merkLaptop.text = items.merk
            itemView.typeLaptop.text = items.type
            itemView.hargaLaptop.text = items.harga

            itemView.btnEdit.setOnClickListener {
                itemView.context.startActivity<MainActivity>(
                    "oldMerk" to items.merk,
                    "oldType" to items.type,
                    "oldHarga" to items.harga
                )
            }

            itemView.btnHapus.setOnClickListener {
                itemView.context.database.use {
                    delete(Laptop.TABLE_LAPTOP, "(${Laptop.MERK} = {merk})",
                        "merk" to items.merk.toString())
                }
                itemView.context.toast("Data Dihapus")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}