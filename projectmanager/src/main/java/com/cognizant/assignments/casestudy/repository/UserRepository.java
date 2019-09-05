package com.cognizant.assignments.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.assignments.casestudy.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByFirstNameAndLastNameAndEmployeeId(String firstName,
                                                          String lastName, int employeeId);

}
