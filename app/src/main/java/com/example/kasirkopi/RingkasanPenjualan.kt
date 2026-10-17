package com.example.kasirkopi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

// Ringkasan jumlah transaksi dan total penjualan sejak aplikasi dibuka
// (kerangka dari ketua, diisi oleh Alya). Masih perlu tata letak yang rapi.
@Composable
fun RingkasanPenjualan(
    jumlahTransaksi: Int,
    totalPenjualan: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(stringResource(R.string.judul_ringkasan))
        Text(stringResource(R.string.label_jumlah_transaksi))
        Text(jumlahTransaksi.toString())
        Text(stringResource(R.string.label_total_penjualan))
        Text(rupiah(totalPenjualan))
    }
}