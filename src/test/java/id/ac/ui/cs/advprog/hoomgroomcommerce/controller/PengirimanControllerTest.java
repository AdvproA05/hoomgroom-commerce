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
}
