package lk.ijse.userservice.util.validation.impl;

import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.util.validation.Validation;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;

@Component
public class ValidationImpl implements Validation {
    private final Map<ValidationType, Pattern> validationMap = new HashMap<>();

    ValidationImpl() {
        validationMap.put(ValidationType.EMAIL, Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"));
        validationMap.put(ValidationType.PASSWORD, Pattern.compile(""));
        validationMap.put(ValidationType.CONTACT, Pattern.compile(""));
        validationMap.put(ValidationType.USERNAME, Pattern.compile(""));
        validationMap.put(ValidationType.NAME, Pattern.compile(""));
        validationMap.put(ValidationType.REMARKS, Pattern.compile(""));
        validationMap.put(ValidationType.GENDER, Pattern.compile("^(male|female)$"));
        validationMap.put(ValidationType.ROLE, Pattern.compile("^(user|admin)$"));
        validationMap.put(ValidationType.DATE, Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$"));
        validationMap.put(ValidationType.NIC, Pattern.compile(""));
        validationMap.put(ValidationType.PASSPORT, Pattern.compile(""));
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
//        List<String> errors = new ArrayList<>();
//        if (!requestDto.getUsername()
//                .matches(validationMap.get(ValidationType.USERNAME).pattern()))
//            errors.add(ValidationType.USERNAME.toString());
//        if (!requestDto.getPassword()
//                .matches(validationMap.get(ValidationType.PASSWORD).pattern()))
//            errors.add(ValidationType.PASSWORD.toString());
//        if (!requestDto.getContact()
//                .matches(validationMap.get(ValidationType.CONTACT).pattern()))
//            errors.add(ValidationType.CONTACT.toString());
//        if (!requestDto.getEmail()
//                .matches(validationMap.get(ValidationType.EMAIL).pattern()))
//            errors.add(ValidationType.EMAIL.toString());
//        if (!requestDto.getAddress()
//                .matches(validationMap.get(ValidationType.ADDRESS).pattern()))
//            errors.add(ValidationType.ADDRESS.toString());
//        if (!requestDto.getName()
//                .matches(validationMap.get(ValidationType.NAME).pattern()))
//            errors.add(ValidationType.NAME.toString());
//        if (!requestDto.getRemarks()
//                .matches(validationMap.get(ValidationType.REMARKS).pattern()))
//            errors.add(ValidationType.REMARKS.toString());
//        if (!requestDto.getGender().toString().toLowerCase()
//                .matches(validationMap.get(ValidationType.GENDER).pattern()))
//            errors.add(ValidationType.GENDER.toString());
//        if (!requestDto.getRole().toString().toLowerCase()
//                .matches(validationMap.get(ValidationType.ROLE).pattern()))
//            errors.add(ValidationType.ROLE.toString());
//        if (!requestDto.getDob().toString().
//                matches(validationMap.get(ValidationType.DATE).pattern()))
//            errors.add(ValidationType.DATE.toString());
//        if (!requestDto.getNicOrPassport().getId()
//                .matches(validationMap.get(ValidationType.NIC).pattern()) ||
//                requestDto.getNicOrPassport().getId()
//                        .matches(validationMap.get(ValidationType.PASSPORT).pattern()))
//            errors.add("NicOrPassport");
//
//        if (errors.isEmpty()) return true;
//        String errorList = String.join(", ", errors);
//        throw new ValidationFailedException("Invalid request data : " + errorList);
        return true;
    }
}
