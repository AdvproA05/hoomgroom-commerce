package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public class AvailableState implements ProductState{

    @Override
    public boolean checkAvailability() {
        return true;
    }

    @Override
    public String printState() {
        return "Product is Available.";
    }
}
