package lk.ijse.userservice.repository;

import lk.ijse.userservice.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<Vehicle, String> {
    public Vehicle findUserByUsername(String username);
    public Vehicle findUserByEmail(String email);

    public List<Vehicle> findUsersByRole(String role);

}
