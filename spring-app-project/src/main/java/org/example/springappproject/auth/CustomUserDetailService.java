package org.example.springappproject.auth;

import org.example.springappproject.entity.User;
import org.example.springappproject.exception.UserNotFoundException;
import org.example.springappproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService  implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.selectUserByUserId(username);

        if(user == null){
            throw new UserNotFoundException();
        }

        return new CustomUserDetail(user);

    }
}
