package com.user.rest.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;

    @NotNull
    @Size(min = 4, max = 10, message = "First name must be between 4 and 10 characters")
    @Pattern(regexp = "[A-Za-z]+", message = "First name must contain only letters")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 10, message = "Last name must be between 3 and 10 characters")
    @Pattern(regexp = "[A-Za-z]+", message = "Last name must contain only letters")
    private String lastName;

    @NaturalId
    @NotNull
    @Email(message = "Invalid email format")
    @Pattern(regexp = ".+@gmail\\.com", message = "Email must end with @gmail.com")
    private String email;

    @NaturalId
    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    @Pattern(regexp = "[0-9]+", message = "Phone number must contain only digits")
    private String phoneNumber;

    private String address;



}
