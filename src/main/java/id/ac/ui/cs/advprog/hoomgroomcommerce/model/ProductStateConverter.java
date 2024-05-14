package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductStateConverter implements AttributeConverter<ProductState, String> {

    @Override
    public String convertToDatabaseColumn(ProductState attribute) {
        if (attribute instanceof AvailableState) {
            return "AVAILABLE";
        } else if (attribute instanceof DiscontinuedState) {
            return "DISCONTINUED";
        } else if (attribute instanceof OutOfStockState) {
            return "OUT_OF_STOCK";
        } else {
            throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public ProductState convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "AVAILABLE":
                return new AvailableState();
            case "DISCONTINUED":
                return new DiscontinuedState();
            case "OUT_OF_STOCK":
                return new OutOfStockState();
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}
