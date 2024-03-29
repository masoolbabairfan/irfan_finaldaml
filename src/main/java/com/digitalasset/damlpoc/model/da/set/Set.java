package com.digitalasset.damlpoc.model.da.set;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;
import java.util.function.Function;

public abstract class Set<a> {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public Set() {
  }

  public abstract Value toValue(Function<a, Value> toValuea);

  public static <a> Set<a> fromValue(Value value$, Function<Value, a> fromValuea) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.set.Set"));
    if ("Set_internal".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.set.set.Set_internal.fromValue(variant$, fromValuea);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.set.Set, expected one of [Set_internal]");
  }
}
