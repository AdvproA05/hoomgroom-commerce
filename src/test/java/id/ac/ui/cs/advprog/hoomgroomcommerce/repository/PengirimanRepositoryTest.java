package id.ac.ui.cs.advprog.hoomgroomcommerce.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class PengirimanRepositoryTest {

    @Test
    public void testFindById() {
        PengirimanRepository pengirimanRepository = Mockito.mock(PengirimanRepository.class);
        Optional<Pengiriman> pengiriman = Optional.of(new Pengiriman());
        when(pengirimanRepository.findById("123")).thenReturn(pengiriman);

        Optional<Pengiriman> result = pengirimanRepository.findById("123");
        assertNotNull(result);
    }
}