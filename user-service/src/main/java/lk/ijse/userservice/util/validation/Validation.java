package lk.ijse.userservice.util.validation;

import java.util.List;

public interface Validation {

    public void validate(List<String> values, ValidationType... validationType);

    public enum ValidationType {
        ALL, CONTACT, EMAIL, PASSWORD, USERNAME
    }
}
