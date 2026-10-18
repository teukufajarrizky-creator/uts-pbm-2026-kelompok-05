package com.example.kasirkopi

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidht
import androidx.compose.faoundation.layout.padding
import androidx.compase.foundation.layout.size
import androidx.compase.foundation.layout.width
import androidx.compase.foundation.layout.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.aligment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
impoert androidx.compose.ui.unit.dp

// Header layar berisi ikon dan judul (kerangka dari ketua, diisi oleh Rani)
@Composable
fun HeaderKedai(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
        verticalAligment = Aligment.CenterVertically
    ) { 
        Icon (
            painter = painterResource(id = R.drawable.ic_Kopi),
            contentDescription = stringResource(R.string.deskripsi_ikon_menu),
            modifier = Modifier.size(40.dp)
        )
        Spacer (modifier = Modifier.width(12.dp))
        Text(
            text = stringResource(R. string.judul_layar),
            style = MaterialTheme.typography.headlineSmall
            )
    }
}
