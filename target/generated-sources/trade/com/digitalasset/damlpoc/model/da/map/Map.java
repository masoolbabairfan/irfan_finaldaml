package com.digitalasset.damlpoc.model.da.map;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;
import java.util.function.Function;

public abstract class Map<k, v> {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public Map() {
  }

  public abstract Value toValue(Function<k, Value> toValuek, Function<v, Value> toValuev);

  public static <k, v> Map<k, v> fromValue(Value value$, Function<Value, k> fromValuek,
      Function<Value, v> fromValuev) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.map.Map"));
    if ("Map_internal".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.map.map.Map_internal.fromValue(variant$, fromValuek, fromValuev);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.map.Map, expected one of [Map_internal]");
  }
}
