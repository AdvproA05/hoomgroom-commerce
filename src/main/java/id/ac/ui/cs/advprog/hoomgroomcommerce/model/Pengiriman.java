public class Pengiriman {
    private PengirimanState state;
    private String id;
    private String kodeResi;

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

    public String getKodeResi() {
        return kodeResi;
    }

    public void setKodeResi(String kodeResi) {
        this.kodeResi = kodeResi;
    }
}
