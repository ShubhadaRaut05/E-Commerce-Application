package com.shubhada.userservice.controllers;

import com.shubhada.userservice.dtos.*;
import com.shubhada.userservice.exceptions.UserAlreadyExistsException;
import com.shubhada.userservice.exceptions.UserDoesNotExistException;
import com.shubhada.userservice.models.SessionStatus;
import com.shubhada.userservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService=authService;
    }
    @PostMapping("/signup")
   public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto request) throws UserAlreadyExistsException {

       UserDto userDto =authService.signUp(request.getEmail(), request.getPassword());
       return new ResponseEntity<>(userDto, HttpStatus.OK);
   }
   @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto request) throws UserDoesNotExistException {
        return authService.login(request.getEmail(),request.getPassword());
   }
   @PostMapping("/logout")
   public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request){

        return authService.logout(request.getToken(), request.getUserId());
   }
   @PostMapping("/validate")
   public ResponseEntity<ValidateTokenResponseDto> validateToken(@RequestBody ValidateTokenRequestDto request){

       Optional<UserDto> userDto =authService.validate(request.getToken(), request.getUserId());
       if(userDto.isEmpty()){

           //session is not validate
           ValidateTokenResponseDto response=new ValidateTokenResponseDto();
           response.setSessionStatus(SessionStatus.INVALID);
           return new ResponseEntity<>(response,HttpStatus.OK);
       }
       ValidateTokenResponseDto response=new ValidateTokenResponseDto();
       response.setSessionStatus(SessionStatus.ACTIVE);
       response.setUserDto(userDto.get());
       return new ResponseEntity<>(response,HttpStatus.OK);
      //  return new ResponseEntity<>(sessionStatus,HttpStatus.OK);
   }


}
