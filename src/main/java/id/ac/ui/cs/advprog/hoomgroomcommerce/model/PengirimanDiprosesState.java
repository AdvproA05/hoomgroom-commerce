public class PengirimanDiprosesState implements PengirimanState {
    @Override
    public void proses(Pengiriman pengiriman) {
        System.out.println("Pengiriman sudah dalam proses.");
    }

    @Override
    public void kirim(Pengiriman pengiriman) {
        pengiriman.setState(new PengirimanDikirimState());
        System.out.println("Pengiriman telah dikirim.");
    }

    @Override
    public void terima(Pengiriman pengiriman) {
        System.out.println("Tidak dapat menerima pengiriman yang belum dikirim.");
    }
}
