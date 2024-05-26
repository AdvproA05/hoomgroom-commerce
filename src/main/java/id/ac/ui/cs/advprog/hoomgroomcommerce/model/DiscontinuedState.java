package id.ac.ui.cs.advprog.hoomgroomcommerce.model;
import lombok.Getter;
@Getter
public class DiscontinuedState implements ProductState{
    private String state = "Discontinued";
    @Override
    public boolean checkAvailability() {
        return false;
    }

    @Override
    public String printState() {
        return "Product is Discontinued.";
    }
}
