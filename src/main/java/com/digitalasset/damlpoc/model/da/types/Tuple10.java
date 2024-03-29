package com.digitalasset.damlpoc.model.da.types;

import com.daml.ledger.javaapi.data.Record;
import com.daml.ledger.javaapi.data.Value;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Tuple10<a, b, c, d, e, f, g, h, i, j> {
  public static final String _packageId = "e401260132882f6bc61e918905451820ea49b293e9cd05d93fea82c1a8d1be37";

  public final a _1;

  public final b _2;

  public final c _3;

  public final d _4;

  public final e _5;

  public final f _6;

  public final g _7;

  public final h _8;

  public final i _9;

  public final j _10;

  public Tuple10(a _1, b _2, c _3, d _4, e _5, f _6, g _7, h _8, i _9, j _10) {
    this._1 = _1;
    this._2 = _2;
    this._3 = _3;
    this._4 = _4;
    this._5 = _5;
    this._6 = _6;
    this._7 = _7;
    this._8 = _8;
    this._9 = _9;
    this._10 = _10;
  }

  public static <a, b, c, d, e, f, g, h, i, j> Tuple10<a, b, c, d, e, f, g, h, i, j> fromValue(
      Value value$, Function<Value, a> fromValuea, Function<Value, b> fromValueb,
      Function<Value, c> fromValuec, Function<Value, d> fromValued, Function<Value, e> fromValuee,
      Function<Value, f> fromValuef, Function<Value, g> fromValueg, Function<Value, h> fromValueh,
      Function<Value, i> fromValuei, Function<Value, j> fromValuej) throws
      IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 10) {
      throw new IllegalArgumentException("Expected 10 arguments, got " + numberOfFields);
    }
    a _1 = fromValuea.apply(fields$.get(0).getValue());
    b _2 = fromValueb.apply(fields$.get(1).getValue());
    c _3 = fromValuec.apply(fields$.get(2).getValue());
    d _4 = fromValued.apply(fields$.get(3).getValue());
    e _5 = fromValuee.apply(fields$.get(4).getValue());
    f _6 = fromValuef.apply(fields$.get(5).getValue());
    g _7 = fromValueg.apply(fields$.get(6).getValue());
    h _8 = fromValueh.apply(fields$.get(7).getValue());
    i _9 = fromValuei.apply(fields$.get(8).getValue());
    j _10 = fromValuej.apply(fields$.get(9).getValue());
    return new com.digitalasset.damlpoc.model.da.types.Tuple10<a, b, c, d, e, f, g, h, i, j>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
  }

  public Record toValue(Function<a, Value> toValuea, Function<b, Value> toValueb,
      Function<c, Value> toValuec, Function<d, Value> toValued, Function<e, Value> toValuee,
      Function<f, Value> toValuef, Function<g, Value> toValueg, Function<h, Value> toValueh,
      Function<i, Value> toValuei, Function<j, Value> toValuej) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(10);
    fields.add(new Record.Field("_1", toValuea.apply(this._1)));
    fields.add(new Record.Field("_2", toValueb.apply(this._2)));
    fields.add(new Record.Field("_3", toValuec.apply(this._3)));
    fields.add(new Record.Field("_4", toValued.apply(this._4)));
    fields.add(new Record.Field("_5", toValuee.apply(this._5)));
    fields.add(new Record.Field("_6", toValuef.apply(this._6)));
    fields.add(new Record.Field("_7", toValueg.apply(this._7)));
    fields.add(new Record.Field("_8", toValueh.apply(this._8)));
    fields.add(new Record.Field("_9", toValuei.apply(this._9)));
    fields.add(new Record.Field("_10", toValuej.apply(this._10)));
    return new Record(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Tuple10)) {
      return false;
    }
    Tuple10 other = (Tuple10) object;
    return this._1.equals(other._1) && this._2.equals(other._2) && this._3.equals(other._3) && this._4.equals(other._4) && this._5.equals(other._5) && this._6.equals(other._6) && this._7.equals(other._7) && this._8.equals(other._8) && this._9.equals(other._9) && this._10.equals(other._10);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10);
  }

  @Override
  public String toString() {
    return String.format("com.digitalasset.damlpoc.model.da.types.Tuple10(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10);
  }
}
