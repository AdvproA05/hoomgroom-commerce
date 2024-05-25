package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.model.User;

import java.util.List;;

public class BuilderPengiriman {

    private PengirimanState state = PengirimanState.MENUNGGU_VERIFIKASI;
    private Transportation transportasi;
    private List<Product> productList;
    private User user;

    public BuilderPengiriman state (PengirimanState state) {
        this.state = state;
        return this;
    }

    public BuilderPengiriman transportasi (Transportation transportasi) {
        this.transportasi = transportasi;
        return this;
    }
    
    public BuilderPengiriman productList (List<Product> productList) {
        this.productList = productList;
        return this;
    }

    public BuilderPengiriman user (User user) {
        this.user = user;
        return this;
    }

    public Pengiriman build () {
        return new Pengiriman(state, transportasi, productList, user);
    }
}
