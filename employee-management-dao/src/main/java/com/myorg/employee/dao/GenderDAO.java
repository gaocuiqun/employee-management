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
public class GenderDAO {

  private final static Logger logger = LoggerFactory.getLogger(GenderDAO.class);
  private final WhereClauseWithUnnamedParams where = new WhereClauseWithUnnamedParams(new CamelToCConverter());
  @Autowired
  private final JdbcTemplate jdbcTemplate;
  public static class ParamMapper implements QueryParamMapper {
    private final Map<String, TypeConverter> mappers;

    public ParamMapper() {
      Map<String, TypeConverter> map = new HashMap<>();
      map.put("id", TypeConverters.toJavaTypeConverter("int"));
      map.put("name", TypeConverters.toJavaTypeConverter("string"));
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
  public static class ResultRowMapper implements RowMapper<GenderVo> {
    public GenderVo mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
      GenderVo.Builder builder = GenderVo.newBuilder();
      builder.setId(rs.getInt("id"));
      if(null != rs.getString("name")) builder.setName(rs.getString("name"));

      return builder.build();
    }
  }
  private final RowMapper<GenderVo> rowMapper = new ResultRowMapper();

  public GenderDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int create(CreateGenderCmd c) {
    int rowsAffected = jdbcTemplate.update("UPDATE employeedb.Gender SET name = ? WHERE id = ?", c.getName(), c.getId());
    if(rowsAffected > 0) {
      return rowsAffected;
    } else {
      return jdbcTemplate.update("INSERT INTO employeedb.Gender(id,name) VALUES (?,?)", c.getId(),c.getName());
    }
  }

  public GenderVo retrieveByRowid(RetrieveByRowidCmd c) {
    return (GenderVo) jdbcTemplate.queryForObject("SELECT id, name FROM employeedb.Gender WHERE rowid = ? ", rowMapper, c.getRowid());
  }

  public GenderVo retrieve(RetrieveGenderCmd c) {
    return (GenderVo) jdbcTemplate.queryForObject("SELECT id, name FROM employeedb.Gender WHERE id = ? ", rowMapper, c.getId());
  }

  public int update(UpdateGenderCmd c) {
    throw new UnsupportedOperationException();
  }

  public int delete(DeleteGenderCmd c) {
    return jdbcTemplate.update("DELETE FROM employeedb.Gender WHERE id = ?", c.getId());
  }

  public GenderListVo query(QueryCommand q) {
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
      String sql = String.format("SELECT id, name "
          + "FROM employeedb.Gender %s "
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
      return GenderListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, params.toArray()))
        .setHasMore(!(jdbcTemplate.query(sql, rowMapper, moreParams.toArray()).isEmpty()))
        .build();
    } else {
      String sql = String.format("SELECT id, name FROM employeedb.Gender %s ", where.toWhereClause(q));
      logger.info(sql);
      return GenderListVo.newBuilder()
        .addAllItems(jdbcTemplate.query(sql, rowMapper, where.toUnnamedParamList(q, paramMapper).toArray()))
        .build();
    }
  }

}
