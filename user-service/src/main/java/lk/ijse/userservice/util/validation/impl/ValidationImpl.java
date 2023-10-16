package lk.ijse.userservice.util.validation.impl;

import lk.ijse.userservice.dto.request.RequestDto;
import lk.ijse.userservice.util.validation.Validation;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;

@Component
public class ValidationImpl implements Validation {
    private final Map<ValidationType, Pattern> validationMap = new HashMap<>();

    ValidationImpl() {
        validationMap.put(ValidationType.EMAIL, Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"));
        validationMap.put(ValidationType.PASSWORD, Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"));
        validationMap.put(ValidationType.CONTACT, Pattern.compile("^(?:7|0|(?:\\+94)|(?:94))[0-9]{9,10}$"));
        validationMap.put(ValidationType.USERNAME, Pattern.compile("^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$"));
        validationMap.put(ValidationType.NAME, Pattern.compile("^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)$"));
        validationMap.put(ValidationType.REMARKS, Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"));
        validationMap.put(ValidationType.GENDER, Pattern.compile("^(male|female)$"));
        validationMap.put(ValidationType.ROLE, Pattern.compile("^(user|admin)$"));
        validationMap.put(ValidationType.DATE, Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$"));
        validationMap.put(ValidationType.NIC, Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$"));
        validationMap.put(ValidationType.PASSPORT, Pattern.compile("^[A-Z][0-9]{8}$"));
        validationMap.put(ValidationType.ADDRESS, Pattern.compile("^[#.0-9a-zA-Z\\s,-]+$"));
    }

    @Override
    public List<ValidationType> validate(List<String> values, ValidationType... validationType) {

        if (validationType.length == 0)
            throw new IllegalArgumentException("No validation types are found");
        if (values.size() != validationType.length) throw new IllegalArgumentException("Invalid number of values");
        if (Arrays.stream(validationType).toList().contains(ValidationType.ALL))
            throw new IllegalArgumentException("Invalid validation type ALL for this method.Please use public boolean validate(RequestDto requestDto) method");

        List<ValidationType> errors = new ArrayList<>();
        for (int i = 0; i < validationType.length; i++) {
            if (validationMap.get(validationType[i]).matcher(values.get(i)).matches()) continue;
            errors.add(validationType[i]);
        }
        return errors;
    }

    @Override
    public boolean validate(RequestDto requestDto) {
        if (requestDto.getUsername()
                .matches(validationMap.get(ValidationType.USERNAME).pattern()))
            if (requestDto.getPassword()
                    .matches(validationMap.get(ValidationType.PASSWORD).pattern()))
                if (requestDto.getContact()
                        .matches(validationMap.get(ValidationType.CONTACT).pattern()))
                    if (requestDto.getEmail()
                            .matches(validationMap.get(ValidationType.EMAIL).pattern()))
                        if (requestDto.getAddress()
                                .matches(validationMap.get(ValidationType.ADDRESS).pattern()))
                            if (requestDto.getName()
                                    .matches(validationMap.get(ValidationType.NAME).pattern()))
                                if (requestDto.getRemarks()
                                        .matches(validationMap.get(ValidationType.REMARKS).pattern()))
                                    if (requestDto.getGender().toString().toLowerCase()
                                            .matches(validationMap.get(ValidationType.GENDER).pattern()))
                                        if (requestDto.getRole().toString().toLowerCase()
                                                .matches(validationMap.get(ValidationType.ROLE).pattern()))
                                            if (requestDto.getDob().toString().
                                                    matches(validationMap.get(ValidationType.DATE).pattern()))
                                                if (requestDto.getNicOrPassport().getId()
                                                        .matches(validationMap.get(ValidationType.NIC).pattern()) ||
                                                        requestDto.getNicOrPassport().getId()
                                                                .matches(validationMap.get(ValidationType.PASSPORT).pattern()))
                                                    return true;
        return false;
    }
}
