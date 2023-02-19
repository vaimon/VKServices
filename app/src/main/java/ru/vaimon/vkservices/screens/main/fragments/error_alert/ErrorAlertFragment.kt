package ru.vaimon.vkservices.screens.main.fragments.error_alert

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.vaimon.vkservices.R

class ErrorAlertFragment(
    val errorResourceId: Int
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(errorResourceId))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .create()

    companion object {
        const val TAG = "ErrorAlertDialog"
    }
}