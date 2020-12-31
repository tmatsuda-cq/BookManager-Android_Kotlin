package com.android.bookmanager_kotlin.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_logout.view.*

class LogoutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_logout, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setTitle(R.string.app_logout)
            it.setDisplayHomeAsUpEnabled(false)
        }

        // TODO: API実装時インターフェースでコールバックさせないとスレッドの違いからクラッシュするかも
        // Builderの引数はrequireContextではなくてactivityで良いのか？？

        val context = requireContext()

        view.bt_logout.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_msg_logout)
                .setPositiveButton(R.string.view_ok) { _, _ -> showLoginScreen()}
                .setNegativeButton(R.string.view_cancel, null)
                .show()
        }
        return view
    }

    private fun showLoginScreen() {
        val intent = Intent(activity?.application, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
