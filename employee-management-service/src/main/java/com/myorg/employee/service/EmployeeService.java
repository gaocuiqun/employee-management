package com.myorg.employee.service;

import com.github.apuex.eventsource.*;
import com.github.apuex.springbootsolution.runtime.*;
import com.myorg.employee.message.*;
import com.myorg.employee.dao.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.net.*;
import java.util.*;
import java.security.*;

@Component
public class EmployeeService {
  private final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
  @Autowired
  private EventSourceAdapter eventSourceAdapter;
  @Autowired
  private EmployeeDAO employeeDAO;

  @Transactional
  public void create(CreateEmployeeCmd c, Principal p, URI u) {
    employeeDAO.create(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public EmployeeVo retrieveByRowid(RetrieveByRowidCmd c, Principal p, URI u) {
    eventSourceAdapter.publish(c, p, u);
    return employeeDAO.retrieveByRowid(c);
  }

  @Transactional
  public EmployeeVo retrieve(RetrieveEmployeeCmd c, Principal p, URI u) {
    eventSourceAdapter.publish(c, p, u);
    return employeeDAO.retrieve(c);
  }

  @Transactional
  public void update(UpdateEmployeeCmd c, Principal p, URI u) {
    employeeDAO.update(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public void delete(DeleteEmployeeCmd c, Principal p, URI u) {
    employeeDAO.delete(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public EmployeeListVo query(QueryCommand q, Principal p, URI u) {
    eventSourceAdapter.publish(q, p, u);
    return employeeDAO.query(q);
  }

}
