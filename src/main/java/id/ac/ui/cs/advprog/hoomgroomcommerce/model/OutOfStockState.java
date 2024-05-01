package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public class OutOfStockState implements ProductState{
    @Override
    public boolean checkAvailability() {
        return false;
    }

    @Override
    public String printState() {
        return "Product is Out Of Stock.";
    }
}
