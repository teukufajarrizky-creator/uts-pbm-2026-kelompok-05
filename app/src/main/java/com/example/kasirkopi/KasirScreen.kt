package com.example.kasirkopi

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import java.text.NumberFormat
import java.util.Locale

// Composable induk: semua state layar disimpan di sini
@Composable
fun KasirScreen(modifier: Modifier = Modifier) {
    var keranjang by remember { mutableStateOf(listOf<ItemKeranjang>()) }
    var uangTeks by remember { mutableStateOf("") }
    var kembalian by remember { mutableStateOf<Int?>(null) }
    var jumlahTransaksi by remember { mutableStateOf(0) }
    var totalPenjualan by remember { mutableStateOf(0) }

    // Nilai turunan: dihitung ulang otomatis setiap state berubah
    val subtotal = hitungSubtotal(keranjang)
    val diskon = hitungDiskon(subtotal)
    val total = hitungTotal(subtotal, diskon)
    val uang = parseUang(uangTeks)
    val aktif = bolehBayar(keranjang, uang, total)
    val errorUang: Int? = when {
        uangTeks.isBlank() -> if (keranjang.isNotEmpty()) R.string.error_uang_kosong else null
        uang == null -> R.string.error_uang_tidak_valid
        keranjang.isNotEmpty() && uang < total -> R.string.error_uang_kurang
        else -> null
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderKedai()

        FormPesanan(
            onTambah = { menu, jumlah, dingin ->
                val baru = ItemKeranjang(menu, jumlah, dingin && menu.kategori == Kategori.MINUMAN)
                keranjang = gabungItem(keranjang, baru)
                kembalian = null
            }
        )

        Text(
            text = stringResource(R.string.judul_keranjang),
            style = MaterialTheme.typography.titleMedium
        )
        if (keranjang.isEmpty()) {
            Text(stringResource(R.string.keranjang_kosong))
        } else {
            keranjang.forEach { item ->
                KartuItem(
                    item = item,
                    onHapus = { keranjang = keranjang.filter { it != item } }
                )
            }
        }

        PanelPembayaran(
            subtotal = subtotal,
            diskon = diskon,
            total = total,
            uangTeks = uangTeks,
            errorUang = errorUang,
            aktif = aktif,
            onUangBerubah = { uangTeks = it },
            onBayar = {
                if (uang != null && aktif) {
                    kembalian = hitungKembalian(uang, total)
                    jumlahTransaksi = jumlahTransaksi + 1
                    totalPenjualan = totalPenjualan + total
                    keranjang = emptyList()
                    uangTeks = ""
                }
            }
        )

        kembalian?.let { nilai ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.pesan_berhasil))
                BarisHarga(R.string.label_kembalian, nilai)
            }
        }

        RingkasanPenjualan(
            jumlahTransaksi = jumlahTransaksi,
            totalPenjualan = totalPenjualan
        )
    }
}

// Stateless: semua data dan event datang lewat parameter
@Composable
fun PanelPembayaran(
    subtotal: Int,
    diskon: Int,
    total: Int,
    uangTeks: String,
    @StringRes errorUang: Int?,
    aktif: Boolean,
    onUangBerubah: (String) -> Unit,
    onBayar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.judul_pembayaran),
            style = MaterialTheme.typography.titleMedium
        )
        BarisHarga(R.string.label_subtotal, subtotal)
        BarisHarga(R.string.label_diskon, diskon)
        BarisHarga(R.string.label_total, total)
        OutlinedTextField(
            value = uangTeks,
            onValueChange = onUangBerubah,
            label = { Text(stringResource(R.string.label_uang)) },
            isError = errorUang != null,
            supportingText = {
                if (errorUang != null) Text(stringResource(errorUang))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onBayar,
            enabled = aktif,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.tombol_bayar))
        }
    }
}

// Stateless, dipakai berulang: satu baris "label ..... Rp nilai"
@Composable
fun BarisHarga(@StringRes label: Int, nilai: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(label))
        Text(rupiah(nilai))
    }
}

// Format angka jadi "Rp10.000"
@Composable
fun rupiah(nilai: Int): String {
    val angka = NumberFormat.getInstance(Locale.forLanguageTag("id-ID")).format(nilai)
    return stringResource(R.string.format_rupiah, angka)
}