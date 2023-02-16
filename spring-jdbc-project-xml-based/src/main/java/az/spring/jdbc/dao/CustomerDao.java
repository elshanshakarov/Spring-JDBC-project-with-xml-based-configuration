package az.spring.jdbc.dao;

import az.spring.jdbc.model.Customer;

import java.util.List;


public interface CustomerDao {

    List<Customer> getCustomerList() throws Exception;

    Customer getCustomerById(Long customerId) throws Exception;

    void addCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    void deleteCustomer(Long customerId) throws Exception;

    Long customerCount() throws Exception;
}
