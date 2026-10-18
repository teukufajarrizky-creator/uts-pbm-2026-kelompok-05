package com.example.kasirkopi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource

// Kartu satu item keranjang dengan tombol hapus (kerangka dari ketua, diisi oleh Tara)
@Composable
fun KartuItem(
    item: ItemKeranjang,
    onHapus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = item.menu.nama,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${item.jumlah} x ${item.menu.nama}",
                style = MaterialTheme.typography.bodyMedium
            )

            if (item.menu.kategori == Kategori.MINUMAN) {
                Text(
                    text = if (item.dingin) {
                        stringResource(R.string.varian_item_dingin)
                    } else {
                        stringResource(R.string.varian_item_panas)
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}