package elec5619.clientuiservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApp.class, args);
	}
}
