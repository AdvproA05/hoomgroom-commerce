package id.ac.ui.cs.advprog.hoomgroomcommerce.model;


import java.util.UUID;
import java.util.List;


import id.ac.ui.cs.advprog.hoomgroomcommerce.enums.PengirimanState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "pengiriman")
@Getter@Setter
@NoArgsConstructor
public class Pengiriman {
    
    @Id
    private String kodeResi;

    public void setKodeResi(){
        UUID kodeResiUUID = UUID.randomUUID();
        String kodeResiGenerated = kodeResiUUID.toString();
        this.kodeResi = kodeResiGenerated;
    }

    @Enumerated(EnumType.STRING)
    private PengirimanState state = PengirimanState.MENUNGGU_VERIFIKASI;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportasi_id", referencedColumnName = "id")
    private Transportation transportasi;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pengiriman_product", joinColumns = @JoinColumn(name = "pengiriman_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Pengiriman (String kodeResi){
        this.kodeResi = kodeResi;
    }

    public Pengiriman (List<Product> productList, User user, String kodeResi){
        this.productList = productList;
        this.user = user;
        this.kodeResi = kodeResi;
    }

}
