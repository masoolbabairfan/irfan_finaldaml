package com.digitalasset.damlpoc.model.da.internal.prelude;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;
import java.util.function.Function;

public abstract class Optional<a> {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public Optional() {
  }

  public abstract Value toValue(Function<a, Value> toValuea);

  public static <a> Optional<a> fromValue(Value value$, Function<Value, a> fromValuea) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.internal.prelude.Optional"));
    if ("None".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.internal.prelude.optional.None.fromValue(variant$, fromValuea);
    }
    if ("Some".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.internal.prelude.optional.Some.fromValue(variant$, fromValuea);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.internal.prelude.Optional, expected one of [None, Some]");
  }
}
