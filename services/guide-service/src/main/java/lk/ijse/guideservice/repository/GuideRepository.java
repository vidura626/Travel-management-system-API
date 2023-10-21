package lk.ijse.guideservice.repository;

import lk.ijse.guideservice.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuideRepository extends MongoRepository<Guide, Long> {
}
