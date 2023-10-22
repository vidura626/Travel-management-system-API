package lk.ijse.travelservice.repository;

import lk.ijse.travelservice.entity.PackageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<PackageInfo, String> {

}
