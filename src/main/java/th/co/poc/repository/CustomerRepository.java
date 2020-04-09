package th.co.poc.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import th.co.poc.domain.Customer;


@RestResource(path="customers", rel="customer")
public interface CustomerRepository extends CrudRepository<Customer,Long>{
	Customer findByfirstName(String firstName);
	

	
	

}
