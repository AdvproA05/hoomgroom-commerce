public class PengirimanDiterimaState implements PengirimanState {
    @Override
    public void proses(Pengiriman pengiriman) {
        System.out.println("Tidak dapat memproses pengiriman yang sudah diterima.");
    }

    @Override
    public void kirim(Pengiriman pengiriman) {
        System.out.println("Tidak dapat mengirim ulang pengiriman yang sudah diterima.");
    }

    @Override
    public void terima(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah diterima.");
    }
}
