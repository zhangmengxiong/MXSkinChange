package com.mx.skinchange.utils

fun Any.Log(message: String) {
    android.util.Log.v(this::class.java.simpleName, message)
}