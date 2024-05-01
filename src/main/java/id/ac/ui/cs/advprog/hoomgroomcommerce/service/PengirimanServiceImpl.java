package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.PengirimanRepository;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.PengirimanDiprosesState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.PengirimanDikirimState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.PengirimanDiterimaState;
import org.springframework.stereotype.Service;

@Service
public class PengirimanServiceImpl implements PengirimanService {
    private Pengiriman pengiriman;

    public PengirimanServiceImpl(PengirimanRepository pengirimanRepository) {
        this.pengiriman = new Pengiriman();
    }

    @Override
    public void simpanPengiriman(Pengiriman pengiriman) {
        this.pengiriman = pengiriman;
    }

    @Override
    public PengirimanState getStatus() {
        return pengiriman.getState();
    }

    @Override
    public void setStatus(PengirimanState statusPengiriman) {
        pengiriman.setState(statusPengiriman);
    }

    @Override
    public String getId() {
        return pengiriman.getId();
    }

    @Override
    public String getKodeResi() {
        return pengiriman.getKodeResi();
    }

    @Override
    public void proses(String id) {
        pengiriman.setState(new PengirimanDiprosesState());
    }

    @Override
    public void kirim(String id) {
        pengiriman.setState(new PengirimanDikirimState());
    }

    @Override
    public void terima(String id) {
        pengiriman.setState(new PengirimanDiterimaState());
    }

    @Override
    public void jenisTransportasi(String id) {
        // implementasi detail
    }

    @Override
    public void finalisasiPesanan(String pengirimanId) {
        // implementasi detail
    }
}