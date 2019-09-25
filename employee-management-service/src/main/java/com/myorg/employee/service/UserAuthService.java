package com.myorg.employee.service;

import com.myorg.employee.dao.UserDAO;
import com.myorg.employee.message.RetrieveUserCmd;
import com.myorg.employee.message.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserAuthService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UserAuthService.class);
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loading user: {}", username);
        try {
            UserVo employeeVo = userDAO.retrieve(RetrieveUserCmd.newBuilder()
                    .setName(username)
                    .build());

            return User.builder()
                    .username(employeeVo.getName())
                    .password("password")
                    .password(employeeVo.getPassword())
                    .roles("USER")
                    .build();

        } catch (Exception sqlex) {
            sqlex.printStackTrace();
            logger.info("no user: {}", username);
            throw new UsernameNotFoundException("User not found.", sqlex);
        }
    }
}
