package com.sprint.s4sprint.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    public List<User> findUsersByUserNameAndEmail(String userName, String email);
    public List<User> findUsersByUserName(String userName);
    public List<User> findUsersByEmail(String email);
}

