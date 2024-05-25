package id.ac.ui.cs.advprog.hoomgroomcommerce.model;
import lombok.Getter;
@Getter
public class OutOfStockState implements ProductState{
    private String state = "Out Of Stock";
    @Override
    public boolean checkAvailability() {
        return false;
    }

    @Override
    public String printState() {
        return "Product is Out  Of Stock.";
    }
}
