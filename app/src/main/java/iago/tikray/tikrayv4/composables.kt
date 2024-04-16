package iago.tikray.tikrayv4

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData

// AlertDialogs

@Composable
fun AlertDialogExample(
    show: LiveData<Boolean>,
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


            }
        )

    }

