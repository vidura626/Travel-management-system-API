package lk.ijse.travelservice.service;

import lk.ijse.travelservice.dto.TravelPackageDto;

import java.util.List;

public interface TravelPackageService {
    List<TravelPackageDto> findTravelsByPackageName(String packageName);

}
