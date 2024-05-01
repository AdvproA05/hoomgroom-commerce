package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public interface PengirimanState {
    void proses(Pengiriman pengiriman);
    void kirim(Pengiriman pengiriman);
    void terima(Pengiriman pengiriman);
}