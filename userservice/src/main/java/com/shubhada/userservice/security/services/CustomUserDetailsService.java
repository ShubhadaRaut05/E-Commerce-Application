package com.shubhada.userservice.security.services;

import com.shubhada.userservice.models.User;
import com.shubhada.userservice.repositories.UserRepository;
import com.shubhada.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(username+" does not exists");
        }
        return new CustomUserDetails(user.get());
    }
}
