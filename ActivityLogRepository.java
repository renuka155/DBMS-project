package LostFoundBackend.mongorepository;

import LostFoundBackend.mongomodel.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityLogRepository
        extends MongoRepository<ActivityLog, String> {
}