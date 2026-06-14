package project2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.entity.Notification;
import project2.repository.NotificationRepo;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController extends AbstractCrudController<Notification> {

    public NotificationController(NotificationRepo notificationRepo) {
        super(notificationRepo);
    }
}
