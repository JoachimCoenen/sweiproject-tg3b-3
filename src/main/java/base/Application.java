package base;

import java.net.NetworkInterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@SpringBootApplication
@EnableEmailTools
public class Application {

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	public String getHostAddress(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}
}
