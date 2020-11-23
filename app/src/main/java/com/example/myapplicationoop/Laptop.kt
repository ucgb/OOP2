package com.example.myapplicationoop

data class Laptop(var id: Long?, var merk: String?, var type: String?, var harga: String?) {
    companion object {
        const val TABLE_LAPTOP: String = "TABLE_LAPTOP"
        const val ID: String = "ID"
        const val MERK: String = "MERK"
        const val TYPE: String = "TYPE"
        const val HARGA: String = "HARGA"

    }
}