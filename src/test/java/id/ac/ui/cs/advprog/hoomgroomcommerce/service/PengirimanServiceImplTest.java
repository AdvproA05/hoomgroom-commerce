package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.PengirimanRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PengirimanServiceImplTest {

    @Test
    public void testSimpanPengiriman() {
        PengirimanRepository pengirimanRepository = mock(PengirimanRepository.class);
        PengirimanService pengirimanService = new PengirimanServiceImpl(pengirimanRepository);
        Pengiriman pengiriman = new Pengiriman();
        pengirimanService.simpanPengiriman(pengiriman);
        verify(pengirimanRepository).save(pengiriman);
    }
}