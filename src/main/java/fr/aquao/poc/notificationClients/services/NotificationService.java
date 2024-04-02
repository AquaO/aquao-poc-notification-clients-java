package fr.aquao.poc.notificationClients.services;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationService {

    @Value("${notification.secret}")
    private String secret;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void processNotification(String body, String uid, String signature, String status) {
        try {
            checkSignature(body.getBytes(), signature);
            logger.info("SUCCESS {} notification for client '{}'", status, uid);
        } catch (Exception e) {
            logger.error("FAILED {} notification: {}", status, e.getMessage());
        }
    }

    private void checkSignature(byte[] data, String signature) throws Exception {
        Mac sha256HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(Base64.decodeBase64(secret), "HmacSHA256");
        sha256HMAC.init(secretKey);

        String hash = Base64.encodeBase64String(sha256HMAC.doFinal(data));
        if (!hash.equals(signature)) {
            throw new Exception("Bad signature '" + signature + "' from computed '" + hash + "'");
        }
    }
}
