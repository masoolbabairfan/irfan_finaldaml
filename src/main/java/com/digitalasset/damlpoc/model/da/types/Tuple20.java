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

public class Tuple20<a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t> {
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

  public final k _11;

  public final l _12;

  public final m _13;

  public final n _14;

  public final o _15;

  public final p _16;

  public final q _17;

  public final r _18;

  public final s _19;

  public final t _20;

  public Tuple20(a _1, b _2, c _3, d _4, e _5, f _6, g _7, h _8, i _9, j _10, k _11, l _12, m _13,
      n _14, o _15, p _16, q _17, r _18, s _19, t _20) {
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
    this._11 = _11;
    this._12 = _12;
    this._13 = _13;
    this._14 = _14;
    this._15 = _15;
    this._16 = _16;
    this._17 = _17;
    this._18 = _18;
    this._19 = _19;
    this._20 = _20;
  }

  public static <a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t> Tuple20<a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t> fromValue(
      Value value$, Function<Value, a> fromValuea, Function<Value, b> fromValueb,
      Function<Value, c> fromValuec, Function<Value, d> fromValued, Function<Value, e> fromValuee,
      Function<Value, f> fromValuef, Function<Value, g> fromValueg, Function<Value, h> fromValueh,
      Function<Value, i> fromValuei, Function<Value, j> fromValuej, Function<Value, k> fromValuek,
      Function<Value, l> fromValuel, Function<Value, m> fromValuem, Function<Value, n> fromValuen,
      Function<Value, o> fromValueo, Function<Value, p> fromValuep, Function<Value, q> fromValueq,
      Function<Value, r> fromValuer, Function<Value, s> fromValues, Function<Value, t> fromValuet)
      throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 20) {
      throw new IllegalArgumentException("Expected 20 arguments, got " + numberOfFields);
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
    k _11 = fromValuek.apply(fields$.get(10).getValue());
    l _12 = fromValuel.apply(fields$.get(11).getValue());
    m _13 = fromValuem.apply(fields$.get(12).getValue());
    n _14 = fromValuen.apply(fields$.get(13).getValue());
    o _15 = fromValueo.apply(fields$.get(14).getValue());
    p _16 = fromValuep.apply(fields$.get(15).getValue());
    q _17 = fromValueq.apply(fields$.get(16).getValue());
    r _18 = fromValuer.apply(fields$.get(17).getValue());
    s _19 = fromValues.apply(fields$.get(18).getValue());
    t _20 = fromValuet.apply(fields$.get(19).getValue());
    return new com.digitalasset.damlpoc.model.da.types.Tuple20<a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20);
  }

  public Record toValue(Function<a, Value> toValuea, Function<b, Value> toValueb,
      Function<c, Value> toValuec, Function<d, Value> toValued, Function<e, Value> toValuee,
      Function<f, Value> toValuef, Function<g, Value> toValueg, Function<h, Value> toValueh,
      Function<i, Value> toValuei, Function<j, Value> toValuej, Function<k, Value> toValuek,
      Function<l, Value> toValuel, Function<m, Value> toValuem, Function<n, Value> toValuen,
      Function<o, Value> toValueo, Function<p, Value> toValuep, Function<q, Value> toValueq,
      Function<r, Value> toValuer, Function<s, Value> toValues, Function<t, Value> toValuet) {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(20);
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
    fields.add(new Record.Field("_11", toValuek.apply(this._11)));
    fields.add(new Record.Field("_12", toValuel.apply(this._12)));
    fields.add(new Record.Field("_13", toValuem.apply(this._13)));
    fields.add(new Record.Field("_14", toValuen.apply(this._14)));
    fields.add(new Record.Field("_15", toValueo.apply(this._15)));
    fields.add(new Record.Field("_16", toValuep.apply(this._16)));
    fields.add(new Record.Field("_17", toValueq.apply(this._17)));
    fields.add(new Record.Field("_18", toValuer.apply(this._18)));
    fields.add(new Record.Field("_19", toValues.apply(this._19)));
    fields.add(new Record.Field("_20", toValuet.apply(this._20)));
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
    if (!(object instanceof Tuple20)) {
      return false;
    }
    Tuple20 other = (Tuple20) object;
    return this._1.equals(other._1) && this._2.equals(other._2) && this._3.equals(other._3) && this._4.equals(other._4) && this._5.equals(other._5) && this._6.equals(other._6) && this._7.equals(other._7) && this._8.equals(other._8) && this._9.equals(other._9) && this._10.equals(other._10) && this._11.equals(other._11) && this._12.equals(other._12) && this._13.equals(other._13) && this._14.equals(other._14) && this._15.equals(other._15) && this._16.equals(other._16) && this._17.equals(other._17) && this._18.equals(other._18) && this._19.equals(other._19) && this._20.equals(other._20);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10, this._11, this._12, this._13, this._14, this._15, this._16, this._17, this._18, this._19, this._20);
  }

  @Override
  public String toString() {
    return String.format("com.digitalasset.damlpoc.model.da.types.Tuple20(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", this._1, this._2, this._3, this._4, this._5, this._6, this._7, this._8, this._9, this._10, this._11, this._12, this._13, this._14, this._15, this._16, this._17, this._18, this._19, this._20);
  }
}
