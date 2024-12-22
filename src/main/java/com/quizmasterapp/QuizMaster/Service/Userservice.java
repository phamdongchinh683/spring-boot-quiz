package com.quizmasterapp.QuizMaster.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmasterapp.QuizMaster.Model.Users;
import com.quizmasterapp.QuizMaster.Repository.Userrepository;

@Service
public class Userservice {
	@Autowired
	private Userrepository userrepository;

	// register user
	public void registerUser(Users user) {
		userrepository.save(user);
	}

	public List<Users> listUsers() {
		return userrepository.findAll();
	}

	public Users loginValidate(String username) {
		return userrepository.findByUsername(username);
	}

}
