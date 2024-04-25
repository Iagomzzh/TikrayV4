package iago.tikray.tikrayv4

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthResult
import java.time.LocalTime

// AlertDialogs

@Composable
fun AlertDialogExample(
    dismiss: () -> Unit,
    confirm: () -> Unit,
    textTitle: String,
    textBody: String
) {

        AlertDialog(

            title = { Text(text = textTitle) },
            text = { Text(text = textBody) },
            onDismissRequest = { dismiss() },
            confirmButton = {
                TextButton(onClick = { confirm() }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { dismiss() }) {
                    Text("Cerrar")
                }


            },

        )

    }



