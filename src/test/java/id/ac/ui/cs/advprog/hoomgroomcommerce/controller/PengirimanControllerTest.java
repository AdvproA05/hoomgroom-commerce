package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Transportation;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.PengirimanService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PengirimanControllerTest {

    @Mock
    private PengirimanService pengirimanService;

    @InjectMocks
    private PengirimanController pengirimanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePengiriman() {
        Pengiriman pengiriman = new Pengiriman("123");
        when(pengirimanService.createPengiriman(pengiriman)).thenReturn(pengiriman);

        Pengiriman createdPengiriman = pengirimanController.creatPengiriman(pengiriman);

        assertEquals(pengiriman, createdPengiriman);
        verify(pengirimanService, times(1)).createPengiriman(pengiriman);
    }

    @Test
    void testGetAllPengiriman() {
        Pengiriman pengiriman1 = new Pengiriman("123");
        Pengiriman pengiriman2 = new Pengiriman("456");
        List<Pengiriman> pengirimans = Arrays.asList(pengiriman1, pengiriman2);
        when(pengirimanService.findAllPengiriman()).thenReturn(pengirimans);

        List<Pengiriman> retrievedPengirimans = pengirimanController.getAllPengiriman();

        assertEquals(pengirimans, retrievedPengirimans);
        verify(pengirimanService, times(1)).findAllPengiriman();
    }

    @Test
    void testGetPengirimanByKodeResi() {
        Pengiriman pengiriman = new Pengiriman("123");
        when(pengirimanService.findByKodeResi("123")).thenReturn(pengiriman);

        Pengiriman retrievedPengiriman = pengirimanController.getPengirimanByKodeResi("123");

        assertEquals(pengiriman, retrievedPengiriman);
        verify(pengirimanService, times(1)).findByKodeResi("123");
    }

    @Test
    void testGetPengirimanByKodeREsiNotFound() {
        when(pengirimanService.findByKodeResi("123")).thenReturn(null);

        Pengiriman retrievedPengiriman = pengirimanController.getPengirimanByKodeResi("123");

        assertNull(retrievedPengiriman);
        verify(pengirimanService, times(1)).findByKodeResi("123");
    }

    @Test
    void testUpdatePengirimanState() throws Exception{
        Pengiriman pengiriman = new Pengiriman("123");
        pengiriman.setState(PengirimanState.MENUNGGU_VERIFIKASI);

        when(pengirimanService.updateStatusAsync("123", PengirimanState.DIPROSES))
                .thenReturn(CompletableFuture.completedFuture(pengiriman));

        CompletableFuture<Pengiriman> futurePengiriman = pengirimanController.updatePengirimanState("123", PengirimanState.DIPROSES);
        Pengiriman updatedPengiriman = futurePengiriman.get();

        assertEquals(PengirimanState.MENUNGGU_VERIFIKASI, updatedPengiriman.getState());
        verify(pengirimanService, times(1)).updateStatusAsync("123", PengirimanState.DIPROSES);
    }

    @Test
    void testUpdatePengirimanTransportation() {
        Transportation transportasi = new Transportation("Truck");
        Pengiriman pengiriman = new Pengiriman("123");
        pengiriman.setTransportasi(transportasi);

        when(pengirimanService.updateTransportation("123", transportasi)).thenReturn(pengiriman);

        Pengiriman updatedPengiriman = pengirimanController.updatePengirimanTransportation("123", transportasi);

        assertEquals(transportasi, updatedPengiriman.getTransportasi());
        verify(pengirimanService, times(1)).updateTransportation("123", transportasi);
    }

    @Test
    void testDeletePengiriman() {
        Pengiriman pengiriman = new Pengiriman("123");
        when(pengirimanService.deletePengiriman("123")).thenReturn(pengiriman);

        pengirimanController.deletePengiriman("123");

        verify(pengirimanService, times(1)).deletePengiriman("123");
    }
}

