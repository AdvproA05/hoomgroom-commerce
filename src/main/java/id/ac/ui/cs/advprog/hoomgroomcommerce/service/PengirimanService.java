package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.PengirimanState;

public interface PengirimanService {
    void simpanPengiriman(Pengiriman pengiriman);
    PengirimanState getStatus();
    void setStatus(PengirimanState statusPengiriman);
    String getId();
    String getKodeResi();
    void proses(String id);
    void kirim(String id);
    void terima(String id);
    void jenisTransportasi(String id);
    void finalisasiPesanan(String pengirimanId);
}