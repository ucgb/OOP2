package com.example.myapplicationoop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.editText
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var oldMerk = intent.getStringExtra("oldMerk")
        var oldType = intent.getStringExtra("oldType")
        var oldHarga = intent.getStringExtra("oldHarga")



        if (oldHarga.isNullOrBlank()){
            buttonUpdate.isEnabled = false
        }else{
            buttonSimpan.isEnabled = false
            editTextMerk.setText(oldMerk)
            editTextType.setText(oldType)
            editTextHarga.setText(oldHarga)
        }

        buttonSimpan.setOnClickListener {
            addDataLaptop()

            // clear data
            clearData()
        }

        buttonLihatData.setOnClickListener {
            startActivity<ListLaptopActivity>()
        }

        buttonUpdate.setOnClickListener {
            database.use {
                update(Laptop.TABLE_LAPTOP,
                    Laptop.MERK to editTextMerk.text.toString(),
                    Laptop.TYPE to editTextType.text.toString(),
                    Laptop.HARGA to editTextHarga.text.toString())
                    .whereArgs("${Laptop.MERK} = {merk}",
                        "merk" to oldMerk
                    ).exec()
            }

            // clear data
            clearData()
            toast("Data Diupdate")
        }
    }

    private fun addDataLaptop() {
        database.use {
            insert(Laptop.TABLE_LAPTOP,
                Laptop.MERK to editTextMerk.text.toString(),
                Laptop.TYPE to editTextType.text.toString(),
                Laptop.HARGA to editTextHarga.text.toString()
            )
            toast("Data berhasil disimpan!")
        }
    }

    fun clearData(){
        editTextMerk.text.clear()
        editTextType.text.clear()
        editTextHarga.text.clear()
    }
}