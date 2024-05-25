package id.ac.ui.cs.advprog.hoomgroomcommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;

@Repository
public interface PengirimanRepository extends JpaRepository<Pengiriman, String> {
    Pengiriman findByKodeResi(String kodeResi);
    boolean deleteByKodeResi(String kodeResi);
}

