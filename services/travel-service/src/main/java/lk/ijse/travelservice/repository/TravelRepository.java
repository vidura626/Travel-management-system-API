package lk.ijse.travelservice.repository;


import lk.ijse.travelservice.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, String> {
    public List<Travel> findTravelPackagesByPackageID(String packageId);
    public List<Travel> findTravelsByUserID(long userId);

}
