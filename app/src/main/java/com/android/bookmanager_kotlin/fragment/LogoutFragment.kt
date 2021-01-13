package com.android.bookmanager_kotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_logout.view.*

class LogoutFragment : Fragment(), LogoutDialogFragment.DialogListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_logout, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setTitle(R.string.app_logout)
            it.setDisplayHomeAsUpEnabled(false)
        }

        // TODO: setTargetFragmentが非推奨のため改善したい
        view.bt_logout.setOnClickListener {
          val dialogFragment: LogoutDialogFragment =  LogoutDialogFragment.newInstance()
          dialogFragment.setTargetFragment(this@LogoutFragment, 0)
          dialogFragment.show(parentFragmentManager, "LogoutDialogFragment")
        }
        return view
    }

    override fun onPositiveClick(dialog: DialogFragment) {
        val intent = Intent(activity?.application, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
