package lk.ijse.hotelservice.repository;

import lk.ijse.hotelservice.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Long> {

    public Hotel findByName(String name);
}
