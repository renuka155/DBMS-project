package LostFoundBackend.mongocontroller;

import LostFoundBackend.mongomodel.ActivityLog;
import LostFoundBackend.mongorepository.ActivityLogRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/activity-logs")
@CrossOrigin(origins = "*")
public class ActivityLogController {
    private final ActivityLogRepository repository;

    public ActivityLogController(ActivityLogRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<ActivityLog> createLog(
            @RequestBody ActivityLog log) {
        ActivityLog savedLog = repository.save(log);
        return ResponseEntity.ok(savedLog);
    }
}