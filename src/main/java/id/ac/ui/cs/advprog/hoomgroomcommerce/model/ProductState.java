package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;

public interface ProductState {
    boolean checkAvailability();
    String printState();
}