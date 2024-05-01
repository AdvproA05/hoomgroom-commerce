package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public class DiprosesState implements PengirimanState {
    @Override
    public void proses(Pengiriman pengiriman) {
        System.out.println("Pengiriman sedang diproses");
    }

    @Override
    public void kirim(Pengiriman pengiriman) {
        System.out.println("Pengiriman dikirim");
        pengiriman.setState(new DikirimState());
    }

    @Override
    public void terima(Pengiriman pengiriman) {
        System.out.println("Pengiriman tidak bisa diterima, masih diproses");
    }
}