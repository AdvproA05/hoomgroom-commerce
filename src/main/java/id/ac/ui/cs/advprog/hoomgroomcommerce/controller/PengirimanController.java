package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.service.PengirimanService;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Transportation;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pengiriman")
public class PengirimanController {

    @Autowired
    private PengirimanService pengirimanService;

    @PostMapping
    public Pengiriman creatPengiriman(@RequestBody Pengiriman pengiriman) {
        return pengirimanService.createPengiriman(pengiriman);
    }

    @GetMapping
    public List<Pengiriman> getAllPengiriman() {
        return pengirimanService.findAllPengiriman();
    }

    @GetMapping("/{kodeResi}")
    public Pengiriman getPengirimanByKodeResi(@PathVariable String kodeResi){
        return pengirimanService.findByKodeResi(kodeResi);
    }

    @PutMapping("/{kodeResi}/state")
    public CompletableFuture<Pengiriman> updatePengirimanState(@PathVariable String kodeResi, @RequestBody PengirimanState newState){
        return pengirimanService.updateStatusAsync(kodeResi, newState);
    }

    @PutMapping("/{kodeResi}/{transportation}")
    public Pengiriman updatePengirimanTransportation(@PathVariable String kodeResi, @RequestBody Transportation newTransportation) {
        return pengirimanService.updateTransportation(kodeResi, newTransportation);
    }

    @DeleteMapping("/{kodeResi}")
    public void deletePengiriman(@PathVariable String kodeResi) {
        pengirimanService.deletePengiriman(kodeResi);
    }
}