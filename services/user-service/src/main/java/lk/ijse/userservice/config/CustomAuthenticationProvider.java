package lk.ijse.userservice.config;

import lk.ijse.userservice.repository.UserRepository;
import lk.ijse.userservice.service.UserDetailService.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;
    private CustomUserDetailsService customUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(UserRepository userRepository,
                                        CustomUserDetailsService customUserDetailsService,
                                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
        if (userDetails == null) {
            throw new BadCredentialsException("No user registered with this details");
        }else {
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials().toString(),
                    authentication.getAuthorities());
        }
/*

        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(pwd, user.getPassword())) {

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole()));
                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("No use registered with this details");
        }*/
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
