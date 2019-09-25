package com.myorg.employee.service;

import com.github.apuex.eventsource.EventSourceAdapter;
import com.github.apuex.springbootsolution.runtime.FilterPredicate;
import com.github.apuex.springbootsolution.runtime.QueryCommand;
import com.myorg.employee.dao.EmployeeDAO;
import com.myorg.employee.dao.EmployeeDepartmentDAO;
import com.myorg.employee.message.CreateEmployeeCmd;
import com.myorg.employee.message.EmployeeDepartmentListVo;
import com.myorg.employee.message.EmployeeVo;
import com.myorg.employee.message.RetrieveEmployeeCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.github.apuex.springbootsolution.runtime.MessageUtil.createPredicate;
import static com.github.apuex.springbootsolution.runtime.PredicateType.EQ;
import static com.myorg.employee.util.PasswordHash.hashed;

@Service
public class UserAuthService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UserAuthService.class);
    @Autowired
    private EventSourceAdapter eventSourceAdapter;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private EmployeeDepartmentDAO employeeDepartmentDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loading user: {}", username);
        try {
            EmployeeVo employeeVo = employeeDAO.retrieve(RetrieveEmployeeCmd.newBuilder()
                    .setId(username)
                    .build());

            if (employeeVo != null) {
                HashMap<String, String> params = new HashMap<>();
                FilterPredicate predicate = createPredicate(EQ, "name", "value", params);

                QueryCommand q = QueryCommand.newBuilder()
                        .setPredicate(predicate)
                        .putAllParams(params)
                        .build();

                User.UserBuilder userBuilder = User.builder()
                        .passwordEncoder(x -> hashed(x))
                        .username(employeeVo.getId())
                        .password(employeeVo.getPassword());

                EmployeeDepartmentListVo employeeDepartmentListVo = employeeDepartmentDAO.query(q);
                employeeDepartmentListVo.getItemsList()
                        .stream()
                        .map(x -> x.getDepartmentId())
                        .forEach(x -> userBuilder.roles(x));

                return userBuilder.build();
            } else {
                logger.info("no user: {}, creating...", username);
                employeeDAO.create(CreateEmployeeCmd.newBuilder()
                        .setId("user")
                        .setName("User A")
                        .setPassword("password")
                        .setDesc("Just A User")
                        .build());
                throw new UsernameNotFoundException("User not found.");
            }
        } catch (Exception sqlex) {
            logger.info("no user: {}, creating...", username);
            logger.info("no user: {}", username);
            employeeDAO.create(CreateEmployeeCmd.newBuilder()
                    .setId("user")
                    .setName("User A")
                    .setPassword("password")
                    .setDesc("Just A User")
                    .build());
            throw new UsernameNotFoundException("User not found.", sqlex);
        }
    }
}
