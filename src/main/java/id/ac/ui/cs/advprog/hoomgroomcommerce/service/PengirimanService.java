package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;

public interface PengirimanService {
    void simpanPengiriman(Pengiriman pengiriman);
    StatusPengiriman getStatus();
    void setStatus(StatusPengiriman statusPengiriman);
    String getId();
    String getKodeResi();
}

