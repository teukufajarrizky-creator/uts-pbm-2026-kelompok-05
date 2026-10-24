package com.example.kasirkopi

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// Kartu satu item keranjang dengan tombol hapus
@Composable
fun KartuItem(
    item: ItemKeranjang,
    onHapus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Menampilkan nama menu
                Text(
                    text = item.menu.nama,
                    style = MaterialTheme.typography.titleMedium
                )

                // Menampilkan jumlah pesanan (teks diambil dari strings.xml)
                Text(
                    text = stringResource(
                        R.string.format_item,
                        item.jumlah,
                        item.menu.nama
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                // Menampilkan varian hanya untuk minuman
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

                // Menampilkan subtotal harga
                Text(
                    text = rupiah(hitungSubtotalItem(item)),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Tombol untuk menghapus item
            Button(
                onClick = onHapus
            ) {
                Text(
                    text = stringResource(R.string.deskripsi_hapus)
                )
            }
        }
    }
}
