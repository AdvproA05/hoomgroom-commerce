import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PengirimanServiceImplTest {

    @Test
    public void testSimpanPengiriman() {
        PengirimanRepository pengirimanRepository = mock(PengirimanRepository.class);
        PengirimanService pengirimanService = new PengirimanServiceImpl();
        Pengiriman pengiriman = new Pengiriman();
        pengirimanService.simpanPengiriman(pengiriman);
        verify(pengirimanRepository).save(pengiriman);
    }
}
