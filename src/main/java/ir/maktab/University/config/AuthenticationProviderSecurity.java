package ir.maktab.University.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderSecurity implements AuthenticationProvider {


    private final UserDetailsServiceSecurity userDetailsServiceSecurity;

    @Autowired
    public AuthenticationProviderSecurity(UserDetailsServiceSecurity userDetailsServiceSecurity) {
        this.userDetailsServiceSecurity = userDetailsServiceSecurity;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws
            AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsServiceSecurity.loadUserByUsername(username);
        System.out.println(userDetails.getAuthorities());
        if (userDetails.isEnabled()) {
            if (password.equals(userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        userDetails.getAuthorities()
                );
            } else {
                throw new RuntimeException("Username or password is wrong!");
            }
        } else {
            throw new RuntimeException("Your account is not active yet!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
