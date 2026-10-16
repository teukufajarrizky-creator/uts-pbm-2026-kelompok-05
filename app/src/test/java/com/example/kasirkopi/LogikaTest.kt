package com.example.kasirkopi

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LogikaTest {

    // Menu bantu: urutan sama dengan daftarMenu di Model.kt
    private val kopiHitam = daftarMenu[0]   // 5.000
    private val sanger = daftarMenu[1]      // 8.000
    private val kopiSusu = daftarMenu[2]    // 10.000
    private val rotiSrikaya = daftarMenu[4] // 6.000

    @Test
    fun hargaSatuan_minumanDingin_ditambah2000() {
        // 10.000 + 2.000 = 12.000
        assertEquals(12000, hitungHargaSatuan(kopiSusu, true))
    }

    @Test
    fun hargaSatuan_makananTidakKenaTambahanDingin() {
        assertEquals(6000, hitungHargaSatuan(rotiSrikaya, true))
    }

    @Test
    fun subtotalItem_sangerDingin3Gelas() {
        // (8.000 + 2.000) x 3 = 30.000
        val item = ItemKeranjang(sanger, 3, true)
        assertEquals(30000, hitungSubtotalItem(item))
    }

    @Test
    fun gabungItem_menuDanVarianSama_jumlahDigabung() {
        val awal = listOf(ItemKeranjang(kopiHitam, 2, false))
        val hasil = gabungItem(awal, ItemKeranjang(kopiHitam, 3, false))
        assertEquals(1, hasil.size)
        assertEquals(5, hasil[0].jumlah)
    }

    @Test
    fun gabungItem_varianBeda_barisBaru() {
        val awal = listOf(ItemKeranjang(kopiHitam, 2, false))
        val hasil = gabungItem(awal, ItemKeranjang(kopiHitam, 1, true))
        assertEquals(2, hasil.size)
    }

    @Test
    fun diskon_dibawah100ribu_nol() {
        assertEquals(0, hitungDiskon(99999))
    }

    @Test
    fun diskon_tepat100ribu_10persen() {
        assertEquals(10000, hitungDiskon(100000))
    }

    @Test
    fun total_keranjangDapatDiskon() {
        // Kopi susu dingin x5 = 12.000 x 5 = 60.000
        // Sanger panas x5 = 8.000 x 5 = 40.000
        // Subtotal 100.000, diskon 10.000, total 90.000
        val keranjang = listOf(
            ItemKeranjang(kopiSusu, 5, true),
            ItemKeranjang(sanger, 5, false)
        )
        val subtotal = hitungSubtotal(keranjang)
        val diskon = hitungDiskon(subtotal)
        assertEquals(100000, subtotal)
        assertEquals(10000, diskon)
        assertEquals(90000, hitungTotal(subtotal, diskon))
    }

    @Test
    fun kembalian_uangCukup() {
        assertEquals(10000, hitungKembalian(100000, 90000))
    }

    @Test
    fun kembalian_uangKurang_nol() {
        assertEquals(0, hitungKembalian(50000, 90000))
    }

    @Test
    fun parseJumlah_valid() {
        assertEquals(5, parseJumlah("5"))
        assertEquals(1, parseJumlah("1"))
        assertEquals(20, parseJumlah("20"))
    }

    @Test
    fun parseJumlah_tidakValid_null() {
        assertNull(parseJumlah(""))
        assertNull(parseJumlah("abc"))
        assertNull(parseJumlah("0"))
        assertNull(parseJumlah("21"))
        assertNull(parseJumlah("-3"))
    }

    @Test
    fun parseUang_validDanTidakValid() {
        assertEquals(50000, parseUang("50000"))
        assertNull(parseUang(""))
        assertNull(parseUang("lima"))
        assertNull(parseUang("-100"))
    }

    @Test
    fun bolehBayar_semuaKondisi() {
        val keranjang = listOf(ItemKeranjang(kopiHitam, 2, false))
        // Total 10.000
        assertFalse(bolehBayar(emptyList(), 50000, 0))
        assertFalse(bolehBayar(keranjang, null, 10000))
        assertFalse(bolehBayar(keranjang, 5000, 10000))
        assertTrue(bolehBayar(keranjang, 10000, 10000))
        assertTrue(bolehBayar(keranjang, 20000, 10000))
    }
}