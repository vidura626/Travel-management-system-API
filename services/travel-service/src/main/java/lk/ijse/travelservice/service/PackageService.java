package lk.ijse.travelservice.service;

import lk.ijse.travelservice.dto.PackageDto;

import java.util.List;

public interface PackageService {

    String createNewPackage(PackageDto packageInfo);

    String updateExistingPackage(PackageDto packageInfo);

    List<PackageDto> getAllPackages();

    String deleteExistingPackage(String packageName);

    PackageDto findPackageByPackageName(String packageName);
}
