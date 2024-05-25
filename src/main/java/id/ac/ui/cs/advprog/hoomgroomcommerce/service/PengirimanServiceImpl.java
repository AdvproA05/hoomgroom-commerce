package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.PengirimanRepository;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Transportation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PengirimanServiceImpl implements PengirimanService {

    private final PengirimanRepository pengirimanRepository;

    @Autowired
    public PengirimanServiceImpl(PengirimanRepository pengirimanRepository) {
        this.pengirimanRepository = pengirimanRepository;
    }

    @Override
    public Pengiriman createPengiriman(Pengiriman pengiriman) {
        return pengirimanRepository.save(pengiriman);
    }

    @Override
    public List<Pengiriman> findAllPengiriman() {
        return pengirimanRepository.findAll();
    }


    @Override
    public Pengiriman findByKodeResi(String kodeResi) {
        return pengirimanRepository.findByKodeResi(kodeResi);
    }

    @Override
    @Async 
    public CompletableFuture<Pengiriman> updateStatusAsync(String kodeResi, PengirimanState newState) {
        Pengiriman pengiriman = pengirimanRepository.findByKodeResi(kodeResi);
        if (pengiriman != null) {
            pengiriman.setState(newState);
            pengirimanRepository.save(pengiriman);
        }
        return CompletableFuture.completedFuture(pengiriman);
    }

    @Override
    public Pengiriman updateTransportation(String kodeResi, Transportation newTransportation) {
        Pengiriman pengiriman = pengirimanRepository.findByKodeResi(kodeResi);
        if (pengiriman != null && pengiriman.getState() == PengirimanState.DIPROSES) {
            pengiriman.setTransportasi(newTransportation);
            return pengirimanRepository.save(pengiriman);
        }
        return null;
    }

    @Override
    public Pengiriman deletePengiriman(String kodeResi) {
        if (pengirimanRepository.deleteByKodeResi(kodeResi)) {
            return new Pengiriman(kodeResi);
        }
        return null;
    }
}
