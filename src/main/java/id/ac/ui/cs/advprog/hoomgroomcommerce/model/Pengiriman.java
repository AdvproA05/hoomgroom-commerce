package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

public class Pengiriman {
    private String id;
    private String kodeResi;
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
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
