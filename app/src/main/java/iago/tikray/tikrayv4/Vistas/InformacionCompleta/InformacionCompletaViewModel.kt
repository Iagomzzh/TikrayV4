package iago.tikray.tikrayv4.Vistas.InformacionCompleta

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InformacionCompletaViewModel @Inject constructor(): ViewModel() {




    fun copyToClipboard(textToCopy: String, context: Context) {

            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", textToCopy)
    }}


