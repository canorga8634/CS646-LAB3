package com.zybooks.counterapplab3

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class WarningDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?)
            : Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.warning)
        builder.setMessage(R.string.warning_message)
        builder.setPositiveButton(R.string.ok, null)
        return builder.create()
    }
}