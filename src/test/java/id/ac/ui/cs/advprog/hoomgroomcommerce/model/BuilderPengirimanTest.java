package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.model.User;
import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuilderPengirimanTest {

    @Test
    void testDefaultBuild() {
        Pengiriman pengiriman = new BuilderPengiriman().build();
        
        assertNotNull(pengiriman);
        assertEquals(PengirimanState.MENUNGGU_VERIFIKASI, pengiriman.getState());
        assertNull(pengiriman.getTransportasi());
        assertNull(pengiriman.getProductList());
        assertNull(pengiriman.getUser());
    }

    @Test
    void testBuildWithStatus() {
        Pengiriman pengiriman = new BuilderPengiriman()
                .state(PengirimanState.DIKIRIM)
                .build();
        
        assertNotNull(pengiriman);
        assertEquals(PengirimanState.DIKIRIM, pengiriman.getState());
    }

    @Test
    void testBuildWithTransportation() {
        Transportation transportation = new Transportation("Truck");
        
        Pengiriman pengiriman = new BuilderPengiriman()
                .transportasi(transportation)
                .build();
        
        assertNotNull(pengiriman);
        assertEquals(transportation, pengiriman.getTransportasi());
        assertEquals("Truck", pengiriman.getTransportasi().getType());
    }

    @Test
    void testBuildWithFurnitureList() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);
        
        Pengiriman pengiriman = new BuilderPengiriman()
                .productList(productList)
                .build();
        
        assertNotNull(pengiriman);
        assertEquals(productList, pengiriman.getProductList());
    }

    @Test
    void testBuildWithUser() {
        User user = new User();
        
        Pengiriman pengiriman = new BuilderPengiriman()
                .user(user)
                .build();
        
        assertNotNull(pengiriman);
        assertEquals(user, pengiriman.getUser());
    }

    @Test
    void testFullBuild() {
        PengirimanState state = PengirimanState.DIKIRIM;
        Transportation transportation = new Transportation("Truck");
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);
        User user = new User();

        Pengiriman pengiriman = new BuilderPengiriman()
                .state(state)
                .transportasi(transportation)
                .productList(productList)
                .user(user)
                .build();
        
        assertNotNull(pengiriman);
        assertEquals(state, pengiriman.getState());
        assertEquals(transportation, pengiriman.getTransportasi());
        assertEquals(productList, pengiriman.getProductList());
        assertEquals(user, pengiriman.getUser());
    }
}
