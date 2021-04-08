package com.meza.moviesapp.util

import android.content.res.Resources
import androidx.fragment.app.DialogFragment
import com.meza.moviesapp.R

class DialogUtil {
    companion object {
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