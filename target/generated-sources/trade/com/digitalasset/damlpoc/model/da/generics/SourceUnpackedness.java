package com.digitalasset.damlpoc.model.da.generics;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class SourceUnpackedness {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public SourceUnpackedness() {
  }

  public abstract Value toValue();

  public static SourceUnpackedness fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.generics.SourceUnpackedness"));
    if ("NoSourceUnpackedness".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.sourceunpackedness.NoSourceUnpackedness.fromValue(variant$);
    }
    if ("SourceNoUnpack".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.sourceunpackedness.SourceNoUnpack.fromValue(variant$);
    }
    if ("SourceUnpack".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.generics.sourceunpackedness.SourceUnpack.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.generics.SourceUnpackedness, expected one of [NoSourceUnpackedness, SourceNoUnpack, SourceUnpack]");
  }
}
