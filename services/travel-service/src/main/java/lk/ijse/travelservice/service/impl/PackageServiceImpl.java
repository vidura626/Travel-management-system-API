package lk.ijse.travelservice.service.impl;

import lk.ijse.travelservice.dto.PackageInfoDto;
import lk.ijse.travelservice.entity.PackageInfo;
import lk.ijse.travelservice.exception.AlreadyExistsException;
import lk.ijse.travelservice.exception.CannotDeleteException;
import lk.ijse.travelservice.exception.NotFoundException;
import lk.ijse.travelservice.repository.PackageRepository;
import lk.ijse.travelservice.service.PackageService;
import lk.ijse.travelservice.service.TravelPackageService;
import lk.ijse.travelservice.util.mappers.PackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    private final TravelPackageService travelPackageService;
    private final PackageRepository repository;
    private final PackageMapper mapper;

    @Autowired
    public PackageServiceImpl(TravelPackageService travelPackageService, PackageRepository repository, PackageMapper mapper) {
        this.travelPackageService = travelPackageService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public String createNewPackage(PackageInfoDto packageInfo) {
//        Validation
        if (repository.findById(packageInfo.getPackageName()).isPresent()) {
            throw new AlreadyExistsException("Package already exists : " + packageInfo.getPackageName());
        } else {
//        TODO: Check Hotels and Vehicles are really exist


//        Convert into package entity
            PackageInfo packageEntity = mapper.toEntity(packageInfo);
            repository.save(packageEntity);
            return packageEntity.getPackageName();
        }
    }

    @Override
    public String updateExistingPackage(PackageInfoDto packageInfo) {
//        Validation
        if (repository.findById(packageInfo.getPackageName()).isEmpty()) {
            throw new NotFoundException("Package not found : " + packageInfo.getPackageName());
        } else {
//        TODO: Check Hotels and Vehicles are really exist


//        Convert into package entity
            PackageInfo packageEntity = mapper.toEntity(packageInfo);
            repository.save(packageEntity);
            return packageEntity.getPackageName();
        }
    }

    @Override
    public List<PackageInfoDto> getAllPackages() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public String deleteExistingPackage(String packageName) {
        PackageInfo packageInfo = repository
                .findById(packageName)
                .orElseThrow(
                        () -> new NotFoundException("Package not found : " + packageName)
                );
        if (travelPackageService.findTravelsByPackageName(packageName) == null) {
            repository.delete(packageInfo);
            return packageName;
        } else throw new CannotDeleteException("Package is in use");
    }

    @Override
    public PackageInfoDto findPackageByPackageName(String packageName) {
        return mapper.toDto(
                repository.findById(packageName).orElseThrow(
                        () -> new NotFoundException("Package not found : " + packageName)));
    }
}
