import java.util.UUID;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.StatusPengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.PengirimanRepository;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.PengirimanRepository; // Add this import statement

@Service
public class PengirimanServiceImpl implements PengirimanService {

    private final PengirimanRepository pengirimanRepo;

    public PengirimanServiceImpl(PengirimanRepository pengirimanRepo) {
        this.pengirimanRepo = pengirimanRepo;
    }

    @Override
    public void simpanPengiriman(Pengiriman pengiriman) {
        pengirimanRepo.save(pengiriman);
    }

    // Existing code...

    @Override
    public String getKodeResi() {
        return generateKodeResi(); 
    }

    private String generateKodeResi() {
        StringBuilder kodeResiBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char randomChar = (char) ('A' + Math.random() * ('Z' - 'A' + 1)); 
            kodeResiBuilder.append(randomChar);
        }
        return kodeResiBuilder.toString();
    }
}