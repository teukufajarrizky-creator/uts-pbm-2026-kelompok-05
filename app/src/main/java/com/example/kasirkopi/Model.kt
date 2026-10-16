package com.example.kasirkopi

// Kategori menu: minuman atau makanan
enum class Kategori {
    MINUMAN,
    MAKANAN
}

// Satu jenis menu di kedai (data tetap, ditulis di kode sesuai aturan kasus)
data class MenuItem(
    val nama: String,
    val kategori: Kategori,
    val harga: Int
)

// Satu baris di keranjang: menu + jumlah + varian (dingin/panas)
data class ItemKeranjang(
    val menu: MenuItem,
    val jumlah: Int,
    val dingin: Boolean
)

// Daftar menu tetap kedai kopi
val daftarMenu = listOf(
    MenuItem("Kopi Hitam", Kategori.MINUMAN, 5000),
    MenuItem("Sanger", Kategori.MINUMAN, 8000),
    MenuItem("Kopi Susu", Kategori.MINUMAN, 10000),
    MenuItem("Teh Tarik", Kategori.MINUMAN, 8000),
    MenuItem("Roti Srikaya", Kategori.MAKANAN, 6000)
)