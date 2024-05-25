package id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.model;

import id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.enums.Role;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class User {

    private String fullName;
    private LocalDate dateOfBirth;
    private String username;
    private String email;
    private String password;
    private Role role;
    private double walletBalance;
    
}
