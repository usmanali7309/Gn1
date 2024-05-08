package com.user.rest.entities;

import com.sun.org.apache.xpath.internal.objects.XString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

   private String username;

   private String email;

   private String password;



}
