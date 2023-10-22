package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.TravelPackageDto;
import lk.ijse.travelservice.repository.TravelPackageRepository;
import lk.ijse.travelservice.service.TravelPackageService;
import lk.ijse.travelservice.util.mappers.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository repository;
    private final TravelMapper mapper;

    @Autowired
    public TravelPackageServiceImpl(TravelPackageRepository repository, TravelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<TravelPackageDto> findTravelsByPackageName(String packageName) {
//        return mapper.travelListToTravelDtoList(repository.findTravelPackagesByPackageID(packageName));
        return null;
    }
}
