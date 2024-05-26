package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.enums.Role;
import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void testUserGettersAndSetters() {
        // Create a User object
        User user = new User();
        UUID userId = UUID.randomUUID();
        String fullName = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 5, 15);
        String username = "johndoe";
        String email = "john@example.com";
        String password = "password123";
        Role role = Role.ADMIN;
        double walletBalance = 100.0;

        // Set values using setters
        user.setUserId(userId);
        user.setFullName(fullName);
        user.setDateOfBirth(dateOfBirth);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setWalletBalance(walletBalance);

        // Test getters
        assertEquals(userId, user.getUserId());
        assertEquals(fullName, user.getFullName());
        assertEquals(dateOfBirth, user.getDateOfBirth());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
        assertEquals(walletBalance, user.getWalletBalance());
    }
}
