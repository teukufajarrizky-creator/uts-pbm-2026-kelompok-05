package com.example.kasirkopi

// Tambahan harga untuk minuman dingin (per gelas)
const val TAMBAHAN_DINGIN = 2000

// Batas subtotal untuk mendapat diskon, dan persen diskonnya
const val BATAS_DISKON = 500000
const val PERSEN_DISKON = 10

// Batas jumlah pesanan per item
const val JUMLAH_MIN = 1
const val JUMLAH_MAKS = 20

// Harga satu porsi: minuman dingin ditambah Rp2.000
fun hitungHargaSatuan(menu: MenuItem, dingin: Boolean): Int {
    return when {
        menu.kategori == Kategori.MINUMAN && dingin -> menu.harga + TAMBAHAN_DINGIN
        else -> menu.harga
    }
}

// Subtotal satu baris keranjang
fun hitungSubtotalItem(item: ItemKeranjang): Int {
    return hitungHargaSatuan(item.menu, item.dingin) * item.jumlah
}

// Menu dan varian yang sama digabung jumlahnya, bukan dibuat baris baru
fun gabungItem(keranjang: List<ItemKeranjang>, baru: ItemKeranjang): List<ItemKeranjang> {
    val sudahAda = keranjang.any {
        it.menu.nama == baru.menu.nama && it.dingin == baru.dingin
    }
    return if (sudahAda) {
        keranjang.map {
            if (it.menu.nama == baru.menu.nama && it.dingin == baru.dingin) {
                it.copy(jumlah = it.jumlah + baru.jumlah)
            } else {
                it
            }
        }
    } else {
        keranjang + baru
    }
}

// Subtotal seluruh keranjang
fun hitungSubtotal(keranjang: List<ItemKeranjang>): Int {
    return keranjang.sumOf { hitungSubtotalItem(it) }
}

// Diskon 10% bila subtotal mencapai Rp100.000 atau lebih
fun hitungDiskon(subtotal: Int): Int {
    return when {
        subtotal >= BATAS_DISKON -> subtotal * PERSEN_DISKON / 100
        else -> 0
    }
}

// Total yang harus dibayar
fun hitungTotal(subtotal: Int, diskon: Int): Int {
    return subtotal - diskon
}

// Kembalian (0 bila uang kurang)
fun hitungKembalian(uangBayar: Int, total: Int): Int {
    return when {
        uangBayar >= total -> uangBayar - total
        else -> 0
    }
}

// Jumlah valid: angka bulat 1-20, selain itu null (aman dari huruf/kosong)
fun parseJumlah(teks: String): Int? {
    val angka = teks.trim().toIntOrNull() ?: return null
    return when {
        angka in JUMLAH_MIN..JUMLAH_MAKS -> angka
        else -> null
    }
}

// Uang valid: angka bulat tidak negatif, selain itu null
fun parseUang(teks: String): Int? {
    val angka = teks.trim().toIntOrNull() ?: return null
    return when {
        angka >= 0 -> angka
        else -> null
    }
}

// Tombol Bayar aktif hanya bila keranjang tidak kosong dan uang mencukupi
fun bolehBayar(keranjang: List<ItemKeranjang>, uangBayar: Int?, total: Int): Boolean {
    return keranjang.isNotEmpty() && uangBayar != null && uangBayar >= total
}
