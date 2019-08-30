package com.digitalasset.damlpoc.model.da.generics;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class Fixity {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public Fixity() {
  }

  public abstract Value toValue();

  public static Fixity fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.generics.Fixity"));
    if ("Prefix".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.fixity.Prefix.fromValue(variant$);
    }
    if ("Infix".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.fixity.Infix.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.generics.Fixity, expected one of [Prefix, Infix]");
  }
}
