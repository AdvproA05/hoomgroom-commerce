import org.springframework.data.repository.CrudRepository;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;

public interface PengirimanRepository extends CrudRepository<Pengiriman, String> {
    Pengiriman findById(String id);
}

