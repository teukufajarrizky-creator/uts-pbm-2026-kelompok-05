package com.example.kasirkopi

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidht
import androidx.compose.faoundation.layout.padding
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
        Text(
            text = stringResource(R. string.judul_layar),
            style = MaterialTheme.typography.headlineSmall
            )
    }
}
