package com.digitalasset.damlpoc.model.da.generics;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class DecidedStrictness {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public DecidedStrictness() {
  }

  public abstract Value toValue();

  public static DecidedStrictness fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.generics.DecidedStrictness"));
    if ("DecidedLazy".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.decidedstrictness.DecidedLazy.fromValue(variant$);
    }
    if ("DecidedStrict".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.decidedstrictness.DecidedStrict.fromValue(variant$);
    }
    if ("DecidedUnpack".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.decidedstrictness.DecidedUnpack.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.generics.DecidedStrictness, expected one of [DecidedLazy, DecidedStrict, DecidedUnpack]");
  }
}
