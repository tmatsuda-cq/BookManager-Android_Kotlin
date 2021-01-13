package com.android.bookmanager_kotlin.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.android.bookmanager_kotlin.R
import java.lang.ClassCastException
import java.lang.IllegalStateException

class LogoutDialogFragment : DialogFragment() {

    private lateinit var listener: DialogListener

    // TODO: publicでも良いか確認
    companion object {
        fun newInstance() : LogoutDialogFragment {
            return LogoutDialogFragment()
        }
    }

    interface DialogListener {
        fun onPositiveClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // TODO: targetFragmentが非推奨のため改善したい
        try {
            listener = targetFragment as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((targetFragment.toString() +
                    "DialogLister が実装されていません"))
        }
    }

    // ダイアログを生成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)

            builder.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_msg_logout)
                .setPositiveButton(R.string.view_ok,
                DialogInterface.OnClickListener { _, _ ->
                    listener.onPositiveClick(this)
                })
                .setNegativeButton(R.string.view_cancel, null)

            builder.create()
        } ?: throw IllegalStateException("Activity が nullになっている")
    }
}
