package com.sprint.s4sprint;

import com.sprint.S4sprintApplication;
import com.sprint.s4sprint.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.sprint.s4sprint.User.User;
import com.sprint.s4sprint.User.UserService;
import com.sprint.s4sprint.User.UserController;

@SpringBootTest
class S4sprintApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testConnection() {
		S4sprintApplication.main(new String[] {});
	}

	@Test
	void testUser() {
		User user = new User();
		user.setUserId(1);
		user.setEmail("user@hotmail.com");
		user.setUserName("username");
		user.setPassword("password");
		user.setPhone("1234567890");
		user.getUserId();
		user.getEmail();
		user.getUserName();
		user.getPassword();
		user.getPhone();
	}

	@Test
	void testUserService() {
		UserService userService = new UserService();
		userService.createUser(new User());
		userService.getAllUsers();
		userService.getUser(1);
		userService.findUsersByUserNameAndEmail("username", "user@hotmail.com");
	}

	@Test
	void testUserController() {
		UserController userController = new UserController();
		userController.createUser(new User());
		userController.getAllUsers();
		userController.getUser(1);
		userController.searchUser("username", "user@hotmail.com");
	}
}


