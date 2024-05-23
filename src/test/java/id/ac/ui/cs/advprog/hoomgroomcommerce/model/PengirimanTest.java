package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}
