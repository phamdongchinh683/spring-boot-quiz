package com.quizmasterapp.QuizMaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizmasterapp.QuizMaster.Model.Users;

@Repository
public interface Userrepository extends JpaRepository<Users, Long> {
	public Users findByUsername(String username);
}
