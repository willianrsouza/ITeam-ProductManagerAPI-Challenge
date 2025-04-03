package br.com.iteam.infrastructure.service.User;

import static br.com.iteam.infrastructure.utils.Utilities.serviceLog;
import br.com.iteam.infrastructure.entity.UserEntity;
import br.com.iteam.infrastructure.exception.NotFoundException;
import br.com.iteam.infrastructure.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAuthenticateGatewayImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserAuthenticateGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        serviceLog.info("Starting loadUserByUsername::UserAuthenticateGatewayImpl");

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("User with e-mail: " + email + " not found.");
        }

        UserEntity user = userOptional.get();

        serviceLog.info("Done loadUserByUsername::UserAuthenticateGatewayImpl");

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
