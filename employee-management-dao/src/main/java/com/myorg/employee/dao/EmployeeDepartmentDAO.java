package com.myorg.employee.dao;

import com.github.apuex.springbootsolution.runtime.*;
import static com.github.apuex.springbootsolution.runtime.DateFormat.*;
import com.myorg.employee.message.*;
import com.github.apuex.springbootsolution.runtime.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Component
public class EmployeeDepartmentDAO {

  private final static Logger logger = LoggerFactory.getLogger(EmployeeDepartmentDAO.class);
  private final WhereClauseWithUnnamedParams where = new WhereClauseWithUnnamedParams(new CamelToCConverter());
  @Autowired
  private final JdbcTemplate jdbcTemplate;
  public static class ParamMapper implements QueryParamMapper {
    private final Map<String, TypeConverter> mappers;

    public ParamMapper() {
      Map<String, TypeConverter> map = new HashMap<>();
      map.put("employeeId", TypeConverters.toJavaTypeConverter("string"));
      map.put("departmentId", TypeConverters.toJavaTypeConverter("string"));
      this.mappers = map;
    }

    public Object map(String name, String value) {
      TypeConverter c = mappers.get(name);
      if(null == c) {
        logger.error("No such a field: {}", name);
      }
      return c.convert(value);
    }

    public boolean exists(String name) {
      return mappers.containsKey(name);
    }
  }
  private final QueryParamMapper paramMapper = new ParamMapper();
  public static class ResultRowMapper implements RowMapper<EmployeeDepartmentVo> {
    public EmployeeDepartmentVo mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
      EmployeeDepartmentVo.Builder builder = EmployeeDepartmentVo.newBuilder();
      if(null != rs.getString("employee_id")) builder.setEmployeeId(rs.getString("employee_id"));
      if(null != rs.getString("department_id")) builder.setDepartmentId(rs.getString("department_id"));

      return builder.build();
    }
  }
  private final RowMapper<EmployeeDepartmentVo> rowMapper = new ResultRowMapper();

  public EmployeeDepartmentDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int create(CreateEmployeeDepartmentCmd c) {
    int rowsAffected = jdbcTemplate.update("UPDATE employeedb.employee_department SET  WHERE employee_id = ? AND department_id = ?", c.getEmployeeId(), c.getDepartmentId());
    if(rowsAffected > 0) {
      return rowsAffected;
    } else {
      return jdbcTemplate.update("INSERT INTO employeedb.employee_department(employee_id,department_id) VALUES (?,?)", c.getEmployeeId(),c.getDepartmentId());
    }
  }

  public EmployeeDepartmentVo retrieveByRowid(RetrieveByRowidCmd c) {
    return (EmployeeDepartmentVo) jdbcTemplate.queryForObject("SELECT employee_id, department_id FROM employeedb.employee_department WHERE rowid = ? ", rowMapper, c.getRowid());
  }

  public EmployeeDepartmentVo retrieve(RetrieveEmployeeDepartmentCmd c) {
    return (EmployeeDepartmentVo) jdbcTemplate.queryForObject("SELECT employee_id, department_id FROM employeedb.employee_department WHERE employee_id = ? AND department_id = ? ", rowMapper, c.getEmployeeId(),c.getDepartmentId());
  }

  public int update(UpdateEmployeeDepartmentCmd c) {
    return jdbcTemplate.update("UPDATE employeedb.employee_department SET  WHERE employee_id = ? AND department_id = ?", c.getEmployeeId(), c.getDepartmentId());
  }

  public int delete(DeleteEmployeeDepartmentCmd c) {
    return jdbcTemplate.update("DELETE FROM employeedb.employee_department WHERE employee_id = ? AND department_id = ?", c.getEmployeeId(),c.getDepartmentId());
  }

  public EmployeeDepartmentListVo query(QueryCommand q) {
    if(q.getPageNumber() > 0
      && q.getRowsPerPage() > 0
      && q.getOrderByCount() > 0) {
      if(!(q.getOrderByList().stream()
        .map(x -> paramMapper.exists(x.getFieldName()))
        .reduce((x, y) -> x && y)
        .get())) throw new RuntimeException("Invalid order by field.");
      String orderBy = q.getOrderByList().stream()
          .map(x -> String.format("%s %s", x.getFieldName(), x.getOrder()))
          .reduce((x, y) -> String.format("%s, %s", x, y))
          .get();
      String sql = String.format("SELECT employee_id, department_id "
          + "FROM employeedb.employee_department %s "
          + "ORDER BY %s "
          + "LIMIT ? OFFSET ?",
          where.toWhereClause(q),
          orderBy);
      List<Object> params = new LinkedList<>(where.toUnnamedParamList(q, paramMapper));
      Integer beginRow = Integer.valueOf((q.getPageNumber() - 1) * q.getRowsPerPage());
      Integer endRow = Integer.valueOf(q.getPageNumber() * q.getRowsPerPage());
      List<Object> moreParams = new LinkedList<>(params);
      params.add(q.getRowsPerPage());
      params.add(beginRow);
      moreParams.add(endRow);
      moreParams.add(endRow + 1);
      logger.info(sql);
      jdbcTemplate.query(sql, rowMapper, params.toArray());
      return EmployeeDepartmentListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, params.toArray()))
        .setHasMore(!(jdbcTemplate.query(sql, rowMapper, moreParams.toArray()).isEmpty()))
        .build();
    } else {
      String sql = String.format("SELECT employee_id, department_id FROM employeedb.employee_department %s ", where.toWhereClause(q));
      logger.info(sql);
      return EmployeeDepartmentListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, where.toUnnamedParamList(q, paramMapper).toArray()))
        .build();
    }
  }

}
