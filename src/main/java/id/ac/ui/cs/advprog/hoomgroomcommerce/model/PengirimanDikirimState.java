public class PengirimanDikirimState implements PengirimanState {
    @Override
    public void proses(Pengiriman pengiriman) {
        System.out.println("Tidak dapat memproses pengiriman yang sudah dikirim.");
    }

    @Override
    public void kirim(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah dalam proses pengiriman.");
    }

    @Override
    public void terima(Pengiriman pengiriman) {
        pengiriman.setState(new PengirimanDiterimaState());
        System.out.println("Pengiriman telah diterima.");
    }
}
