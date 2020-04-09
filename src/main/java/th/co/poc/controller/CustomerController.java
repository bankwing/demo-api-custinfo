package th.co.poc.controller;


import static net.logstash.logback.argument.StructuredArguments.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Model;
import io.swagger.annotations.ApiOperation;
import th.co.poc.domain.Customer;
import th.co.poc.repository.CustomerRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
public class CustomerController {
	


	private static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired CustomerRepository customerRepository;
	
	@ApiOperation(value = "View a list of available customers",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "Fail to retrieved list")
	}
	)
	
	

	@RequestMapping(value = "/customers", method= RequestMethod.GET, produces = "Application/json")
	public Iterable<Customer> findAll() {
		log.info("{}", value("app_action","getAllCustomers"), value("app_request",""), value("app_response", customerRepository.findAll()));
		return customerRepository.findAll();
	}



	@ApiOperation(value = "Search a customer with an ID",response = Customer.class)
	@RequestMapping(value = "/customers/{customerId}", method= RequestMethod.GET, produces = "Application/json")
	public Customer showCustomer(@PathVariable long customerId, Model model) {
		//Customer customer = customerService.getCustomerById(customerId);
		log.info("{}", value("app_action","getCustomerById"), value("app_request",customerId), value("app_response",customerRepository.findOne(customerId)));
		return customerRepository.findOne(customerId);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Search a customer with name",response = Customer.class)
	@RequestMapping(value = "/customers/findByName/{name}", method= RequestMethod.GET, produces = "Application/json")
	public Customer showCustomer(@PathVariable String name) {
		//Customer customer = customerService.getCustomerByName(customerName);
		log.info("{}", value("app_action","getCustomerByName"), value("app_request",name), value("app_response",customerRepository.findByfirstName(name)));
		return customerRepository.findByfirstName(name);
	}

	@CrossOrigin
	@ApiOperation(value = "Add a customer")
	@RequestMapping(value = "/customers/add", method = RequestMethod.POST, produces = "Application/json")
	public void saveCustomer(@RequestBody Customer customer){
		customerRepository.save(customer);
//		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		//return customerRepository.saveCustomer(customer);
	}

	@CrossOrigin
	@ApiOperation(value = "Update a customer")
	@RequestMapping(value = "/customers/update/{customerId}", method = RequestMethod.PUT, produces = "Application/json")
	public void updateCustomer(@PathVariable long customerId, String firstName,String lastName,String tel){
		Customer customerdetail = customerRepository.findOne(customerId);
		if(firstName == null){
			customerdetail.setFirstName(customerdetail.getFirstName());
		}else{
			customerdetail.setFirstName(firstName);
		}
		if(lastName == null){
			customerdetail.setLastName(customerdetail.getLastName());
		}else{
			customerdetail.setLastName(lastName);
		}
		if(tel == null){
			customerdetail.setTel(customerdetail.getTel());
		}else{
			customerdetail.setTel(tel);
		}		
		customerRepository.save(customerdetail);
//		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		//return customerRepository.saveCustomer(customer);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Delete a customer")
	@RequestMapping(value = "/customers/delete/{customerId}", method = RequestMethod.DELETE, produces = "Application/json")
	public void deleteCustomer(@PathVariable Long customerId){
		//Customer customerdetail = customerRepository.findOne(customerId);

		customerRepository.delete(customerRepository.findOne(customerId));
		//return new ResponseEntity("Customer deleted successfully", HttpStatus.OK);
	}
}

