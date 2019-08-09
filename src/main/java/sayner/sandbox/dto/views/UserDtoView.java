package sayner.sandbox.dto.views;

public class UserDtoView {

    public interface Id {
    }

    public interface Login {
    }

    public interface Email {
    }

    public interface Password {
    }

    public interface Username {
    }

    public interface AccountNonExpired {
    }

    public interface AccountNonLocked {
    }

    public interface CredentialsNonExpired {
    }

    public interface Enabled {
    }

    public interface UserRole {
    }

    public interface UserState {
    }

    public interface Basic extends Id, Login, Email, Password {
    }

    public interface Full extends Id, Login, Email, Password, Username, AccountNonExpired, AccountNonLocked, CredentialsNonExpired, Enabled, UserRole, UserState {
    }
}
