package sayner.sandbox.dto.views;

public class UserDtoView {

    public interface Id {
    }

    public interface Name {
    }

    public interface Address {
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

    public interface UserRoles {
    }

    public interface Basic extends Id, Name, Address, Email {
    }

    public interface Full extends Id, Name, Address, Email, Password, Username, AccountNonExpired, AccountNonLocked, CredentialsNonExpired, Enabled, UserRoles {
    }
}
