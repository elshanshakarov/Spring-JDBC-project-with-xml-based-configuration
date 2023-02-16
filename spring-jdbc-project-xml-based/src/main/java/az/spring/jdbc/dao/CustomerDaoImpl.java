package az.spring.jdbc.dao;

import az.spring.jdbc.model.Customer;
import lombok.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerDaoImpl implements CustomerDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomerList() throws Exception {
        String query = "select * from customer where active=true";
        List<Customer> customerList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Customer.class));
        //sorğudan gələn nəticəni yerləşdirirəm Customer.class-a. Yəni Customer class-nın içərisinə
        return customerList;
    }

    @Override
    public void addCustomer(Customer customer) throws Exception {
        String sql = "insert into customer(id, name,surname,phone,address,email,password)\n"
                + "values (nextval('customer_seq'),?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{customer.getName(), customer.getSurname(), customer.getPhone(), customer.getAddress(), customer.getEmail(), customer.getPassword()});
    }

    @Override
    public Customer getCustomerById(Long customerId) throws Exception {
        String query = "select * from customer where active=true and id=?";
        List<Customer> customer = jdbcTemplate.query(query, new Object[]{customerId}, new BeanPropertyRowMapper<>(Customer.class));
        if (!customer.isEmpty()) {
            return customer.get(0);
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        String query = "update customer set name=?, surname=?, phone=?, address=?, email=?, password=? where id=?";
        jdbcTemplate.update(query, new Object[]{customer.getName(), customer.getSurname(), customer.getPhone(), customer.getAddress(), customer.getEmail(), customer.getPassword(), customer.getId()});
    }

    @Override
    public void deleteCustomer(Long customerId) throws Exception {
        String query = "update customer set active=false where id=?";
        jdbcTemplate.update(query, new Object[]{customerId});
    }

    @Override
    public Long customerCount() throws Exception {
        String query = "select count(*) from customer where active=true";
        Long count = jdbcTemplate.queryForObject(query, Long.class);
        return count;
    }


}
