package com.example.springboot401b;

import org.springframework.data.repository.CrudRepository;

public interface User1Repository extends CrudRepository<User1, Long> {
    User1 findByUsername(String username);
    User1 findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
}
