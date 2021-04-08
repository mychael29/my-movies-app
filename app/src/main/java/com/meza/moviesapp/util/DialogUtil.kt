package com.meza.moviesapp.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.meza.moviesapp.R

class DialogUtil {
    interface OnEventDialog {
        fun onClickAccept()
    }
    companion object {
        private fun setupDialog(ctx: Context?, res: Int): Dialog {
            val dialog = Dialog(ctx!!)
            dialog.setCancelable(false)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(res)
            return dialog
        }

        fun showDialog(context: Context?, message: String?) {
            if (context != null && !(context as Activity).isFinishing) {
                val dialog: Dialog = setupDialog(context, R.layout.dialog_message)
                val mTxtMessage = dialog.findViewById<TextView>(R.id.message_dialog)
                mTxtMessage.text = message
                val mBtnClose =
                    dialog.findViewById<Button>(R.id.close_dialog_liq)
                mBtnClose.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }
        }

        fun showDialogListener(context: Context?, message: String?, onEventDialog: OnEventDialog) {
            if (context != null && !(context as Activity).isFinishing) {
                val dialog: Dialog = setupDialog(context, R.layout.dialog_message)
                val mTxtMessage = dialog.findViewById<TextView>(R.id.message_dialog)
                mTxtMessage.text = message
                val mBtnClose =
                    dialog.findViewById<Button>(R.id.close_dialog_liq)
                mBtnClose.setOnClickListener {
                    dialog.dismiss()
                    onEventDialog.onClickAccept()
                }
                dialog.show()
            }
        }

        fun configDialogFragment(dialogFragment: DialogFragment, resources: Resources) {
            dialogFragment.dialog?.let { dialog ->
                dialog.window?.let {
                    it.setBackgroundDrawableResource(R.color.colorWhite)
                    it.setLayout(
                        (resources.displayMetrics.widthPixels * 0.95).toInt(),
                        (resources.displayMetrics.heightPixels * 0.80).toInt()
                    )
                }
            }
        }
    }
}