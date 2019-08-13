package sayner.sandbox.model;

import lombok.*;
import sayner.sandbox.model.enums.RoleEnum;
import sayner.sandbox.model.enums.StateEnum;

import javax.persistence.*;
import java.util.List;

/**
 * Жирненький пользователь
 */
@NoArgsConstructor // mapper'у очень нужен, ну вот никак без него
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    private String email;

    @Column(nullable = false)
    private String hashPassword;

    private String username;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum userRole;

    @Enumerated(value = EnumType.STRING)
    private StateEnum userState;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Token> tokens;

    /**
     * Constructors 
     */

    /**
     * Ленивый конструктор только с основной информацией
     *
     * @param id
     * @param login
     * @param email
     * @param hashPassword
     */
    public User(Long id, String login, String email, String hashPassword) {

        this.id = id;
        this.login = login;
        this.email = email;
        this.hashPassword = hashPassword;
        this.username = "username";
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.userRole = RoleEnum.ROLE_USER;
        this.userState = StateEnum.ACTIVE;
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
}
