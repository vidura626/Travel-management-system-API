package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, Long> {
    public Vehicle findVehicleByVehicleId(long vehicleId);

    public Vehicle findByName(String name);

}
