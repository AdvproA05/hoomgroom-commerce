package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public class PengirimanDikirimState implements PengirimanState {
    @Override
    public void proses(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah dikirim, tidak bisa diproses lagi");
    }

    @Override
    public void kirim(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah dikirim");
    }

    @Override
    public void terima(Pengiriman pengiriman) {
        System.out.println("Pengiriman diterima");
        pengiriman.setState(new PengirimanDiterimaState());
    }
}