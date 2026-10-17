package com.example.kasirkopi

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// Kartu satu item keranjang dengan tombol hapus (kerangka dari ketua,
// diisi oleh Tara). Masih perlu varian, subtotal item, dan tata letak yang rapi.
@Composable
fun KartuItem(
    item: ItemKeranjang,
    onHapus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp)) {
            Text(
                text = stringResource(R.string.format_item, item.jumlah, item.menu.nama),
                modifier = Modifier.weight(1f)
            )
            Button(onClick = onHapus) {
                Text(stringResource(R.string.deskripsi_hapus))
            }
        }
    }
}