package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.model.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Arrays;

public class PengirimanTest {

    @Test
    void testGetPengirimanState() {
        Pengiriman pengiriman = new Pengiriman("ABC123");
        assertEquals(PengirimanState.MENUNGGU_VERIFIKASI, pengiriman.getState());
    }

    @Test
    void testGetTransportation() {
        Transportation transportation = new Transportation("Truck");
        Pengiriman pengiriman = new Pengiriman("ABC123");
        pengiriman.setTransportasi(transportation);
        assertEquals("Truck", pengiriman.getTransportasi().getType());
    }

    @Test
    void testGetTransportationNull() {
        Pengiriman pengiriman = new Pengiriman("ABC123");
        assertNull(pengiriman.getTransportasi());
    }

    @Test
    void testGetKodeResi() {
        Pengiriman pengiriman = new Pengiriman("ABC123");
        assertEquals("ABC123", pengiriman.getKodeResi());
    }

    @Test
    void testDeliveryBuilder(){
        Transportation transportasi = new Transportation("Truck");
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);
        User user = new User();

        Pengiriman pengiriman = new BuilderPengiriman()
                .state(PengirimanState.MENUNGGU_VERIFIKASI)
                .transportasi(transportasi)
                .productList(productList)
                .user(user)
                .build();

        assertNotNull(pengiriman);
        assertEquals(PengirimanState.MENUNGGU_VERIFIKASI, pengiriman.getState());
        assertEquals("Truck", pengiriman.getTransportasi().getType());
        assertEquals(productList, pengiriman.getProductList());
        assertNull(pengiriman.getKodeResi(), "kodeResi should be null initially");

        pengiriman.setKodeResi();

        assertNotNull(pengiriman.getKodeResi(), "kodeResi should be set");
        assertTrue(pengiriman.getKodeResi().startsWith("HG-"), "kodeResi should start with 'HG-'");
    }
}
