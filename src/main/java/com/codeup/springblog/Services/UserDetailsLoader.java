package com.codeup.springblog.Services;

import com.codeup.springblog.Models.User;
import com.codeup.springblog.Models.UserWithRoles;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService{
    private final UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = users.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("No user found for " + username);
            }
            return new UserWithRoles(user);
        }
}
