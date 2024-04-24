package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.PengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pengiriman")
public class PengirimanController {

    @Autowired
    private PengirimanService pengirimanService;

    @PutMapping("/updateStatus/{id}/{newStatus}")
    public void updateStatusPengiriman(@PathVariable("id") String id, @PathVariable("newStatus") StatusPengiriman newStatus) {
        pengirimanService.updateStatusPengiriman(id, newStatus);
    }

    @PutMapping("/jenisTransportasi/{id}")
    public void jenisTransportasi(@PathVariable("id") String id) {
        pengirimanService.jenisTransportasi(id);
    }

    @PutMapping("/finalisasiPesanan/{pengirimanId}")
    public void finalisasiPesanan(@PathVariable("pengirimanId") String pengirimanId) {
        pengirimanService.finalisasiPesanan(pengirimanId);
    }
}


