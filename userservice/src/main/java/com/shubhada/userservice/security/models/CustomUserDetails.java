package com.shubhada.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shubhada.userservice.models.Role;
import com.shubhada.userservice.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    //private User user;
    //Jackson takes default constructor to convert object into json
    private List<GrantedAuthority>  authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Long userId;



    public CustomUserDetails(User user){

       // this.user=user;
        authorities=new ArrayList<>();
        for(Role role:user.getRoles()){

          authorities.add(new CustomGrantedAuthority(role));
        }
        this.password=user.getPassword();
        this.username=user.getEmail();
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;
        this.enabled=true;
        this.userId=user.getId();

    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       /* List<CustomGrantedAuthority> customGrantedAuthorities=new ArrayList<>();
        for(Role role:user.getRoles()){

            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
        }
        return customGrantedAuthorities;*/
        return this.authorities;
    }

    @Override
    public String getPassword() {

        //return user.getPassword();
        //jackson thinks if there is a method getPassword(), there should be field password
        return this.password;
    }

    @Override
    public String getUsername() {

        //return user.getEmail();
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //account never expired
        //return true;
        return this.accountNonExpired;

    }

    @Override
    public boolean isAccountNonLocked() {
        //account is never locked if hacked
      //  return true;
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // if (Date.now() - lastPasswordUpdateDate > 90) {return false} return true;
      //  return true;
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        //in our service we are not disabling anything
        //return true;
        return this.enabled;
    }
}
