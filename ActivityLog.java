package LostFoundBackend.mongomodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "activity_logs")
public class ActivityLog {

    @Id
    private String id;

    private String action;
    private String itemTitle;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ActivityLog() {
    }

    public ActivityLog(String action, String itemTitle) {
        this.action = action;
        this.itemTitle = itemTitle;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}