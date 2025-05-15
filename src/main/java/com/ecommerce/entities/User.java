package com.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;
import org.hibernate.id.IdentityGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name ="users")   // to change the name of table
public class User {

    @Id                                     // To make primary key
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)     //to generate id
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email" , unique = true)
    private String email;

    @Column(name = "user_password", length = 10)
    private String password;

    private String gender;

    @Column(name = "about")
    private String about;

    @Column(name = "user_image")
    private String image;


}
