package th.co.poc;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import th.co.poc.domain.Customer;
import th.co.poc.repository.CustomerRepository;


@SpringBootApplication
public class Application {
	
	@Autowired CustomerRepository customerRepository;
	@Value("${initCustomers}") String initCustomers;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	 @PostConstruct
		public void init() {
			List<Customer> list = new ArrayList<>();
		
			try {
				String[] initCustomersArray = initCustomers.split(";");
			
				for(int i = 0; i < initCustomers.length(); i++) {
					String[] initCustomer = initCustomersArray[i].split(",");
					list.add(new Customer(Integer.parseInt(initCustomer[0]),initCustomer[1],initCustomer[2],initCustomer[3]));
				} 
			} catch(Exception e){
				e.printStackTrace();
			}
						

			customerRepository.save(list);
	 }


}
