package com.android.bookmanager_kotlin.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_logout.*
import kotlinx.android.synthetic.main.fragment_logout.view.*

class LogoutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_logout, container, false)
        activity?.setTitle(R.string.app_logout)

        // TODO: ダイアログを表示してそこに画面遷移処理を実装する
        view.bt_logout.setOnClickListener {
            showLoginScreen()
        }
        return view
    }

    private fun showLoginScreen() {
        val intent = Intent(activity?.application, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
