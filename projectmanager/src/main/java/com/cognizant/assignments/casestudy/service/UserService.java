package com.cognizant.assignments.casestudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cognizant.assignments.casestudy.common.UserVO;
import com.cognizant.assignments.casestudy.entity.User;
import com.cognizant.assignments.casestudy.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	UserRepository userRepo;
	
	public User insertUser(UserVO userVO) {

		User userEntity = new User();
		userEntity.setFirstName(userVO.getFirstName());
		userEntity.setLastName(userVO.getLastName());
		userEntity.setEmployeeId(userVO.getEmployeeId());

		System.out.println(userEntity.toString());
		return userRepo.save(userEntity);
	}

	public List<User> findAllUsers() {
		
		return userRepo.findAll();
	}

	public void deleteUser(int userId) {

		User userEntity = userRepo.findById(userId).orElseThrow();
		userRepo.delete(userEntity);
	}
	
	public User updateUser(UserVO userVO, int userId) {

		User userEntity = userRepo.findById(userId).orElseThrow();

		userEntity.setFirstName(userVO.getFirstName());
		userEntity.setLastName(userVO.getLastName());
		userEntity.setEmployeeId(userVO.getEmployeeId());

		System.out.println(userEntity.toString());
		return userRepo.save(userEntity);


	}

}
