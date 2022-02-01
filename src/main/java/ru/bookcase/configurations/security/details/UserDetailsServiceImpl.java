package ru.bookcase.configurations.security.details;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bookcase.repositories.UserRepository;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public final class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepository.findUserByLogin(login)
                .orElseThrow(IllegalArgumentException::new));
    }
}
