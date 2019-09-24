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
public class DepartmentDAO {

  private final static Logger logger = LoggerFactory.getLogger(DepartmentDAO.class);
  private final WhereClauseWithUnnamedParams where = new WhereClauseWithUnnamedParams(new CamelToCConverter());
  @Autowired
  private final JdbcTemplate jdbcTemplate;
  public static class ParamMapper implements QueryParamMapper {
    private final Map<String, TypeConverter> mappers;

    public ParamMapper() {
      Map<String, TypeConverter> map = new HashMap<>();
      map.put("id", TypeConverters.toJavaTypeConverter("string"));
      map.put("name", TypeConverters.toJavaTypeConverter("string"));
      map.put("desc", TypeConverters.toJavaTypeConverter("string"));
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
  public static class ResultRowMapper implements RowMapper<DepartmentVo> {
    public DepartmentVo mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
      DepartmentVo.Builder builder = DepartmentVo.newBuilder();
      if(null != rs.getString("id")) builder.setId(rs.getString("id"));
      if(null != rs.getString("name")) builder.setName(rs.getString("name"));
      if(null != rs.getString("desc")) builder.setDesc(rs.getString("desc"));

      return builder.build();
    }
  }
  private final RowMapper<DepartmentVo> rowMapper = new ResultRowMapper();

  public DepartmentDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int create(CreateDepartmentCmd c) {
    int rowsAffected = jdbcTemplate.update("UPDATE employeedb.department SET name = ?, desc = ? WHERE id = ?", c.getName(), c.getDesc(), c.getId());
    if(rowsAffected > 0) {
      return rowsAffected;
    } else {
      return jdbcTemplate.update("INSERT INTO employeedb.department(id,name,desc) VALUES (?,?,?)", c.getId(),c.getName(),c.getDesc());
    }
  }

  public DepartmentVo retrieveByRowid(RetrieveByRowidCmd c) {
    return (DepartmentVo) jdbcTemplate.queryForObject("SELECT id, name, desc FROM employeedb.department WHERE rowid = ? ", rowMapper, c.getRowid());
  }

  public DepartmentVo retrieve(RetrieveDepartmentCmd c) {
    return (DepartmentVo) jdbcTemplate.queryForObject("SELECT id, name, desc FROM employeedb.department WHERE id = ? ", rowMapper, c.getId());
  }

  public int update(UpdateDepartmentCmd c) {
    return jdbcTemplate.update("UPDATE employeedb.department SET name = ?, desc = ? WHERE id = ?", c.getName(), c.getDesc(), c.getId());
  }

  public int delete(DeleteDepartmentCmd c) {
    return jdbcTemplate.update("DELETE FROM employeedb.department WHERE id = ?", c.getId());
  }

  public DepartmentListVo query(QueryCommand q) {
    if(q.getPageNumber() > 0
      && q.getRowsPerPage() > 0
      && q.getOrderByCount() > 0) {
      if(!(q.getOrderByList().stream()
        .map(x -> paramMapper.exists(x.getFieldName()))
        .reduce((x, y) -> x && y)
        .get())) throw new RuntimeException("Invalid order by field.");
      String orderBy = q.getOrderByList().stream()
          .map(x -> String.format("%s %s", SymbolConverters.cToPascal().apply(x.getFieldName()), x.getOrder()))
          .reduce((x, y) -> String.format("%s, %s", x, y))
          .get();
      String sql = String.format("WITH Paginateddepartment AS ("
          + "SELECT ROW_NUMBER() OVER (ORDER BY %s) AS RowNumber, "
          + "id, name, desc "
          + "FROM employeedb.department %s "
          + ")"
          + "SELECT id, name, desc "
          + "FROM Paginateddepartment "
          + "WHERE RowNumber > ? AND RowNumber <= ?",
          orderBy,
          where.toWhereClause(q));
      List<Object> params = new LinkedList<>(where.toUnnamedParamList(q, paramMapper));
      Integer beginRow = Integer.valueOf((q.getPageNumber() == 0 ? 0 : (q.getPageNumber() - 1)) * q.getRowsPerPage());
      Integer endRow = Integer.valueOf(q.getPageNumber() * q.getRowsPerPage());
      List<Object> moreParams = new LinkedList<>(params);
      params.add(beginRow);
      params.add(endRow);
      moreParams.add(endRow);
      moreParams.add(endRow + 1);
      logger.info(sql);
      jdbcTemplate.query(sql, rowMapper, params.toArray());
      return DepartmentListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, params.toArray()))
        .setHasMore(!(jdbcTemplate.query(sql, rowMapper, moreParams.toArray()).isEmpty()))
        .build();
    } else {
      String sql = String.format("SELECT id, name, desc FROM employeedb.department %s ", where.toWhereClause(q));
      logger.info(sql);
      return DepartmentListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, where.toUnnamedParamList(q, paramMapper).toArray()))
        .build();
    }
  }

}
