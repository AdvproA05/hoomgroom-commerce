package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.AvailableState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.DiscontinuedState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.OutOfStockState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.ProductStateConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductStateConverterTest {

    private final ProductStateConverter converter = new ProductStateConverter();

    @Test
    void testConvertToDatabaseColumn() {
        assertEquals("AVAILABLE", converter.convertToDatabaseColumn(new AvailableState()));
        assertEquals("DISCONTINUED", converter.convertToDatabaseColumn(new DiscontinuedState()));
        assertEquals("OUT_OF_STOCK", converter.convertToDatabaseColumn(new OutOfStockState()));
    }

    @Test
    void testConvertToEntityAttribute() {
        assertEquals(AvailableState.class, converter.convertToEntityAttribute("AVAILABLE").getClass());
        assertEquals(DiscontinuedState.class, converter.convertToEntityAttribute("DISCONTINUED").getClass());
        assertEquals(OutOfStockState.class, converter.convertToEntityAttribute("OUT_OF_STOCK").getClass());
    }

    @Test
    void testUnknownDatabaseValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("INVALID_STATE"));
    }
}
