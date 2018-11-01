package com.example.springBoot;

import org.springframework.data.repository.CrudRepository;

//CrudRepository内置一些增删改查方法
public interface UserDao extends CrudRepository<User, Integer> {

}
