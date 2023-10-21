package lk.ijse.guideservice.service.impl;


import lk.ijse.guideservice.dto.RequestDto;
import lk.ijse.guideservice.dto.ResponseDto;
import lk.ijse.guideservice.entity.Guide;
import lk.ijse.guideservice.exception.AlreadyExistsException;
import lk.ijse.guideservice.exception.NotFoundException;
import lk.ijse.guideservice.repository.GuideRepository;
import lk.ijse.guideservice.service.GuideService;
import lk.ijse.guideservice.util.mappers.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;
    private final RequestMapper requestMapper;

    @Autowired
    public GuideServiceImpl(GuideRepository guideRepository, RequestMapper requestMapper) {
        this.guideRepository = guideRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public void registerGuide(RequestDto requestDto) {
        if (guideRepository.findById(requestDto.getGuideId()).isPresent()) {
            throw new AlreadyExistsException("Guide already exists. Guide ID : " + requestDto.getGuideId());
        } else {
            guideRepository.save(requestMapper.guideToRequestDto(requestDto));
        }
    }

    @Override
    public void updateGuide(RequestDto guide) {
        Guide guideByRepo = guideRepository.findById(guide.getGuideId()).isPresent()
                ? guideRepository.findById(guide.getGuideId()).get()
                : null;
        if (guideByRepo == null) {
            throw new NotFoundException("Guide not found. Guide ID : " + guide.getGuideId());
        } else {
            guideRepository.save(requestMapper.guideToRequestDto(guide));
        }
    }

    @Override
    public void deleteGuide(long guideId) {
        Guide guideByRepo = guideRepository.findById(guideId).isPresent()
                ? guideRepository.findById(guideId).get()
                : null;
        if (guideByRepo == null) {
            throw new NotFoundException("Guide not found. Guide ID : " + guideId);
        } else {
            guideRepository.delete(guideByRepo);
        }
    }

    public ResponseDto findGuideByID(long guideId) {
        Guide guideByRepo = guideRepository.findById(guideId).isPresent()
                ? guideRepository.findById(guideId).get()
                : null;
        if (guideByRepo == null) {
            throw new NotFoundException("Guide not found. Guide ID : " + guideId);
        } else {
            return requestMapper.guideToResponseDto(guideByRepo);
        }
    }

    @Override
    public List<ResponseDto> findAllGuides() {
        return guideRepository
                .findAll()
                .stream()
                .map(requestMapper::guideToResponseDto)
                .collect(toList());
    }
}
