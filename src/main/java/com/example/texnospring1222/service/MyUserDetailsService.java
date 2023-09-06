package com.example.texnospring1222.service;

import com.example.texnospring1222.dao.AuthorityEntity;
import com.example.texnospring1222.dao.User;
import com.example.texnospring1222.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEnabled(),
                true, true, true, // account non-expired, non-locked, and credentials non-expired
                // Get authorities/roles for the user, you need to adapt this part based on your data structure
                getAuthorities(userEntity)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<AuthorityEntity> roles = user.getAuthorityEntityList();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (AuthorityEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return authorities;
    }

}
