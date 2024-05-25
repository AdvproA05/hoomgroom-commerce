package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AvailableState.class, name = "AvailableState"),
        @JsonSubTypes.Type(value = DiscontinuedState.class, name = "DiscontinuedState"),
        @JsonSubTypes.Type(value = OutOfStockState.class, name = "OutOfStockState")
})

public interface ProductState {
    boolean checkAvailability();
    String printState();
}