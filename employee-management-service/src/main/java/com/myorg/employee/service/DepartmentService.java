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
public class DepartmentService {
  private final static Logger logger = LoggerFactory.getLogger(DepartmentService.class);
  @Autowired
  private EventSourceAdapter eventSourceAdapter;
  @Autowired
  private DepartmentDAO departmentDAO;

  @Transactional
  public void create(CreateDepartmentCmd c, Principal p, URI u) {
    departmentDAO.create(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public DepartmentVo retrieveByRowid(RetrieveByRowidCmd c, Principal p, URI u) {
    eventSourceAdapter.publish(c, p, u);
    return departmentDAO.retrieveByRowid(c);
  }

  @Transactional
  public DepartmentVo retrieve(RetrieveDepartmentCmd c, Principal p, URI u) {
    eventSourceAdapter.publish(c, p, u);
    return departmentDAO.retrieve(c);
  }

  @Transactional
  public void update(UpdateDepartmentCmd c, Principal p, URI u) {
    departmentDAO.update(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public void delete(DeleteDepartmentCmd c, Principal p, URI u) {
    departmentDAO.delete(c);
    eventSourceAdapter.publish(c, p, u);
  }

  @Transactional
  public DepartmentListVo query(QueryCommand q, Principal p, URI u) {
    eventSourceAdapter.publish(q, p, u);
    return departmentDAO.query(q);
  }

}
