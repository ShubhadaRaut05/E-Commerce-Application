package com.shubhada.userservice.services;

import com.shubhada.userservice.dtos.UserDto;
import com.shubhada.userservice.models.Role;
import com.shubhada.userservice.models.User;
import com.shubhada.userservice.repositories.RoleRepository;
import com.shubhada.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public UserDto getUserDetails(Long userId){
        System.out.println("I got the request");
        //return random user
        return new UserDto();
        /*Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty()){
            return null;
        }
        return UserDto.from(userOptional.get());*/
    }
    public UserDto setUserRoles(Long userId, List<Long> roleIds){
        Optional<User> userOptional=userRepository.findById(userId);
        List<Role> roles=roleRepository.findAllByIdIn(roleIds);
        if(userOptional.isEmpty()){
            return null;
        }
        User user=userOptional.get();
        user.setRoles(Set.copyOf(roles));
        User savedUser=userRepository.save(user);
        return UserDto.from(savedUser);

    }
}
