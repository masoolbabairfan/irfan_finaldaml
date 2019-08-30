package com.digitalasset.damlpoc.model.da.generics;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class Associativity {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public Associativity() {
  }

  public abstract Value toValue();

  public static Associativity fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.generics.Associativity"));
    if ("LeftAssociative".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.associativity.LeftAssociative.fromValue(variant$);
    }
    if ("RightAssociative".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.associativity.RightAssociative.fromValue(variant$);
    }
    if ("NotAssociative".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.associativity.NotAssociative.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.generics.Associativity, expected one of [LeftAssociative, RightAssociative, NotAssociative]");
  }
}
