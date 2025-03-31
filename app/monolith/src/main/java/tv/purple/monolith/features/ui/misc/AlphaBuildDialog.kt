package tv.purple.monolith.features.ui.misc

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.TextView
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate

class AlphaBuildDialog(
    private val context: Context
) {
    private lateinit var dialog: AlertDialog

    fun show() {
        val builder = AlertDialog.Builder(context)
        val dialogView = context.inflate("purpletv_alpha_dialog")

        val titleTextView = dialogView.getView<TextView>("purpletv_alpha_dialog__title")
        val messageTextView = dialogView.getView<TextView>("purpletv_alpha_dialog__message")
        val okButton = dialogView.getView<Button>("purpletv_alpha_dialog__ok")

        titleTextView.text = "Alpha Build Notice"
        messageTextView.text = "You are currently using an alpha build of this application.\n " +
                "This version may contain bugs, incomplete features, and unexpected behavior.\n " +
                "It is intended for testing purposes only.\n " +
                "Please report any issues you encounter."

        builder.setView(dialogView)
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
            dialog.dismiss()
            Flag.ALPHA_BUILD_SHOWN.setValue(true)
        }

        dialog.show()
    }
}