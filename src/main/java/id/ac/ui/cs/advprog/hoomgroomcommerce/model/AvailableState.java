package id.ac.ui.cs.advprog.hoomgroomcommerce.model;
import lombok.Getter;
@Getter
public class AvailableState implements ProductState{
    private String state = "Available";

    @Override
    public boolean checkAvailability() {
        return true;
    }

    @Override
    public String printState() {
        return "Product is Available.";
    }
}
