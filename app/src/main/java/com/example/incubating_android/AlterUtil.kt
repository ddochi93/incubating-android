package com.example.incubating_android

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

fun createDialogWithTitle(title: String, context: Context) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setCancelable(true)
    builder.setPositiveButton("확인",DialogInterface.OnClickListener { _, _ ->  } )
    builder.create().show()
}