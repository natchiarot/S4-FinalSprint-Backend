package com.sprint.s4sprint.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    public Optional<User> findByUserName(String userName);
    public List<User> findUsersByUserNameAndEmail(String userName, String email);
    public Optional<User> findUserByUserNameOrEmail(String userName, String email);
    public List<User> findUsersByEmail(String email);
}

