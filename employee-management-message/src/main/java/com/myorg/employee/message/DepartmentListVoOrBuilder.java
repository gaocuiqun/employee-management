// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/myorg/employee/message/employee-management.proto

package com.myorg.employee.message;

public interface DepartmentListVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.myorg.employee.message.DepartmentListVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .com.myorg.employee.message.DepartmentVo items = 1;</code>
   */
  java.util.List<com.myorg.employee.message.DepartmentVo> 
      getItemsList();
  /**
   * <code>repeated .com.myorg.employee.message.DepartmentVo items = 1;</code>
   */
  com.myorg.employee.message.DepartmentVo getItems(int index);
  /**
   * <code>repeated .com.myorg.employee.message.DepartmentVo items = 1;</code>
   */
  int getItemsCount();
  /**
   * <code>repeated .com.myorg.employee.message.DepartmentVo items = 1;</code>
   */
  java.util.List<? extends com.myorg.employee.message.DepartmentVoOrBuilder> 
      getItemsOrBuilderList();
  /**
   * <code>repeated .com.myorg.employee.message.DepartmentVo items = 1;</code>
   */
  com.myorg.employee.message.DepartmentVoOrBuilder getItemsOrBuilder(
      int index);

  /**
   * <code>bool hasMore = 2;</code>
   */
  boolean getHasMore();

  /**
   * <code>string pagingState = 3;</code>
   */
  java.lang.String getPagingState();
  /**
   * <code>string pagingState = 3;</code>
   */
  com.google.protobuf.ByteString
      getPagingStateBytes();
}
