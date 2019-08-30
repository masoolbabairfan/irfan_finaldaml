package com.digitalasset.damlpoc.model.da.generics;

import com.daml.ledger.javaapi.data.DamlOptional;
import com.daml.ledger.javaapi.data.Record;
import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MetaSel0 {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public final Optional<Unit> mbRecordName;

  public final SourceUnpackedness sourceUnpackedness;

  public final SourceStrictness sourceStrictness;

  public MetaSel0(Optional<Unit> mbRecordName, SourceUnpackedness sourceUnpackedness,
      SourceStrictness sourceStrictness) {
    this.mbRecordName = mbRecordName;
    this.sourceUnpackedness = sourceUnpackedness;
    this.sourceStrictness = sourceStrictness;
  }

  public static MetaSel0 fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 3) {
      throw new IllegalArgumentException("Expected 3 arguments, got " + numberOfFields);
    }
    Optional<Unit> mbRecordName = fields$.get(0).getValue().asOptional().map(v$0 -> v$0.getValue().map(v$1 -> v$1.asUnit().orElseThrow(() -> new IllegalArgumentException("Expected v$1 to be of type com.daml.ledger.javaapi.data.Unit")))).orElseThrow(() -> new IllegalArgumentException("Expected mbRecordName to be of type com.daml.ledger.javaapi.data.DamlOptional"));
    SourceUnpackedness sourceUnpackedness = SourceUnpackedness.fromValue(fields$.get(1).getValue());
    SourceStrictness sourceStrictness = SourceStrictness.fromValue(fields$.get(2).getValue());
    return new com.digitalasset.damlpoc.model.da.generics.MetaSel0(mbRecordName, sourceUnpackedness, sourceStrictness);
  }

  public Record toValue() {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(3);
    fields.add(new Record.Field("mbRecordName", new DamlOptional(this.mbRecordName.map(v$0 -> Unit.getInstance()))));
    fields.add(new Record.Field("sourceUnpackedness", this.sourceUnpackedness.toValue()));
    fields.add(new Record.Field("sourceStrictness", this.sourceStrictness.toValue()));
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
    if (!(object instanceof MetaSel0)) {
      return false;
    }
    MetaSel0 other = (MetaSel0) object;
    return this.mbRecordName.equals(other.mbRecordName) && this.sourceUnpackedness.equals(other.sourceUnpackedness) && this.sourceStrictness.equals(other.sourceStrictness);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.mbRecordName, this.sourceUnpackedness, this.sourceStrictness);
  }

  @Override
  public String toString() {
    return String.format("com.digitalasset.damlpoc.model.da.generics.MetaSel0(%s, %s, %s)", this.mbRecordName, this.sourceUnpackedness, this.sourceStrictness);
  }
}
