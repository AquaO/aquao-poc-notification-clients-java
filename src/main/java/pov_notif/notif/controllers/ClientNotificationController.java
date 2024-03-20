package pov_notif.notif.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pov_notif.notif.services.NotificationService;

@RestController
@RequestMapping("${server.endpoint.base}/client")
public class ClientNotificationController {

    private final NotificationService notificationService;

    public ClientNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/created")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clientCreated(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                              @RequestHeader("x-aquao-notification-sign") String signature) {
        notificationService.processNotification(body, uid, signature, "CREATED");
    }

    @PostMapping("/updated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clientUpdated(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                              @RequestHeader("x-aquao-notification-sign") String signature) {
        notificationService.processNotification(body, uid, signature, "UPDATED");
    }

    @PostMapping("/deleted")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clientDeleted(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                              @RequestHeader("x-aquao-notification-sign") String signature) {
        notificationService.processNotification(body, uid, signature, "DELETED");
    }

    @PostMapping("/anonymized")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clientAnonymized(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                                 @RequestHeader("x-aquao-notification-sign") String signature) {
        notificationService.processNotification(body, uid, signature, "ANONYMIZED");
    }
}