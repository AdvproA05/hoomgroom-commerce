import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.enums.Role;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    @Test
    void testRoleValues() {
        assertEquals("GUEST", Role.GUEST.getValue());
        assertEquals("PEMBELI", Role.PEMBELI.getValue());
        assertEquals("ADMIN", Role.ADMIN.getValue());
    }

    @Test
    void testRoleEquality() {
        assertEquals(Role.GUEST, Role.GUEST);
        assertEquals(Role.PEMBELI, Role.PEMBELI);
        assertEquals(Role.ADMIN, Role.ADMIN);
    }

    @Test
    void testRoleInequality() {
        // Test inequality between different roles
        assertNotEquals(Role.GUEST, Role.PEMBELI);
        assertNotEquals(Role.GUEST, Role.ADMIN);
        assertNotEquals(Role.PEMBELI, Role.ADMIN);

        // Test inequality between role and null
        assertNotEquals(Role.GUEST, null);
        assertNotEquals(Role.PEMBELI, null);
        assertNotEquals(Role.ADMIN, null);

        // Test inequality between different types
        assertNotEquals(Role.GUEST, "GUEST");
        assertNotEquals(Role.PEMBELI, "PEMBELI");
        assertNotEquals(Role.ADMIN, "ADMIN");
    }

    @Test
    void testRoleHashCode() {
        assertEquals(Role.GUEST.hashCode(), Role.GUEST.hashCode());
        assertEquals(Role.PEMBELI.hashCode(), Role.PEMBELI.hashCode());
        assertEquals(Role.ADMIN.hashCode(), Role.ADMIN.hashCode());

        // Hash code of different roles should not be equal
        assertNotEquals(Role.GUEST.hashCode(), Role.PEMBELI.hashCode());
        assertNotEquals(Role.GUEST.hashCode(), Role.ADMIN.hashCode());
        assertNotEquals(Role.PEMBELI.hashCode(), Role.ADMIN.hashCode());
    }
}
