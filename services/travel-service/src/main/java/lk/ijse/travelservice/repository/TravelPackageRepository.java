package lk.ijse.travelservice.repository;


import lk.ijse.travelservice.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    public List<TravelPackage> findTravelPackagesByPackageID(String packageId);
}
