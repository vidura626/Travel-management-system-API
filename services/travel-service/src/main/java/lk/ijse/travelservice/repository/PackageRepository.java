package lk.ijse.travelservice.repository;

import lk.ijse.travelservice.entity.PackageDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PackageRepository extends JpaRepository<PackageDetail, String> {

}
