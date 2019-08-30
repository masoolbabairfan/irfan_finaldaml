package com.digitalasset.damlpoc.model.da.date.month;

import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import com.digitalasset.damlpoc.model.da.date.Month;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public class Apr extends Month {
  public static final String _packageId = "9c7782872e80413fe718c7b50f28c5860f66a04c68644d6ffcd3605de879c25c";

  public final Unit unitValue;

  public Apr(Unit unitValue) {
    this.unitValue = unitValue;
  }

  public Variant toValue() {
    return new Variant("Apr", Unit.getInstance());
  }

  public static Apr fromValue(Value value$) throws IllegalArgumentException {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected: Variant. Actual: " + value$.getClass().getName()));
    if (!"Apr".equals(variant$.getConstructor())) throw new IllegalArgumentException("Invalid constructor. Expected: Apr. Actual: " + variant$.getConstructor());
    Value variantValue$ = variant$.getValue();
    Unit body = variantValue$.asUnit().orElseThrow(() -> new IllegalArgumentException("Expected body to be of type com.daml.ledger.javaapi.data.Unit"));
    return new Apr(body);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Apr)) {
      return false;
    }
    Apr other = (Apr) object;
    return this.unitValue.equals(other.unitValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unitValue);
  }

  @Override
  public String toString() {
    return String.format("Apr(%s)", this.unitValue);
  }
}