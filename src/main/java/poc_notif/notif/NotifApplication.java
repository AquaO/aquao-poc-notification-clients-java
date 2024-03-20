package poc_notif.notif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class NotifApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		System.setProperty("server.port", dotenv.get("SERVER_PORT"));
		System.setProperty("server.endpoint.base", dotenv.get("SERVER_ENDPOINT_BASE"));
		System.setProperty("notification.secret", dotenv.get("NOTIFICATION_SECRET"));
		
		SpringApplication.run(NotifApplication.class, args);
	}

}
