package poc_notif.notif.service;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class SignatureValidator {

    private final String secret = System.getenv("NOTIFICATION_SECRET");

    public boolean checkSignature(byte[] data, String signature) {
        if (signature == null) {
            throw new IllegalArgumentException("Bad headers signature");
        }

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] computed = mac.doFinal(data);
            String computedSignature = Base64.getEncoder().encodeToString(computed);

            return computedSignature.equals(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error while computing signature", e);
        }
    }
}

