package id.ac.ui.cs.advprog.hoomgroomcommerce.repository;

import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Transportation;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.PengirimanServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PengirimanRepositoryTest {

    @Mock
    private PengirimanRepository pengirimanRepository;

    @InjectMocks
    private PengirimanServiceImpl pengirimanService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAndFind() {
        Pengiriman pengiriman = new Pengiriman("ABC123");
        pengiriman.setState(PengirimanState.MENUNGGU_VERIFIKASI);
        Transportation transportation = new Transportation("Truck");
        pengiriman.setTransportasi(transportation);

        when(pengirimanRepository.save(pengiriman)).thenReturn(pengiriman);

        List<Pengiriman> allPengirimans = new ArrayList<>();
        allPengirimans.add(pengiriman);
        when(pengirimanRepository.findAll()).thenReturn(allPengirimans);

        Pengiriman savedPengiriman = pengirimanService.createPengiriman(pengiriman);
        assertEquals(pengiriman, savedPengiriman);

        Iterable<Pengiriman> pengirimans = pengirimanService.findAllPengiriman();
        Iterator<Pengiriman> pengirimanIterator = pengirimans.iterator();
        assertTrue(pengirimanIterator.hasNext());

        Pengiriman pengirimanDitemukan = pengirimanIterator.next();
        assertEquals(pengiriman.getKodeResi(), pengirimanDitemukan.getKodeResi());
        assertEquals(pengiriman.getState(), pengirimanDitemukan.getState());
        assertEquals(pengiriman.getTransportasi().getType(), pengirimanDitemukan.getTransportasi().getType());
    }

    @Test
    void testFindAllIfEmpty() {
        when(pengirimanRepository.findAll()).thenReturn(new ArrayList<>());
        Iterable<Pengiriman> pengirimans = pengirimanService.findAllPengiriman();
        assertFalse(pengirimans.iterator().hasNext());
        verify(pengirimanRepository, times(1)).findAll();
    }

    @Test
    void testFindAllIfLebihDariSatuPengiriman() {
        
        Pengiriman pengiriman1 = new Pengiriman("ABC123");
        pengiriman1.setState(PengirimanState.MENUNGGU_VERIFIKASI);
        Transportation transportation1 = new Transportation("Truck");
        pengiriman1.setTransportasi(transportation1);

        Pengiriman pengiriman2 = new Pengiriman("DEF456");
        pengiriman2.setState(PengirimanState.DIPROSES);
        Transportation transportation2 = new Transportation("Motor");
        pengiriman2.setTransportasi(transportation2);

        
        List<Pengiriman> allPengirimans = new ArrayList<>();
        allPengirimans.add(pengiriman1);
        allPengirimans.add(pengiriman2);
        when(pengirimanRepository.findAll()).thenReturn(allPengirimans);

        Iterable<Pengiriman> pengirimans = pengirimanService.findAllPengiriman();

        Iterator<Pengiriman> iteratorPengiriman = pengirimans.iterator();
        assertTrue(iteratorPengiriman.hasNext());

        Pengiriman savedPengiriman = iteratorPengiriman.next();
        assertEquals(pengiriman1.getKodeResi(), savedPengiriman.getKodeResi());
        savedPengiriman = iteratorPengiriman.next();
        assertEquals(pengiriman2.getKodeResi(), savedPengiriman.getKodeResi());
        assertFalse(iteratorPengiriman.hasNext());

        verify(pengirimanRepository, times(1)).findAll();
    }

    @Test
    void testFindByKodeResi() {
       
        Pengiriman pengiriman = new Pengiriman("ABC123");
        pengiriman.setState(PengirimanState.MENUNGGU_VERIFIKASI);
        Transportation transportation = new Transportation("Truck");
        pengiriman.setTransportasi(transportation);

        
        when(pengirimanRepository.findByKodeResi("ABC123")).thenReturn(pengiriman);

        
        Pengiriman pengirimanDitemukan = pengirimanService.findByKodeResi("ABC123");

        
        assertNotNull(pengirimanDitemukan);
        assertEquals(pengiriman.getKodeResi(), pengirimanDitemukan.getKodeResi());
        assertEquals(pengiriman.getState(), pengirimanDitemukan.getState());
        assertEquals(pengiriman.getTransportasi().getType(), pengirimanDitemukan.getTransportasi().getType());

        
        verify(pengirimanRepository, times(1)).findByKodeResi("ABC123");
    }

    @Test
    void testFindByKodeResiIfDoesNotExist() {
        
        when(pengirimanRepository.findByKodeResi("nonexistentKodeResi")).thenReturn(null);

        
        Pengiriman foundDelivery = pengirimanService.findByKodeResi("nonexistentKodeResi");

        
        assertNull(foundDelivery);

        verify(pengirimanRepository, times(1)).findByKodeResi("nonexistentKodeResi");
    }
}
