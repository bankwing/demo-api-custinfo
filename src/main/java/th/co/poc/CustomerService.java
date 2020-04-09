package th.co.poc;

import th.co.poc.domain.Customer;

public interface CustomerService {
    Iterable<Customer> listAllProducts();

    Customer getCustomerById(Long customerId);
    Customer setFirstName(String customerFirstName);
    Customer setLastName(String customerLastName);
    Customer setTel(String tel);

    
    //Customer saveCustomer(Customer customer);
    Customer deleteCustomer(Long id);

    void deleteProduct(Long id);
}
