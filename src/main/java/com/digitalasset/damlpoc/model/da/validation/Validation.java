package com.digitalasset.damlpoc.model.da.validation;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;
import java.util.function.Function;

public abstract class Validation<err, a> {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public Validation() {
  }

  public abstract Value toValue(Function<err, Value> toValueerr, Function<a, Value> toValuea);

  public static <err, a> Validation<err, a> fromValue(Value value$,
      Function<Value, err> fromValueerr, Function<Value, a> fromValuea) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.validation.Validation"));
    if ("Errors".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.validation.validation.Errors.fromValue(variant$, fromValueerr, fromValuea);
    }
    if ("Success".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.validation.validation.Success.fromValue(variant$, fromValueerr, fromValuea);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.validation.Validation, expected one of [Errors, Success]");
  }
}
