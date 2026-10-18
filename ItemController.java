package LostFoundBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import LostFoundBackend.model.Item;
import LostFoundBackend.repository.ItemRepository;
import LostFoundBackend.mongomodel.ActivityLog;
import LostFoundBackend.mongorepository.ActivityLogRepository;

@CrossOrigin(origins = "*")
@RestController
public class ItemController {

    private final ItemRepository itemRepository;
    private final ActivityLogRepository activityLogRepository;

    public ItemController(
            ItemRepository itemRepository,
            ActivityLogRepository activityLogRepository) {
        this.itemRepository = itemRepository;
        this.activityLogRepository = activityLogRepository;
    }

    // Get all items
    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepository.getAllItems();
    }

    // Save item in MySQL and log the activity in MongoDB
    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        Item savedItem = itemRepository.save(item);

        ActivityLog log = new ActivityLog(
                "ITEM_REPORTED",
                savedItem.getTitle()
        );

        activityLogRepository.save(log);

        return savedItem;
    }
}