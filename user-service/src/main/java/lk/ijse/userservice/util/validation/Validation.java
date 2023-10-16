package lk.ijse.userservice.util.validation;

import lk.ijse.userservice.dto.request.RequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Validation {

    public List<ValidationType> validate(List<String> values, ValidationType... validationType);
    public boolean validate(RequestDto requestDto);

    public enum ValidationType {
        ALL, CONTACT, EMAIL, PASSWORD, USERNAME, NIC,PASSPORT, ADDRESS, NAME, REMARKS, GENDER, ROLE,DATE
    }
}
