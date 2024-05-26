package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportationTest {

    @Test
    void testNoArgsConstructor() {
        Transportation transportation = new Transportation();
        assertNotNull(transportation);
    }

    @Test
    void testConstructorWithType() {
        Transportation transportation = new Transportation("Truck");
        assertNotNull(transportation);
        assertEquals("Truck", transportation.getType());
    }

    @Test
    void testSetAndGetId() {
        Transportation transportation = new Transportation();
        transportation.setId(1L);
        assertEquals(1L, transportation.getId());
    }

    @Test
    void testSetAndGetType() {
        Transportation transportation = new Transportation();
        transportation.setType("Van");
        assertEquals("Van", transportation.getType());
    }
}
