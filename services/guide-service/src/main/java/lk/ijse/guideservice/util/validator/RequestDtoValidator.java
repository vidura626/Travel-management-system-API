package lk.ijse.guideservice.util.validator;

import lk.ijse.guideservice.exception.RequestDtoValidationException;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper
public class RequestDtoValidator {
    @Named("guide")
    public long validate(long userId) throws RequestDtoValidationException {
        if (userId > 0) {
            return userId;
        }
        throw new RequestDtoValidationException("Invalid User Id");
    }
}
