// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/myorg/employee/message/employee-management.proto

package com.myorg.employee.message;

public interface EmployeeVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.myorg.employee.message.EmployeeVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.com.myorg.employee.message.Gender gender = 3;</code>
   */
  int getGenderValue();
  /**
   * <code>.com.myorg.employee.message.Gender gender = 3;</code>
   */
  com.myorg.employee.message.Gender getGender();

  /**
   * <code>.google.protobuf.Timestamp employDate = 4;</code>
   */
  boolean hasEmployDate();
  /**
   * <code>.google.protobuf.Timestamp employDate = 4;</code>
   */
  com.google.protobuf.Timestamp getEmployDate();
  /**
   * <code>.google.protobuf.Timestamp employDate = 4;</code>
   */
  com.google.protobuf.TimestampOrBuilder getEmployDateOrBuilder();
}
