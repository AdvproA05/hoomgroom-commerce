package id.ac.ui.cs.advprog.hoomgroomcommerce.model;
import lombok.Getter;
public class OutOfStockState implements ProductState{
    @Getter
    private String state = "Out Of Stock";
    @Override
    public boolean checkAvailability() {
        return false;
    }

    @Override
    public String printState() {
        return "Product is Out Of Stock.";
    }
}
