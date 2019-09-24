package com.myorg.employee.controller;

import com.github.apuex.springbootsolution.runtime.*;
import static com.github.apuex.springbootsolution.runtime.DateFormat.*;
import com.myorg.employee.message.*;
import com.myorg.employee.service.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping(value="employee-department", method=RequestMethod.POST)
public class EmployeeDepartmentController {
  @Autowired
  private EmployeeDepartmentService service;

  @RequestMapping(value="create-employee-department", produces="application/json")
  public void create(@RequestBody CreateEmployeeDepartmentCmd c, HttpServletRequest r) throws URISyntaxException {
    service.create(c, r.getUserPrincipal(), new URI(r.getRequestURI()));
  }

  @RequestMapping(value="retrieve-employee-department-by-rowid", produces="application/json")
  public EmployeeDepartmentVo retrieveByRowid(@RequestBody RetrieveByRowidCmd c, HttpServletRequest r) throws URISyntaxException {
    return service.retrieveByRowid(c, r.getUserPrincipal(), new URI(r.getRequestURI()));
  }
  
  @RequestMapping(value="retrieve-employee-department", produces="application/json")
  public EmployeeDepartmentVo retrieve(@RequestBody RetrieveEmployeeDepartmentCmd c, HttpServletRequest r) throws URISyntaxException {
    return service.retrieve(c, r.getUserPrincipal(), new URI(r.getRequestURI()));
  }
  
  @RequestMapping(value="update-employee-department", produces="application/json")
  public void update(@RequestBody UpdateEmployeeDepartmentCmd c, HttpServletRequest r) throws URISyntaxException {
    service.update(c, r.getUserPrincipal(), new URI(r.getRequestURI()));
  }

  @RequestMapping(value="delete-employee-department", produces="application/json")
  public void delete(@RequestBody DeleteEmployeeDepartmentCmd c, HttpServletRequest r) throws URISyntaxException {
    service.delete(c, r.getUserPrincipal(), new URI(r.getRequestURI()));
  }

  @RequestMapping(value="query-employee-department", produces="application/json")
  public EmployeeDepartmentListVo query(@RequestBody QueryCommand q, HttpServletRequest r) throws URISyntaxException {
    return service.query(q, r.getUserPrincipal(), new URI(r.getRequestURI()));
  }

  @RequestMapping(value="export-employee-department", consumes="application/json")
  public void export(@RequestBody QueryCommand q, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {
    final EmployeeDepartmentListVo listVo = service.query(q, request.getUserPrincipal(), new URI(request.getRequestURI()));
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("EmployeeDepartment");

    HSSFCellStyle style = wb.createCellStyle();
    style.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.AQUA.getIndex());
    style.setFillPattern(FillPatternType.BIG_SPOTS);

    style = wb.createCellStyle();
    style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.ORANGE.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    short rowNumber = 0;
    exportHeaderCells(sheet.createRow(rowNumber++), style);
    for(EmployeeDepartmentVo vo: listVo.getItemsList()) {
      HSSFRow row = sheet.createRow(rowNumber++);
      exportDataCells(vo, row, style);
    }

    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-disposition",String.format("attachment; filename=%sList-%s.xls", "EmployeeDepartment", formatTimestamp(new Date())));
    wb.write(response.getOutputStream());
  }

  private void exportHeaderCells(HSSFRow row, HSSFCellStyle style) {
    short colNumber = 0;
    HSSFCell cell = null;
    cell = row.createCell(colNumber++);
    cell.setCellValue("EmployeeId");
    cell.setCellStyle(style);
    cell = row.createCell(colNumber++);
    cell.setCellValue("DepartmentId");
    cell.setCellStyle(style);
  }

  private void exportDataCells(EmployeeDepartmentVo vo, HSSFRow row, HSSFCellStyle style) {
    short colNumber = 0;
    HSSFCell cell = null;
    cell = row.createCell(colNumber++);
    cell.setCellValue(String.format("%s", vo.getEmployeeId()));
    cell.setCellStyle(style);
    cell = row.createCell(colNumber++);
    cell.setCellValue(String.format("%s", vo.getDepartmentId()));
    cell.setCellStyle(style);
  }
}
