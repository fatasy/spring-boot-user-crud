package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserModel;

/**
 * UserInterface
 */
public interface IUserRepository extends JpaRepository<UserModel, Long> {

}