import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PengirimanServiceImpl implements PengirimanService {

    private StatusPengiriman statusPengiriman;

    @Autowired
    private PengirimanRepository pengirimanRepo;

    @Override
    public void simpanPengiriman(Pengiriman pengiriman) {
        pengirimanRepo.save(pengiriman);
    }

    @Override
    public StatusPengiriman getStatus() {
        return statusPengiriman;
    }

    @Override
    public void setStatus(StatusPengiriman statusPengiriman) {
        this.statusPengiriman = statusPengiriman;
    }

    @Override
    public String getId() {
        return UUID.randomUUID().toString(); 
    }

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
