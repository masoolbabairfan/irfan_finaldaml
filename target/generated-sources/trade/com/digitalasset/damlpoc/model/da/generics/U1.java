package com.digitalasset.damlpoc.model.da.generics;

import com.daml.ledger.javaapi.data.Record;
import com.daml.ledger.javaapi.data.Value;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class U1<p> {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public U1() {
  }

  public static <p> U1<p> fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 0) {
      throw new IllegalArgumentException("Expected 0 arguments, got " + numberOfFields);
    }
    return new com.digitalasset.damlpoc.model.da.generics.U1<p>();
  }

  public Record toValue() {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(0);
    return new Record(fields);
  }

  public static <p> U1<p> fromValue(Value value$, Function<Value, p> fromValuep) throws
      IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 0) {
      throw new IllegalArgumentException("Expected 0 arguments, got " + numberOfFields);
    }
    return new com.digitalasset.damlpoc.model.da.generics.U1<p>();
  }

  public Record toValue(Function<p, Value> toValuep) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(0);
    return new Record(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof U1)) {
      return false;
    }
    U1 other = (U1) object;
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    return "com.digitalasset.damlpoc.model.da.generics.U1";
  }
}
