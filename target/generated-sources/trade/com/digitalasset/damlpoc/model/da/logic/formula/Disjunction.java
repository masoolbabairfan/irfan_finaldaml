package com.digitalasset.damlpoc.model.da.logic.formula;

import com.daml.ledger.javaapi.data.DamlList;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.digitalasset.damlpoc.model.da.logic.Formula;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Disjunction<t> extends Formula<t> {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public final List<Formula<t>> listValue;

  public Disjunction(List<Formula<t>> listValue) {
    this.listValue = listValue;
  }

  public Variant toValue(Function<t, Value> toValuet) {
    return new Variant("Disjunction", new DamlList(this.listValue.stream().map(v$0 -> v$0.toValue(v$1 -> toValuet.apply(v$1))).collect(Collectors.<Value>toList())));
  }

  public static <t> Disjunction<t> fromValue(Value value$, Function<Value, t> fromValuet) throws
      IllegalArgumentException {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected: Variant. Actual: " + value$.getClass().getName()));
    if (!"Disjunction".equals(variant$.getConstructor())) throw new IllegalArgumentException("Invalid constructor. Expected: Disjunction. Actual: " + variant$.getConstructor());
    Value variantValue$ = variant$.getValue();
    List<Formula<t>> body = variantValue$.asList().map(v$0 -> v$0.getValues().stream().map(v$1 -> Formula.<t>fromValue(v$1, v$2 -> fromValuet.apply(v$2))).collect(Collectors.<com.digitalasset.damlpoc.model.da.logic.Formula<t>>toList())).orElseThrow(() -> new IllegalArgumentException("Expected body to be of type com.daml.ledger.javaapi.data.DamlList"));
    return new Disjunction<t>(body);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Disjunction)) {
      return false;
    }
    Disjunction other = (Disjunction) object;
    return this.listValue.equals(other.listValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.listValue);
  }

  @Override
  public String toString() {
    return String.format("Disjunction(%s)", this.listValue);
  }
}
