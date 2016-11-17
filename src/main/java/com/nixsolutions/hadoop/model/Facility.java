/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.nixsolutions.hadoop.model;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Facility extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Facility\",\"namespace\":\"com.nixsolutions.hadoop.model\",\"fields\":[{\"name\":\"TruvenFacilityName\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"AcoId\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"TruvenFacilityId\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"CDAFacilityId\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"CdaClientCode\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String TruvenFacilityName;
  @Deprecated public java.lang.String AcoId;
  @Deprecated public java.lang.String TruvenFacilityId;
  @Deprecated public java.lang.String CDAFacilityId;
  @Deprecated public java.lang.String CdaClientCode;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Facility() {}

  /**
   * All-args constructor.
   *
   */
  public Facility(java.lang.String TruvenFacilityName, java.lang.String AcoId, java.lang.String TruvenFacilityId, java.lang.String CDAFacilityId, java.lang.String CdaClientCode) {
    this.TruvenFacilityName = TruvenFacilityName;
    this.AcoId = AcoId;
    this.TruvenFacilityId = TruvenFacilityId;
    this.CDAFacilityId = CDAFacilityId;
    this.CdaClientCode = CdaClientCode;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return TruvenFacilityName;
    case 1: return AcoId;
    case 2: return TruvenFacilityId;
    case 3: return CDAFacilityId;
    case 4: return CdaClientCode;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: TruvenFacilityName = (java.lang.String)value$; break;
    case 1: AcoId = (java.lang.String)value$; break;
    case 2: TruvenFacilityId = (java.lang.String)value$; break;
    case 3: CDAFacilityId = (java.lang.String)value$; break;
    case 4: CdaClientCode = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'TruvenFacilityName' field.
   */
  public java.lang.String getTruvenFacilityName() {
    return TruvenFacilityName;
  }

  /**
   * Sets the value of the 'TruvenFacilityName' field.
   * @param value the value to set.
   */
  public void setTruvenFacilityName(java.lang.String value) {
    this.TruvenFacilityName = value;
  }

  /**
   * Gets the value of the 'AcoId' field.
   */
  public java.lang.String getAcoId() {
    return AcoId;
  }

  /**
   * Sets the value of the 'AcoId' field.
   * @param value the value to set.
   */
  public void setAcoId(java.lang.String value) {
    this.AcoId = value;
  }

  /**
   * Gets the value of the 'TruvenFacilityId' field.
   */
  public java.lang.String getTruvenFacilityId() {
    return TruvenFacilityId;
  }

  /**
   * Sets the value of the 'TruvenFacilityId' field.
   * @param value the value to set.
   */
  public void setTruvenFacilityId(java.lang.String value) {
    this.TruvenFacilityId = value;
  }

  /**
   * Gets the value of the 'CDAFacilityId' field.
   */
  public java.lang.String getCDAFacilityId() {
    return CDAFacilityId;
  }

  /**
   * Sets the value of the 'CDAFacilityId' field.
   * @param value the value to set.
   */
  public void setCDAFacilityId(java.lang.String value) {
    this.CDAFacilityId = value;
  }

  /**
   * Gets the value of the 'CdaClientCode' field.
   */
  public java.lang.String getCdaClientCode() {
    return CdaClientCode;
  }

  /**
   * Sets the value of the 'CdaClientCode' field.
   * @param value the value to set.
   */
  public void setCdaClientCode(java.lang.String value) {
    this.CdaClientCode = value;
  }

  /** Creates a new Facility RecordBuilder */
  public static com.nixsolutions.hadoop.model.Facility.Builder newBuilder() {
    return new com.nixsolutions.hadoop.model.Facility.Builder();
  }
  
  /** Creates a new Facility RecordBuilder by copying an existing Builder */
  public static com.nixsolutions.hadoop.model.Facility.Builder newBuilder(com.nixsolutions.hadoop.model.Facility.Builder other) {
    return new com.nixsolutions.hadoop.model.Facility.Builder(other);
  }
  
  /** Creates a new Facility RecordBuilder by copying an existing Facility instance */
  public static com.nixsolutions.hadoop.model.Facility.Builder newBuilder(com.nixsolutions.hadoop.model.Facility other) {
    return new com.nixsolutions.hadoop.model.Facility.Builder(other);
  }
  
  /**
   * RecordBuilder for Facility instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Facility>
    implements org.apache.avro.data.RecordBuilder<Facility> {

    private java.lang.String TruvenFacilityName;
    private java.lang.String AcoId;
    private java.lang.String TruvenFacilityId;
    private java.lang.String CDAFacilityId;
    private java.lang.String CdaClientCode;

    /** Creates a new Builder */
    private Builder() {
      super(com.nixsolutions.hadoop.model.Facility.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.nixsolutions.hadoop.model.Facility.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.TruvenFacilityName)) {
        this.TruvenFacilityName = data().deepCopy(fields()[0].schema(), other.TruvenFacilityName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.AcoId)) {
        this.AcoId = data().deepCopy(fields()[1].schema(), other.AcoId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.TruvenFacilityId)) {
        this.TruvenFacilityId = data().deepCopy(fields()[2].schema(), other.TruvenFacilityId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.CDAFacilityId)) {
        this.CDAFacilityId = data().deepCopy(fields()[3].schema(), other.CDAFacilityId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.CdaClientCode)) {
        this.CdaClientCode = data().deepCopy(fields()[4].schema(), other.CdaClientCode);
        fieldSetFlags()[4] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Facility instance */
    private Builder(com.nixsolutions.hadoop.model.Facility other) {
            super(com.nixsolutions.hadoop.model.Facility.SCHEMA$);
      if (isValidValue(fields()[0], other.TruvenFacilityName)) {
        this.TruvenFacilityName = data().deepCopy(fields()[0].schema(), other.TruvenFacilityName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.AcoId)) {
        this.AcoId = data().deepCopy(fields()[1].schema(), other.AcoId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.TruvenFacilityId)) {
        this.TruvenFacilityId = data().deepCopy(fields()[2].schema(), other.TruvenFacilityId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.CDAFacilityId)) {
        this.CDAFacilityId = data().deepCopy(fields()[3].schema(), other.CDAFacilityId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.CdaClientCode)) {
        this.CdaClientCode = data().deepCopy(fields()[4].schema(), other.CdaClientCode);
        fieldSetFlags()[4] = true;
      }
    }

    /** Gets the value of the 'TruvenFacilityName' field */
    public java.lang.String getTruvenFacilityName() {
      return TruvenFacilityName;
    }
    
    /** Sets the value of the 'TruvenFacilityName' field */
    public com.nixsolutions.hadoop.model.Facility.Builder setTruvenFacilityName(java.lang.String value) {
      validate(fields()[0], value);
      this.TruvenFacilityName = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'TruvenFacilityName' field has been set */
    public boolean hasTruvenFacilityName() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'TruvenFacilityName' field */
    public com.nixsolutions.hadoop.model.Facility.Builder clearTruvenFacilityName() {
      TruvenFacilityName = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'AcoId' field */
    public java.lang.String getAcoId() {
      return AcoId;
    }
    
    /** Sets the value of the 'AcoId' field */
    public com.nixsolutions.hadoop.model.Facility.Builder setAcoId(java.lang.String value) {
      validate(fields()[1], value);
      this.AcoId = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'AcoId' field has been set */
    public boolean hasAcoId() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'AcoId' field */
    public com.nixsolutions.hadoop.model.Facility.Builder clearAcoId() {
      AcoId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'TruvenFacilityId' field */
    public java.lang.String getTruvenFacilityId() {
      return TruvenFacilityId;
    }
    
    /** Sets the value of the 'TruvenFacilityId' field */
    public com.nixsolutions.hadoop.model.Facility.Builder setTruvenFacilityId(java.lang.String value) {
      validate(fields()[2], value);
      this.TruvenFacilityId = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'TruvenFacilityId' field has been set */
    public boolean hasTruvenFacilityId() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'TruvenFacilityId' field */
    public com.nixsolutions.hadoop.model.Facility.Builder clearTruvenFacilityId() {
      TruvenFacilityId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'CDAFacilityId' field */
    public java.lang.String getCDAFacilityId() {
      return CDAFacilityId;
    }
    
    /** Sets the value of the 'CDAFacilityId' field */
    public com.nixsolutions.hadoop.model.Facility.Builder setCDAFacilityId(java.lang.String value) {
      validate(fields()[3], value);
      this.CDAFacilityId = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'CDAFacilityId' field has been set */
    public boolean hasCDAFacilityId() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'CDAFacilityId' field */
    public com.nixsolutions.hadoop.model.Facility.Builder clearCDAFacilityId() {
      CDAFacilityId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'CdaClientCode' field */
    public java.lang.String getCdaClientCode() {
      return CdaClientCode;
    }
    
    /** Sets the value of the 'CdaClientCode' field */
    public com.nixsolutions.hadoop.model.Facility.Builder setCdaClientCode(java.lang.String value) {
      validate(fields()[4], value);
      this.CdaClientCode = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'CdaClientCode' field has been set */
    public boolean hasCdaClientCode() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'CdaClientCode' field */
    public com.nixsolutions.hadoop.model.Facility.Builder clearCdaClientCode() {
      CdaClientCode = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    public Facility build() {
      try {
        Facility record = new Facility();
        record.TruvenFacilityName = fieldSetFlags()[0] ? this.TruvenFacilityName : (java.lang.String) defaultValue(fields()[0]);
        record.AcoId = fieldSetFlags()[1] ? this.AcoId : (java.lang.String) defaultValue(fields()[1]);
        record.TruvenFacilityId = fieldSetFlags()[2] ? this.TruvenFacilityId : (java.lang.String) defaultValue(fields()[2]);
        record.CDAFacilityId = fieldSetFlags()[3] ? this.CDAFacilityId : (java.lang.String) defaultValue(fields()[3]);
        record.CdaClientCode = fieldSetFlags()[4] ? this.CdaClientCode : (java.lang.String) defaultValue(fields()[4]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
