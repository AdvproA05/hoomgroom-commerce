import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PengirimanTest {
    @Test
    public void testPengirimanState() {
        Pengiriman pengiriman = new Pengiriman();
        assertEquals("Pengiriman sudah dalam proses.", pengiriman.proses());

        pengiriman.kirim();
        assertEquals("Pengiriman telah dikirim.", pengiriman.kirim());

        pengiriman.terima();
        assertEquals("Pengiriman telah diterima.", pengiriman.terima());
    }
}
