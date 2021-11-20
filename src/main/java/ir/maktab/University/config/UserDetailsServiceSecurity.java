package ir.maktab.University.config;

import ir.maktab.University.entities.User;
import ir.maktab.University.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceSecurity implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceSecurity(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if(user != null){
            return new UserDetailsSecurity(user);
        }else{
            throw new RuntimeException("Username or password is wrong!");
        }
    }
}
