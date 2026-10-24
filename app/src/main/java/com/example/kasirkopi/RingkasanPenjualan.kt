package com.example.kasirkopi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// Ringkasan jumlah transaksi dan total penjualan (kerangka dari ketua, diisi oleh Alya)
@Composable
fun RingkasanPenjualan(
    jumlahTransaksi: Int,
    totalPenjualan: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.judul_ringkasan),
            style = MaterialTheme.typography.titleMedium
        )
        // Jumlah transaksi adalah hitungan, bukan uang, jadi tanpa awalan Rp
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.label_jumlah_transaksi))
            Text(text = jumlahTransaksi.toString())
        }
        BarisHarga(
            label = R.string.label_total_penjualan,
            nilai = totalPenjualan
        )
    }
}