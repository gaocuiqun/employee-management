// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/myorg/employee/message/employee-management.proto

package com.myorg.employee.message;

public interface UserListVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.myorg.employee.message.UserListVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .com.myorg.employee.message.UserVo items = 1;</code>
   */
  java.util.List<com.myorg.employee.message.UserVo> 
      getItemsList();
  /**
   * <code>repeated .com.myorg.employee.message.UserVo items = 1;</code>
   */
  com.myorg.employee.message.UserVo getItems(int index);
  /**
   * <code>repeated .com.myorg.employee.message.UserVo items = 1;</code>
   */
  int getItemsCount();
  /**
   * <code>repeated .com.myorg.employee.message.UserVo items = 1;</code>
   */
  java.util.List<? extends com.myorg.employee.message.UserVoOrBuilder> 
      getItemsOrBuilderList();
  /**
   * <code>repeated .com.myorg.employee.message.UserVo items = 1;</code>
   */
  com.myorg.employee.message.UserVoOrBuilder getItemsOrBuilder(
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
