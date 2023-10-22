package lk.ijse.travelservice.repository;


import lk.ijse.travelservice.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<TravelPackage, Long> {

}
