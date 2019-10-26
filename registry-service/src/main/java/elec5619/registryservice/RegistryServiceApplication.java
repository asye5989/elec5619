package elec5619.registryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 
 * @author ahmed
 * Description : This is our Registry Service implemented using Eureka Server. 
 * 	             All exsisting services except configuation service  in the system will register here for 
 *               discovery by other services and load balancing  
 *               
 *               Security: Basic Http Security enable
 *               Load Balancing: Enabled using Ribbon 
 *               This connects with configuration service for configuring itself 
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryServiceApplication.class, args);
	}

}
