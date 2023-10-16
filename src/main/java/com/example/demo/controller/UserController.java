package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AddressModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.IAddressRepository;
import com.example.demo.repository.IUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IAddressRepository addressRepository;

  @GetMapping("/")
  public Iterable<UserModel> index() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public UserModel getUser(@PathVariable Long id) {
    return userRepository.findById(id).get();
  }

  @PostMapping("/")
  public UserModel create(@RequestBody UserModel user) {
    AddressModel address = user.getAddress();
    addressRepository.save(address);
    user.setAddress(address);
    return userRepository.save(user);
  }

  @PutMapping("/{id}")
  public UserModel update(@PathVariable Long id, @RequestBody UserModel nextUser) {
    UserModel user = userRepository.findById(id).get();
    user.name = nextUser.name;
    user.password = nextUser.password;
    user.birthData = nextUser.birthData;

    AddressModel address = user.getAddress();
    AddressModel nextAddress = nextUser.getAddress();
    address.address = nextAddress.address;
    address.city = nextAddress.city;
    address.state = nextAddress.state;
    address.country = nextAddress.country;
    address.number = nextAddress.number;
    addressRepository.save(address);
    user.setAddress(address);

    return userRepository.save(user);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    userRepository.deleteById(id);
    return "Sucesso!";
  }

}
