package com.ecommerce.repository;

import com.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,String> {

       Optional <User> findByEmail(String email); //this method not come in jpa repositoy  so we make
                                    // custome method like these ---using(findBy)

    Optional   <User> findByEmailAndPassword(String email, String password);

   Optional <User> findByNameContaining(String keywords);


}
