package gmky.core.security;

import gmky.core.exception.NotFoundException;
import gmky.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static gmky.core.enumeration.CommonExceptionEnum.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getStatus(),
                user.getDeletedAt(),
                user.getExpireAt()
        );
    }
}
