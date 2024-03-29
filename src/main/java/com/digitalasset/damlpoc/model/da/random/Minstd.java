package com.digitalasset.damlpoc.model.da.random;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class Minstd {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public Minstd() {
  }

  public abstract Value toValue();

  public static Minstd fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.random.Minstd"));
    if ("Minstd".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.random.minstd.Minstd.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.random.Minstd, expected one of [Minstd]");
  }
}
