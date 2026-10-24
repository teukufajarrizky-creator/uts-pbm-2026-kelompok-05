package com.example.kasirkopi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// Formulir pilih menu, jumlah, dan varian untuk menambah pesanan
@Composable
fun FormPesanan(
    onTambah: (MenuItem, Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var jumlahTeks by remember { mutableStateOf("") }
    val jumlah = parseJumlah(jumlahTeks)
    val jumlahError = jumlahTeks.isNotBlank() && jumlah == null

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.judul_form),
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = jumlahTeks,
            onValueChange = { jumlahTeks = it },
            label = { Text(stringResource(R.string.label_jumlah)) },
            isError = jumlahError,
            supportingText = {
                if (jumlahError) Text(stringResource(R.string.error_jumlah_tidak_valid))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
