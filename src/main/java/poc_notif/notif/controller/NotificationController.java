package poc_notif.notif.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import poc_notif.notif.service.SignatureValidator;

@RestController
public class NotificationController {
  
    @Autowired
    private SignatureValidator signatureValidator;
    
    @PostMapping("/client/created")
    public ResponseEntity<?> onClientCreated(@RequestBody Map<String, Object> clientData) {
        // Logique pour gérer la notification de création du client
        System.out.println("Client créé : " + clientData);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/client/updated")
    public ResponseEntity<?> onClientUpdated(@RequestBody Map<String, Object> clientData) {
        // Logique pour gérer la notification de mise à jour du client
        System.out.println("Client mis à jour : " + clientData);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/client/deleted")
    public ResponseEntity<?> onClientDeleted(@RequestBody Map<String, Object> clientData) {
        // Logique pour gérer la notification de suppression du client
        System.out.println("Client supprimé : " + clientData);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/client/anonymized")
    public ResponseEntity<?> onClientAnonymized(@RequestBody Map<String, Object> clientData) {
        // Logique pour gérer la notification d'anonymisation du client
        System.out.println("Client anonymisé : " + clientData);
        return ResponseEntity.noContent().build();
    }
}
