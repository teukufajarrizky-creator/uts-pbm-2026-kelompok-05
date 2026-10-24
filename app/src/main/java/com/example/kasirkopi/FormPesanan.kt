package com.example.kasirkopi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
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
    var menuTerpilih by remember { mutableStateOf(daftarMenu[0]) }
    var jumlahTeks by remember { mutableStateOf("") }
    var dingin by remember { mutableStateOf(false) }

    val jumlah = parseJumlah(jumlahTeks)
    val jumlahError = jumlahTeks.isNotBlank() && jumlah == null
    val adalahMinuman = menuTerpilih.kategori == Kategori.MINUMAN

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.judul_form),
            style = MaterialTheme.typography.titleMedium
        )

        Text(text = stringResource(R.string.label_menu))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            daftarMenu.forEach { menu ->
                FilterChip(
                    selected = menu == menuTerpilih,
                    onClick = {
                        menuTerpilih = menu
                        if (menu.kategori != Kategori.MINUMAN) dingin = false
                    },
                    label = { Text(menu.nama) }
                )
            }
        }

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

        if (adalahMinuman) {
            Text(text = stringResource(R.string.label_varian))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = !dingin,
                    onClick = { dingin = false },
                    label = { Text(stringResource(R.string.varian_panas)) }
                )
                FilterChip(
                    selected = dingin,
                    onClick = { dingin = true },
                    label = { Text(stringResource(R.string.varian_dingin)) }
                )
            }
        }

        Button(
            onClick = {
                if (jumlah != null) {
                    onTambah(menuTerpilih, jumlah, dingin)
                    jumlahTeks = ""
                    dingin = false
                }
            },
            enabled = jumlah != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.tombol_tambah))
        }
    }
}
