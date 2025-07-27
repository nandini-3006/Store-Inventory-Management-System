package com.nandini.management.users;

import com.nandini.management.sales.SalesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@PreAuthorize("hasAnyRole('OWNER','INVENTOR','MANAGER',CASHIER)")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService,UserRepository userRepository){
        this.userService=userService;
        this.userRepository=userRepository;
    }
   @PostMapping("/register")
   public ResponseEntity<String> user_register(@RequestBody UserRequestDTO body) {
       try {
           if(!userRepository.existsByUsername(body.getUsername())) {
               var user=userService.register_user(body.getUsername(),body.getPassword(),body.getName());
               return ResponseEntity.ok("User Registered successfully");
           }
           else{
               return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
           }
       } catch (Exception e) {
           return ResponseEntity.badRequest().build();
       }

   }
    @PostMapping("/login")
    public ResponseEntity<String> user_login(@RequestBody UserLoginDTO body) {
        try {
            String message = userService.login_user(body.getUsername(), body.getPassword());
            return ResponseEntity.ok(message);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

    }
}
