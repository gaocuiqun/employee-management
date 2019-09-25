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
public class UserDAO {

  private final static Logger logger = LoggerFactory.getLogger(UserDAO.class);
  private final WhereClauseWithUnnamedParams where = new WhereClauseWithUnnamedParams(new CamelToCConverter());
  @Autowired
  private final JdbcTemplate jdbcTemplate;
  public static class ParamMapper implements QueryParamMapper {
    private final Map<String, TypeConverter> mappers;

    public ParamMapper() {
      Map<String, TypeConverter> map = new HashMap<>();
      map.put("name", TypeConverters.toJavaTypeConverter("string"));
      map.put("password", TypeConverters.toJavaTypeConverter("string"));
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
  public static class ResultRowMapper implements RowMapper<UserVo> {
    public UserVo mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
      UserVo.Builder builder = UserVo.newBuilder();
      if(null != rs.getString("name")) builder.setName(rs.getString("name"));
      if(null != rs.getString("password")) builder.setPassword(rs.getString("password"));

      return builder.build();
    }
  }
  private final RowMapper<UserVo> rowMapper = new ResultRowMapper();

  public UserDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int create(CreateUserCmd c) {
    int rowsAffected = jdbcTemplate.update("UPDATE employeedb.user SET password = ? WHERE name = ?", c.getPassword(), c.getName());
    if(rowsAffected > 0) {
      return rowsAffected;
    } else {
      return jdbcTemplate.update("INSERT INTO employeedb.user(name,password) VALUES (?,?)", c.getName(),c.getPassword());
    }
  }

  public UserVo retrieveByRowid(RetrieveByRowidCmd c) {
    return (UserVo) jdbcTemplate.queryForObject("SELECT name, password FROM employeedb.user WHERE rowid = ? ", rowMapper, c.getRowid());
  }

  public UserVo retrieve(RetrieveUserCmd c) {
    return (UserVo) jdbcTemplate.queryForObject("SELECT name, password FROM employeedb.user WHERE name = ? ", rowMapper, c.getName());
  }

  public int update(UpdateUserCmd c) {
    return jdbcTemplate.update("UPDATE employeedb.user SET password = ? WHERE name = ?", c.getPassword(), c.getName());
  }

  public int delete(DeleteUserCmd c) {
    return jdbcTemplate.update("DELETE FROM employeedb.user WHERE name = ?", c.getName());
  }

  public UserListVo query(QueryCommand q) {
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
      String sql = String.format("SELECT name, password "
          + "FROM employeedb.user %s "
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
      return UserListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, params.toArray()))
        .setHasMore(!(jdbcTemplate.query(sql, rowMapper, moreParams.toArray()).isEmpty()))
        .build();
    } else {
      String sql = String.format("SELECT name, password FROM employeedb.user %s ", where.toWhereClause(q));
      logger.info(sql);
      return UserListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, where.toUnnamedParamList(q, paramMapper).toArray()))
        .build();
    }
  }

}
