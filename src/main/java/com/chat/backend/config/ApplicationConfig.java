package com.chat.backend.config;

import com.chat.backend.model.entity.User;
import com.chat.backend.repository.UserRepository;
import com.chat.backend.security.UserPrincipal;
import com.chat.backend.service.RoleService;
import com.chat.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    CommandLineRunner run() {
        return args -> {
            roleService.addRoles();
            userService.createAdmin();
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this::getUserPrincipal;
    }

    private UserDetails getUserPrincipal(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserPrincipal.build(user);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
