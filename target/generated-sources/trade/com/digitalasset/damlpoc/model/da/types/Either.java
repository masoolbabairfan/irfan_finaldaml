package com.digitalasset.damlpoc.model.da.types;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;
import java.util.function.Function;

public abstract class Either<a, b> {
  public static final String _packageId = "e401260132882f6bc61e918905451820ea49b293e9cd05d93fea82c1a8d1be37";

  public Either() {
  }

  public abstract Value toValue(Function<a, Value> toValuea, Function<b, Value> toValueb);

  public static <a, b> Either<a, b> fromValue(Value value$, Function<Value, a> fromValuea,
      Function<Value, b> fromValueb) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.digitalasset.damlpoc.model.da.types.Either"));
    if ("Left".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.types.either.Left.fromValue(variant$, fromValuea, fromValueb);
    }
    if ("Right".equals(variant$.getConstructor())) {
      return com.digitalasset.damlpoc.model.da.types.either.Right.fromValue(variant$, fromValuea, fromValueb);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.digitalasset.damlpoc.model.da.types.Either, expected one of [Left, Right]");
  }
}
