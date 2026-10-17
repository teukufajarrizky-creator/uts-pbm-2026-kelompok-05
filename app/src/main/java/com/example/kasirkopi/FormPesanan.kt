package com.example.kasirkopi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

// Formulir pilih menu, jumlah, dan varian untuk menambah pesanan
// (kerangka dari ketua, diisi oleh Agil). Tombol di bawah hanya
// sementara dan harus diganti dengan tombol Tambah yang memakai validasi.
@Composable
fun FormPesanan(
    onTambah: (MenuItem, Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(stringResource(R.string.judul_form))
        Button(onClick = { onTambah(daftarMenu[0], 1, false) }) {
            Text(stringResource(R.string.tombol_tambah))
        }
    }
}