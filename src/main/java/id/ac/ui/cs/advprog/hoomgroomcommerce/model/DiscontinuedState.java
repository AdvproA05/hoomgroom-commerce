package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public class DiscontinuedState implements ProductState{

    @Override
    public boolean checkAvailability() {
        return false;
    }

    @Override
    public String printState() {
        return "Product is Discontinued.";
    }
}
