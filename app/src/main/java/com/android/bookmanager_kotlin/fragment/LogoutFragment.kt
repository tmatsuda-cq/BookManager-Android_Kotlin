package com.android.bookmanager_kotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.activity.LoginActivity

class LogoutFragment : Fragment(), LogoutDialogFragment.DialogListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_logout, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setTitle(R.string.app_logout)
            it.setDisplayHomeAsUpEnabled(false)
        }

        view?.findViewById<Button>(R.id.logout_button)?.setOnClickListener {
          LogoutDialogFragment.newInstance().show(childFragmentManager, LogoutDialogFragment.javaClass.simpleName)
        }
        return view
    }

    override fun onPositiveClick(dialog: DialogFragment) {
        // TODO: API実装時、ログアウトAPI叩きつつ画面遷移
        val intent = Intent(activity?.application, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
