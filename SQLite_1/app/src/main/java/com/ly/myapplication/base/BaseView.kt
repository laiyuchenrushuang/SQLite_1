package com.ly.myapplication.base

interface BaseView {
    fun showToast(msg: String)
    fun showToast(msgId: Int)
    fun showErrorDialog(msg: String)
}