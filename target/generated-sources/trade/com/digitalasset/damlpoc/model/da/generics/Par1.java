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

public class Par1<p> {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public final p unPar1;

  public Par1(p unPar1) {
    this.unPar1 = unPar1;
  }

  public static <p> Par1<p> fromValue(Value value$, Function<Value, p> fromValuep) throws
      IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 1) {
      throw new IllegalArgumentException("Expected 1 arguments, got " + numberOfFields);
    }
    p unPar1 = fromValuep.apply(fields$.get(0).getValue());
    return new com.digitalasset.damlpoc.model.da.generics.Par1<p>(unPar1);
  }

  public Record toValue(Function<p, Value> toValuep) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(1);
    fields.add(new Record.Field("unPar1", toValuep.apply(this.unPar1)));
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
    if (!(object instanceof Par1)) {
      return false;
    }
    Par1 other = (Par1) object;
    return this.unPar1.equals(other.unPar1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unPar1);
  }

  @Override
  public String toString() {
    return String.format("com.digitalasset.damlpoc.model.da.generics.Par1(%s)", this.unPar1);
  }
}
