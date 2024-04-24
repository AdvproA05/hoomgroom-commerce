import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PengirimanRepositoryTest {

    @Test
    public void testFindById() {
        PengirimanRepository pengirimanRepository = new PengirimanRepositoryImpl();
        Pengiriman pengiriman = pengirimanRepository.findById("123");
        assertNotNull(pengiriman);
    }
}
