package sayner.sandbox.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Жирненький пользователь
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    private String id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String username;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private List<UserRole> userRoles;

    /**
     * Constructors 
     */

    /**
     * Ленивый конструктор только с основной информацией
     *
     * @param id
     * @param name
     * @param address
     * @param email
     */
    public User(String id, String name, String address, String email) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = "password";
        this.username = "username";
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.userRoles = new ArrayList<>();
        this.userRoles.add(new UserRole("GODLiKE"));
    }

    /**
     * Some logic methods
     */

    public Boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public Boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public Boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }
}
