package com.example.kasirkopi

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

// Header layar berisi ikon dan judul (kerangka dari ketua, diisi oleh Rani)
@Composable
fun HeaderKedai(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.judul_layar),
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}