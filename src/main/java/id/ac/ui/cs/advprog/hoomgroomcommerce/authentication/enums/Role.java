package id.ac.ui.cs.advprog.hoomgroomcommerce.authentication.enums;
import lombok.Getter;

@Getter
public enum Role {
    
    GUEST ("GUEST"),
    PEMBELI ("PEMBELI"),
    ADMIN("ADMIN");

    private final String value;

    private Role(String value) {this.value = value;}

}
