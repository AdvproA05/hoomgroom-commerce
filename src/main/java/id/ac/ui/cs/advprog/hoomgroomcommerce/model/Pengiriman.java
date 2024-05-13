package id.ac.ui.cs.advprog.hoomgroomcommerce.model;



import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
public class Pengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pengirimanId;
    private String kodeResi;

    @Convert(converter = PengirimanStateConverter.class)
    private PengirimanState state;

    public Pengiriman() {
        this.state = new PengirimanDiprosesState();
    }

    public void setState(PengirimanState state) {
        this.state = state;
    }

    public void proses() {
        state.proses(this);
    }

    public void kirim() {
        state.kirim(this);
    }

    public void terima() {
        state.terima(this);
    }

    public String getId() {
        return pengirimanId.toString();
    }

    public void setId(UUID id) {
        this.pengirimanId = id;
    }

    public PengirimanState getState(){
        return state;
    }

    public String getKodeResi() {
        return kodeResi;
    }

    public void setKodeResi(String kodeResi) {
        this.kodeResi = kodeResi;
    }
}
