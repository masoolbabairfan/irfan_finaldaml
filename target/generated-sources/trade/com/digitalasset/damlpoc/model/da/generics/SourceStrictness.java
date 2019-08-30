package com.digitalasset.damlpoc.model.da.generics;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class SourceStrictness {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public SourceStrictness() {
  }

  public abstract Value toValue();

  public static SourceStrictness fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.generics.SourceStrictness"));
    if ("NoSourceStrictness".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.sourcestrictness.NoSourceStrictness.fromValue(variant$);
    }
    if ("SourceLazy".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.sourcestrictness.SourceLazy.fromValue(variant$);
    }
    if ("SourceStrict".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.sourcestrictness.SourceStrict.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.generics.SourceStrictness, expected one of [NoSourceStrictness, SourceLazy, SourceStrict]");
  }
}
