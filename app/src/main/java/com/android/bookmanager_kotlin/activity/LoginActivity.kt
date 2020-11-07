package com.android.bookmanager_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bookmanager_kotlin.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.app_login)
    }
}
