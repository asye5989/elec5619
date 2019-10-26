package elec5619.psychologyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PsychologyServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(PsychologyServiceApp.class, args);
	}
}
