package id.ac.ui.cs.advprog.hoomgroomcommerce.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;

public interface PengirimanRepository extends CrudRepository<Pengiriman, String> {
    Optional<Pengiriman> findById(String id);
}

