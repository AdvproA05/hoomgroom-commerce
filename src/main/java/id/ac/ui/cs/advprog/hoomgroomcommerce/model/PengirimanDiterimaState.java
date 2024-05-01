package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public class PengirimanDiterimaState implements PengirimanState {
    @Override
    public void proses(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah diterima, tidak bisa diproses lagi");
    }

    @Override
    public void kirim(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah diterima, tidak bisa dikirim lagi");
    }

    @Override
    public void terima(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah diterima");
    }
}