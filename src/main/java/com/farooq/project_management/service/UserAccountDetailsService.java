package com.farooq.project_management.service;

import com.farooq.project_management.entity.UserAccount;
import com.farooq.project_management.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAccountDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);

        Set<GrantedAuthority> authorities = userAccount.getRole().stream()
                .map((roles) -> new SimpleGrantedAuthority(roles.getName()))
                .collect(Collectors.toSet());

        return new User(username, userAccount.getPassword(), authorities);
    }

    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
}
