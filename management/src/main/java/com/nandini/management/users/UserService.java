package com.nandini.management.users;

import com.nandini.management.products.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
private final UserRepository userRepository;
public UserService(UserRepository userRepository){
    this.userRepository=userRepository;
}

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserEntity register_user(String username,String password,Role name){
    UserEntity user = new UserEntity();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setName(name);
    return userRepository.save(user);
}
    public String login_user(String username,String password){
       UserEntity user=userRepository.findByUsername(username) .orElseThrow(() -> new UsernameNotFoundException("User not found"));
       if(passwordEncoder.matches(password,user.getPassword())){
           UsernamePasswordAuthenticationToken authToken =
                   new UsernamePasswordAuthenticationToken(
                           user.getUsername(),
                           user.getPassword(),
                           List.of(new SimpleGrantedAuthority("ROLE_" + user.getName()))
                   );

           SecurityContextHolder.getContext().setAuthentication(authToken);
           return "login Successfull";
       }
       else{
           throw new BadCredentialsException("Invalid password");
       }
    }
}
