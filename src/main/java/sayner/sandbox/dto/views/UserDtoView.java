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

    public interface Full extends Id, Name, Address, Email {
    }
}
