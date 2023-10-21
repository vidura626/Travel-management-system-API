package lk.ijse.guideservice.service;



import lk.ijse.guideservice.dto.RequestDto;
import lk.ijse.guideservice.dto.ResponseDto;
import lk.ijse.guideservice.exception.AlreadyExistsException;
import lk.ijse.guideservice.exception.NotFoundException;

import java.util.List;

public interface GuideService {
    public void registerGuide(RequestDto requestDto) throws AlreadyExistsException;

    public void updateGuide(RequestDto requestDto) throws NotFoundException;

    public void deleteGuide(long guideId) throws NotFoundException;

    public ResponseDto findGuideByID(long guideId) ;


    public List<ResponseDto> findAllGuides();


}
