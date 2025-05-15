package com.ecommerce.repository;

import com.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {

    User findByEmail(String email); //this method not come in jpa repositoy  so we make
                                    // custome method like these ---using(findBy)

    User findByEmailAndPassword(String email, String password);

    User findByNameContaining(String keywords);


}
