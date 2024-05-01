import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PengirimanStateTest {
    @Test
    public void testPengirimanState() {
        Pengiriman pengiriman = new Pengiriman();
        PengirimanState PengirimanDiprosesState = Mockito.mock(PengirimanDiprosesState.class);
        PengirimanState PengirimanDikirimState = Mockito.mock(PengirimanDikirimState.class);
        PengirimanState PengirimanDiterimaState = Mockito.mock(PengirimanDiterimaState.class);

        pengiriman.setState(PengirimanDiprosesState);
        pengiriman.proses();
        Mockito.verify(PengirimanDiprosesState).proses(pengiriman);

        pengiriman.setState(PengirimanDikirimState);
        pengiriman.kirim();
        Mockito.verify(PengirimanDikirimState).kirim(pengiriman);

        pengiriman.setState(PengirimanDiterimaState);
        pengiriman.terima();
        Mockito.verify(PengirimanDiterimaState).terima(pengiriman);
    }
}