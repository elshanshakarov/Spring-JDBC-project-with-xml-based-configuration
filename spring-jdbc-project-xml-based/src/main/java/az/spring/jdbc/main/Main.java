package az.spring.jdbc.main;

import az.spring.jdbc.dao.CustomerDao;
import az.spring.jdbc.model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            CustomerDao customerDao = (CustomerDao) context.getBean("customerDaoImpl");

            System.out.println("Which method? view, add, update, delete, count");
            switch (sc.nextLine()) {
                case "view":
                    List<Customer> customerList = customerDao.getCustomerList();
                    customerList.forEach(c -> System.out.println(c));
                    break;
                case "add":
                    Customer customer = new Customer();
                    System.out.println("Please, enter customer data.");
                    System.out.print("Name: ");
                    customer.setName(sc.nextLine());
                    System.out.print("Surname: ");
                    customer.setSurname(sc.nextLine());
                    System.out.print("Address: ");
                    customer.setAddress(sc.nextLine());
                    System.out.print("Email: ");
                    customer.setEmail(sc.nextLine());
                    System.out.print("Password: ");
                    customer.setPassword(sc.nextLine());
                    System.out.print("Phone: ");
                    customer.setPhone(sc.nextInt());
                    customerDao.addCustomer(customer);
                    System.out.println("Success)");
                    break;
                case "update":
                    System.out.print("Enter ID: ");
                    Long id = sc.nextLong();
                    if (id != null) {
                        Customer customer1 = customerDao.getCustomerById(id);
                        System.out.println(customer1);
                        System.out.print("Name: ");
                        sc.nextLine();
                        customer1.setName(sc.nextLine());
                        System.out.print("Surname: ");
                        customer1.setSurname(sc.nextLine());
                        System.out.print("Phone: ");
                        customer1.setPhone(sc.nextInt());
                        System.out.print("Address: ");
                        sc.next();
                        customer1.setAddress(sc.nextLine());
                        System.out.print("Email: ");
                        customer1.setEmail(sc.nextLine());
                        System.out.print("Password: ");
                        customer1.setPassword(sc.nextLine());
                        customerDao.updateCustomer(customer1);
                        System.out.println("Success)");
                    } else {
                        System.out.println("This id is not exist!");
                    }
                    break;
                case "delete":
                    System.out.print("Enter the ID to delete: ");
                    Long customerId = sc.nextLong();
                    Customer selectedCustomer = customerDao.getCustomerById(customerId);
                    System.out.println(selectedCustomer);
                    if (selectedCustomer != null) {
                        customerDao.deleteCustomer(customerId);
                        System.out.println("User with ID number " + customerId + " has been deleted successfully");
                    } else {
                        System.out.println("User with ID number " + customerId + " does not exist");
                    }
                    break;
                case "count":
                    Long count = customerDao.customerCount();
                    System.out.println("Customer count: " + count);
                    break;
                default:
                    System.out.println("Invalid method!");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
