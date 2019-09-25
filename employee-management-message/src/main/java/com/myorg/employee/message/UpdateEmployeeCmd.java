// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/myorg/employee/message/employee-management.proto

package com.myorg.employee.message;

/**
 * Protobuf type {@code com.myorg.employee.message.UpdateEmployeeCmd}
 */
public  final class UpdateEmployeeCmd extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.myorg.employee.message.UpdateEmployeeCmd)
    UpdateEmployeeCmdOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UpdateEmployeeCmd.newBuilder() to construct.
  private UpdateEmployeeCmd(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpdateEmployeeCmd() {
    id_ = "";
    name_ = "";
    gender_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpdateEmployeeCmd(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            id_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            gender_ = rawValue;
            break;
          }
          case 34: {
            com.google.protobuf.Timestamp.Builder subBuilder = null;
            if (employDate_ != null) {
              subBuilder = employDate_.toBuilder();
            }
            employDate_ = input.readMessage(com.google.protobuf.Timestamp.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(employDate_);
              employDate_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.myorg.employee.message.EmployeeManagement.internal_static_com_myorg_employee_message_UpdateEmployeeCmd_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.myorg.employee.message.EmployeeManagement.internal_static_com_myorg_employee_message_UpdateEmployeeCmd_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.myorg.employee.message.UpdateEmployeeCmd.class, com.myorg.employee.message.UpdateEmployeeCmd.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object id_;
  /**
   * <code>string id = 1;</code>
   */
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   */
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int GENDER_FIELD_NUMBER = 3;
  private int gender_;
  /**
   * <code>.com.myorg.employee.message.Gender gender = 3;</code>
   */
  public int getGenderValue() {
    return gender_;
  }
  /**
   * <code>.com.myorg.employee.message.Gender gender = 3;</code>
   */
  public com.myorg.employee.message.Gender getGender() {
    @SuppressWarnings("deprecation")
    com.myorg.employee.message.Gender result = com.myorg.employee.message.Gender.valueOf(gender_);
    return result == null ? com.myorg.employee.message.Gender.UNRECOGNIZED : result;
  }

  public static final int EMPLOYDATE_FIELD_NUMBER = 4;
  private com.google.protobuf.Timestamp employDate_;
  /**
   * <code>.google.protobuf.Timestamp employDate = 4;</code>
   */
  public boolean hasEmployDate() {
    return employDate_ != null;
  }
  /**
   * <code>.google.protobuf.Timestamp employDate = 4;</code>
   */
  public com.google.protobuf.Timestamp getEmployDate() {
    return employDate_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : employDate_;
  }
  /**
   * <code>.google.protobuf.Timestamp employDate = 4;</code>
   */
  public com.google.protobuf.TimestampOrBuilder getEmployDateOrBuilder() {
    return getEmployDate();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (gender_ != com.myorg.employee.message.Gender.UNKNOWN_GENDER.getNumber()) {
      output.writeEnum(3, gender_);
    }
    if (employDate_ != null) {
      output.writeMessage(4, getEmployDate());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (gender_ != com.myorg.employee.message.Gender.UNKNOWN_GENDER.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, gender_);
    }
    if (employDate_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getEmployDate());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.myorg.employee.message.UpdateEmployeeCmd)) {
      return super.equals(obj);
    }
    com.myorg.employee.message.UpdateEmployeeCmd other = (com.myorg.employee.message.UpdateEmployeeCmd) obj;

    if (!getId()
        .equals(other.getId())) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (gender_ != other.gender_) return false;
    if (hasEmployDate() != other.hasEmployDate()) return false;
    if (hasEmployDate()) {
      if (!getEmployDate()
          .equals(other.getEmployDate())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + GENDER_FIELD_NUMBER;
    hash = (53 * hash) + gender_;
    if (hasEmployDate()) {
      hash = (37 * hash) + EMPLOYDATE_FIELD_NUMBER;
      hash = (53 * hash) + getEmployDate().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.myorg.employee.message.UpdateEmployeeCmd parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.myorg.employee.message.UpdateEmployeeCmd prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.myorg.employee.message.UpdateEmployeeCmd}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.myorg.employee.message.UpdateEmployeeCmd)
      com.myorg.employee.message.UpdateEmployeeCmdOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.myorg.employee.message.EmployeeManagement.internal_static_com_myorg_employee_message_UpdateEmployeeCmd_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.myorg.employee.message.EmployeeManagement.internal_static_com_myorg_employee_message_UpdateEmployeeCmd_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.myorg.employee.message.UpdateEmployeeCmd.class, com.myorg.employee.message.UpdateEmployeeCmd.Builder.class);
    }

    // Construct using com.myorg.employee.message.UpdateEmployeeCmd.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = "";

      name_ = "";

      gender_ = 0;

      if (employDateBuilder_ == null) {
        employDate_ = null;
      } else {
        employDate_ = null;
        employDateBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.myorg.employee.message.EmployeeManagement.internal_static_com_myorg_employee_message_UpdateEmployeeCmd_descriptor;
    }

    @java.lang.Override
    public com.myorg.employee.message.UpdateEmployeeCmd getDefaultInstanceForType() {
      return com.myorg.employee.message.UpdateEmployeeCmd.getDefaultInstance();
    }

    @java.lang.Override
    public com.myorg.employee.message.UpdateEmployeeCmd build() {
      com.myorg.employee.message.UpdateEmployeeCmd result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.myorg.employee.message.UpdateEmployeeCmd buildPartial() {
      com.myorg.employee.message.UpdateEmployeeCmd result = new com.myorg.employee.message.UpdateEmployeeCmd(this);
      result.id_ = id_;
      result.name_ = name_;
      result.gender_ = gender_;
      if (employDateBuilder_ == null) {
        result.employDate_ = employDate_;
      } else {
        result.employDate_ = employDateBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.myorg.employee.message.UpdateEmployeeCmd) {
        return mergeFrom((com.myorg.employee.message.UpdateEmployeeCmd)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.myorg.employee.message.UpdateEmployeeCmd other) {
      if (other == com.myorg.employee.message.UpdateEmployeeCmd.getDefaultInstance()) return this;
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        onChanged();
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.gender_ != 0) {
        setGenderValue(other.getGenderValue());
      }
      if (other.hasEmployDate()) {
        mergeEmployDate(other.getEmployDate());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.myorg.employee.message.UpdateEmployeeCmd parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.myorg.employee.message.UpdateEmployeeCmd) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      id_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private int gender_ = 0;
    /**
     * <code>.com.myorg.employee.message.Gender gender = 3;</code>
     */
    public int getGenderValue() {
      return gender_;
    }
    /**
     * <code>.com.myorg.employee.message.Gender gender = 3;</code>
     */
    public Builder setGenderValue(int value) {
      gender_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.myorg.employee.message.Gender gender = 3;</code>
     */
    public com.myorg.employee.message.Gender getGender() {
      @SuppressWarnings("deprecation")
      com.myorg.employee.message.Gender result = com.myorg.employee.message.Gender.valueOf(gender_);
      return result == null ? com.myorg.employee.message.Gender.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.myorg.employee.message.Gender gender = 3;</code>
     */
    public Builder setGender(com.myorg.employee.message.Gender value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      gender_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.myorg.employee.message.Gender gender = 3;</code>
     */
    public Builder clearGender() {
      
      gender_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.Timestamp employDate_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> employDateBuilder_;
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public boolean hasEmployDate() {
      return employDateBuilder_ != null || employDate_ != null;
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public com.google.protobuf.Timestamp getEmployDate() {
      if (employDateBuilder_ == null) {
        return employDate_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : employDate_;
      } else {
        return employDateBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public Builder setEmployDate(com.google.protobuf.Timestamp value) {
      if (employDateBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        employDate_ = value;
        onChanged();
      } else {
        employDateBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public Builder setEmployDate(
        com.google.protobuf.Timestamp.Builder builderForValue) {
      if (employDateBuilder_ == null) {
        employDate_ = builderForValue.build();
        onChanged();
      } else {
        employDateBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public Builder mergeEmployDate(com.google.protobuf.Timestamp value) {
      if (employDateBuilder_ == null) {
        if (employDate_ != null) {
          employDate_ =
            com.google.protobuf.Timestamp.newBuilder(employDate_).mergeFrom(value).buildPartial();
        } else {
          employDate_ = value;
        }
        onChanged();
      } else {
        employDateBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public Builder clearEmployDate() {
      if (employDateBuilder_ == null) {
        employDate_ = null;
        onChanged();
      } else {
        employDate_ = null;
        employDateBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public com.google.protobuf.Timestamp.Builder getEmployDateBuilder() {
      
      onChanged();
      return getEmployDateFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    public com.google.protobuf.TimestampOrBuilder getEmployDateOrBuilder() {
      if (employDateBuilder_ != null) {
        return employDateBuilder_.getMessageOrBuilder();
      } else {
        return employDate_ == null ?
            com.google.protobuf.Timestamp.getDefaultInstance() : employDate_;
      }
    }
    /**
     * <code>.google.protobuf.Timestamp employDate = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
        getEmployDateFieldBuilder() {
      if (employDateBuilder_ == null) {
        employDateBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
                getEmployDate(),
                getParentForChildren(),
                isClean());
        employDate_ = null;
      }
      return employDateBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.myorg.employee.message.UpdateEmployeeCmd)
  }

  // @@protoc_insertion_point(class_scope:com.myorg.employee.message.UpdateEmployeeCmd)
  private static final com.myorg.employee.message.UpdateEmployeeCmd DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.myorg.employee.message.UpdateEmployeeCmd();
  }

  public static com.myorg.employee.message.UpdateEmployeeCmd getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UpdateEmployeeCmd>
      PARSER = new com.google.protobuf.AbstractParser<UpdateEmployeeCmd>() {
    @java.lang.Override
    public UpdateEmployeeCmd parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UpdateEmployeeCmd(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpdateEmployeeCmd> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdateEmployeeCmd> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.myorg.employee.message.UpdateEmployeeCmd getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

