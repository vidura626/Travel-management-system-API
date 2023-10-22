package lk.ijse.travelservice.service;

import lk.ijse.travelservice.dto.PackageInfoDto;
import lk.ijse.travelservice.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PackageService {

    String createNewPackage(PackageInfoDto packageInfo);

    String updateExistingPackage(PackageInfoDto packageInfo);

    List<PackageInfoDto> getAllPackages();

    String deleteExistingPackage(String packageName);

    PackageInfoDto findPackageByPackageName(String packageName);
}
