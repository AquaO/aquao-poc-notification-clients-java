package fr.aquao.poc.notificationClients.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import fr.aquao.poc.notificationClients.services.NotificationService;

@RestController
public class ClientNotificationController {

    private final NotificationService notificationService;

    public ClientNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("${server.endpoint.base}/client/created")
    public ResponseEntity<Void> clientCreated(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                              @RequestHeader("x-aquao-notification-sign") String signature) {
        return handleNotification(body, uid, signature, "CREATED");
    }

    @PostMapping("${server.endpoint.base}/client/updated")
    public ResponseEntity<Void> clientUpdated(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                              @RequestHeader("x-aquao-notification-sign") String signature) {
        return handleNotification(body, uid, signature, "UPDATED");
    }

    @PostMapping("${server.endpoint.base}/client/deleted")
    public ResponseEntity<Void> clientDeleted(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                              @RequestHeader("x-aquao-notification-sign") String signature) {
        return handleNotification(body, uid, signature, "DELETED");
    }

    @PostMapping("${server.endpoint.base}/client/anonymized")
    public ResponseEntity<Void> clientAnonymized(@RequestBody String body, @RequestHeader("x-aquao-notification-uid") String uid,
                                 @RequestHeader("x-aquao-notification-sign") String signature) {
        return handleNotification(body, uid, signature, "ANONYMIZED");
    }

    protected ResponseEntity<Void> handleNotification(String body, String uid, String signature, String status){
        notificationService.processNotification(body, uid, signature, status);
        return ResponseEntity.noContent().build();
    }
}