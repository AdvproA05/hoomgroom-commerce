package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.PengirimanRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.CompletableFuture;

@ExtendWith(MockitoExtension.class)
public class PengirimanServiceTest {

    @InjectMocks
    private PengirimanServiceImpl pengirimanServiceImpl;

    @Mock
    private PengirimanRepository pengirimanRepository;


    @Test
    void testCreateDelivery() {
        Pengiriman pengiriman = new Pengiriman("ABC123");
        when(pengirimanRepository.save(pengiriman)).thenReturn(pengiriman);

        Pengiriman createdPengiriman = pengirimanServiceImpl.createPengiriman(pengiriman);
        assertNotNull(createdPengiriman);
        assertEquals("ABC123", createdPengiriman.getKodeResi());
    }

    @Test
    void testUpdateStatusAsync() throws Exception {
        String kodeResi = "ABC123";
        Pengiriman pengiriman = new Pengiriman(kodeResi);
        pengiriman.setState(PengirimanState.MENUNGGU_VERIFIKASI);

        // Mock the asynchronous behavior
        when(pengirimanRepository.findByKodeResi(kodeResi)).thenReturn(pengiriman);
        when(pengirimanRepository.save(pengiriman)).thenReturn(pengiriman);

        // Call the asynchronous method
        CompletableFuture<Pengiriman> futurePengiriman = pengirimanServiceImpl.updateStatusAsync(kodeResi, PengirimanState.DIPROSES);

        // Wait for the asynchronous computation to complete
        Pengiriman updatedPengiriman = futurePengiriman.get();

        // Assert the result
        assertEquals(PengirimanState.DIPROSES, updatedPengiriman.getState());
    }


    @Test
    void testFindByKodeResi() {
        Pengiriman pengiriman = new Pengiriman("ABC123");
        when(pengirimanRepository.findByKodeResi("ABC123")).thenReturn(pengiriman);

        Pengiriman pengirimanDitemukan = pengirimanServiceImpl.findByKodeResi("ABC123");

        assertNotNull(pengirimanDitemukan);
        assertEquals("ABC123", pengirimanDitemukan.getKodeResi());
    }

    @Test
    void testFindByKodeResiNonExisting() {
        when(pengirimanRepository.findByKodeResi("NONEXISTING")).thenReturn(null);

        Pengiriman pengirimanDitemukan = pengirimanServiceImpl.findByKodeResi("NONEXISTING");

        assertNull(pengirimanDitemukan);
    }

    @Test
    void testDeleteDelivery() {
        Pengiriman pengiriman = new Pengiriman("ABC123");
        when(pengirimanRepository.deleteByKodeResi("ABC123")).thenReturn(true);

        Pengiriman deletedPengiriman = pengirimanServiceImpl.deletePengiriman("ABC123");

        assertNotNull(deletedPengiriman);
        assertEquals("ABC123", deletedPengiriman.getKodeResi());
    }

}
