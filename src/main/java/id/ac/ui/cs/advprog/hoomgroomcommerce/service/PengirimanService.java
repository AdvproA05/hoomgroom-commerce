package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Transportation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PengirimanService {
    Pengiriman createPengiriman(Pengiriman pengiriman);
    List<Pengiriman> findAllPengiriman();
    Pengiriman findByKodeResi(String kodeResi);
    CompletableFuture<Pengiriman> updateStatusAsync(String kodeResi, PengirimanState newState);
    Pengiriman updateTransportation(String kodeResi, Transportation newTransportation);
    Pengiriman deletePengiriman(String KodeResi);

}