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
public class EmployeeDepartmentService {
  private final static Logger logger = LoggerFactory.getLogger(EmployeeDepartmentService.class);
  @Autowired
  private EventSourceAdapter eventSourceAdapter;
  @Autowired
  private EmployeeDepartmentDAO employeeDepartmentDAO;

  @Transactional
  public void create(CreateEmployeeDepartmentCmd c, Principal p, URI u) {
    employeeDepartmentDAO.create(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public EmployeeDepartmentVo retrieveByRowid(RetrieveByRowidCmd c, Principal p, URI u) {
    eventSourceAdapter.publish(c, p, u);
    return employeeDepartmentDAO.retrieveByRowid(c);
  }

  @Transactional
  public EmployeeDepartmentVo retrieve(RetrieveEmployeeDepartmentCmd c, Principal p, URI u) {
    eventSourceAdapter.publish(c, p, u);
    return employeeDepartmentDAO.retrieve(c);
  }

  @Transactional
  public void update(UpdateEmployeeDepartmentCmd c, Principal p, URI u) {
    employeeDepartmentDAO.update(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public void delete(DeleteEmployeeDepartmentCmd c, Principal p, URI u) {
    employeeDepartmentDAO.delete(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public EmployeeDepartmentListVo query(QueryCommand q, Principal p, URI u) {
    eventSourceAdapter.publish(q, p, u);
    return employeeDepartmentDAO.query(q);
  }

}
