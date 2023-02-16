package az.spring.jdbc.model;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private Integer phone;
    private String address;
    private String email;
    private String password;
}
