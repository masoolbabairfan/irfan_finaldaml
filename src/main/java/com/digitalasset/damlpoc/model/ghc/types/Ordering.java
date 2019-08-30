package com.digitalasset.damlpoc.model.ghc.types;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class Ordering {
  public static final String _packageId = "e401260132882f6bc61e918905451820ea49b293e9cd05d93fea82c1a8d1be37";

  public Ordering() {
  }

  public abstract Value toValue();

  public static Ordering fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.ghc.types.Ordering"));
    if ("LT".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.ghc.types.ordering.LT.fromValue(variant$);
    }
    if ("EQ".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.ghc.types.ordering.EQ.fromValue(variant$);
    }
    if ("GT".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.ghc.types.ordering.GT.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.ghc.types.Ordering, expected one of [LT, EQ, GT]");
  }
}
