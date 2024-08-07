package com.sprint.s4sprint.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    public List<User> findUsersByUserNameAndEmail(String userName, String email);
    public Optional<User> findUserByUserName(String userName);
    public Optional<User> findUserByUserNameOrEmail(String userName, String email);
    public List<User> findUsersByEmail(String email);
}

