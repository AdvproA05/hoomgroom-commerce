package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class ProductStateTest {

    @Test
    public void whenProductIsAvailable_thenAvailabilityShouldBeTrue() {
        Product product = new Product();
        product.setProductState(new AvailableState());
        assertTrue(product.checkAvailability());
        assertEquals("Product is Available.", product.printState());
    }

    @Test
    public void whenProductIsDiscontinued_thenAvailabilityShouldBeFalse() {
        Product product = new Product();
        product.setProductState(new DiscontinuedState());
        assertFalse(product.checkAvailability());
        assertEquals("Product is Discontinued.", product.printState());
    }

    @Test
    public void whenProductIsOutOfStock_thenAvailabilityShouldBeFalse() {
        Product product = new Product();
        product.setProductState(new OutOfStockState());
        assertFalse(product.checkAvailability());
        assertEquals("Product is Out Of Stock.", product.printState());
    }
}
