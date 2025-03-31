package tv.purple.monolith.features.proxy.view

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import tv.purple.monolith.bridge.RES_STRINGS
import tv.purple.monolith.core.LoggerImpl
import tv.purple.monolith.core.models.flag.Flag
import tv.purple.monolith.core.util.ViewUtil.getView
import tv.purple.monolith.core.util.ViewUtil.inflate

class CustomProxyUrlFragment : Fragment(), View.OnClickListener, TextWatcher {
    private lateinit var input: EditText
    private lateinit var submit: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.context.inflate(
            parent = container,
            resName = RES_STRINGS.purpletv_custom_proxy_url
        )

        input = view.getView("input")
        submit = view.getView(RES_STRINGS.purpletv_submit_custom_proxy_url)

        submit.setOnClickListener(this)
        input.addTextChangedListener(this)
        input.setText(Flag.CUSTOM_PROXY_URL.asString())

        return view
    }

    override fun onClick(p0: View?) {
        val text = "${input.text}".trim()
        if (isEmptyText(text) || isInvalidUrl(text)) {
            Toast.makeText(requireContext(), "Invalid url!", Toast.LENGTH_SHORT).show()
            return
        }

        LoggerImpl.debug("text: ${text}")
        Flag.CUSTOM_PROXY_URL.setValue(text)
        Toast.makeText(requireContext(), "Saved!", Toast.LENGTH_SHORT).show()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val text = p0?.let { p0.toString() }
        submit.isEnabled = !isInvalidUrl(text)
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    companion object {
        fun isInvalidUrl(text: String?): Boolean {
            text ?: return true

            return !URLUtil.isValidUrl(text)
        }

        fun isEmptyText(p0: CharSequence?): Boolean {
            p0 ?: return true

            if (TextUtils.isEmpty(p0)) {
                return true
            }

            return false
        }
    }
}