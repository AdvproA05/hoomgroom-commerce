package id.ac.ui.cs.advprog.hoomgroomcommerce.enums;

public enum PengirimanState {
    MENUNGGU_VERIFIKASI ("MENUNGGU_VERIFIKASI"),
    DIPROSES ("DIPROSES"),
    DIKIRIM ("DIKIRIM"),
    TIBA ("TIBA"),
    SELESAI ("SELESAI");

    private final String state;

    private PengirimanState(String status){
        this.state = status;
    }
}
