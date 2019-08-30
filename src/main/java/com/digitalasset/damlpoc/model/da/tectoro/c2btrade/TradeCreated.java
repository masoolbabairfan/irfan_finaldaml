package com.digitalasset.damlpoc.model.da.tectoro.c2btrade;

import com.daml.ledger.javaapi.data.CreateAndExerciseCommand;
import com.daml.ledger.javaapi.data.CreateCommand;
import com.daml.ledger.javaapi.data.CreatedEvent;
import com.daml.ledger.javaapi.data.Decimal;
import com.daml.ledger.javaapi.data.ExerciseCommand;
import com.daml.ledger.javaapi.data.Identifier;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Record;
import com.daml.ledger.javaapi.data.Template;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.digitalasset.damlpoc.model.da.tectoro.facts.OrderTerms;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class TradeCreated extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("b92699ad2b22b8c128f4ace48762017ea8e8f5c83a0b57d8614b0ef631ef2f11", "DA.Tectoro.C2BTrade", "TradeCreated");

  public final String broker;

  public final String client;

  public final OrderTerms terms;

  public final String executionId;

  public final BigDecimal principle;

  public final BigDecimal commissionPrice;

  public final BigDecimal exchangePrice;

  public final BigDecimal settlementAmount;

  public TradeCreated(String broker, String client, OrderTerms terms, String executionId,
      BigDecimal principle, BigDecimal commissionPrice, BigDecimal exchangePrice,
      BigDecimal settlementAmount) {
    this.broker = broker;
    this.client = client;
    this.terms = terms;
    this.executionId = executionId;
    this.principle = principle;
    this.commissionPrice = commissionPrice;
    this.exchangePrice = exchangePrice;
    this.settlementAmount = settlementAmount;
  }

  public CreateCommand create() {
    return new CreateCommand(TradeCreated.TEMPLATE_ID, this.toValue());
  }

  public CreateAndExerciseCommand createAndExerciseArchive(Unit arg) {
    return new CreateAndExerciseCommand(TradeCreated.TEMPLATE_ID, this.toValue(), "Archive", arg);
  }

  public static CreateCommand create(String broker, String client, OrderTerms terms,
      String executionId, BigDecimal principle, BigDecimal commissionPrice,
      BigDecimal exchangePrice, BigDecimal settlementAmount) {
    return new TradeCreated(broker, client, terms, executionId, principle, commissionPrice, exchangePrice, settlementAmount).create();
  }

  public static TradeCreated fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 8) {
      throw new IllegalArgumentException("Expected 8 arguments, got " + numberOfFields);
    }
    String broker = fields$.get(0).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected broker to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    String client = fields$.get(1).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected client to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    OrderTerms terms = OrderTerms.fromValue(fields$.get(2).getValue());
    String executionId = fields$.get(3).getValue().asText().orElseThrow(() -> new IllegalArgumentException("Expected executionId to be of type com.daml.ledger.javaapi.data.Text")).getValue();
    BigDecimal principle = fields$.get(4).getValue().asDecimal().orElseThrow(() -> new IllegalArgumentException("Expected principle to be of type com.daml.ledger.javaapi.data.Decimal")).getValue();
    BigDecimal commissionPrice = fields$.get(5).getValue().asDecimal().orElseThrow(() -> new IllegalArgumentException("Expected commissionPrice to be of type com.daml.ledger.javaapi.data.Decimal")).getValue();
    BigDecimal exchangePrice = fields$.get(6).getValue().asDecimal().orElseThrow(() -> new IllegalArgumentException("Expected exchangePrice to be of type com.daml.ledger.javaapi.data.Decimal")).getValue();
    BigDecimal settlementAmount = fields$.get(7).getValue().asDecimal().orElseThrow(() -> new IllegalArgumentException("Expected settlementAmount to be of type com.daml.ledger.javaapi.data.Decimal")).getValue();
    return new com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeCreated(broker, client, terms, executionId, principle, commissionPrice, exchangePrice, settlementAmount);
  }

  public Record toValue() {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(8);
    fields.add(new Record.Field("broker", new Party(this.broker)));
    fields.add(new Record.Field("client", new Party(this.client)));
    fields.add(new Record.Field("terms", this.terms.toValue()));
    fields.add(new Record.Field("executionId", new Text(this.executionId)));
    fields.add(new Record.Field("principle", new Decimal(this.principle)));
    fields.add(new Record.Field("commissionPrice", new Decimal(this.commissionPrice)));
    fields.add(new Record.Field("exchangePrice", new Decimal(this.exchangePrice)));
    fields.add(new Record.Field("settlementAmount", new Decimal(this.settlementAmount)));
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
    if (!(object instanceof TradeCreated)) {
      return false;
    }
    TradeCreated other = (TradeCreated) object;
    return this.broker.equals(other.broker) && this.client.equals(other.client) && this.terms.equals(other.terms) && this.executionId.equals(other.executionId) && this.principle.equals(other.principle) && this.commissionPrice.equals(other.commissionPrice) && this.exchangePrice.equals(other.exchangePrice) && this.settlementAmount.equals(other.settlementAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.broker, this.client, this.terms, this.executionId, this.principle, this.commissionPrice, this.exchangePrice, this.settlementAmount);
  }

  @Override
  public String toString() {
    return String.format("com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeCreated(%s, %s, %s, %s, %s, %s, %s, %s)", this.broker, this.client, this.terms, this.executionId, this.principle, this.commissionPrice, this.exchangePrice, this.settlementAmount);
  }

  public static final class ContractId {
    public final String contractId;

    public ContractId(String contractId) {
      this.contractId = contractId;
    }

    public ExerciseCommand exerciseArchive(Unit arg) {
      return new ExerciseCommand(TradeCreated.TEMPLATE_ID, this.contractId, "Archive", arg);
    }

    public Value toValue() {
      return new com.daml.ledger.javaapi.data.ContractId(this.contractId);
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object == null) {
        return false;
      }
      if (!(object instanceof ContractId)) {
        return false;
      }
      ContractId other = (ContractId) object;
      return this.contractId.equals(other.contractId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.contractId);
    }

    @Override
    public String toString() {
      return String.format("com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeCreated.ContractId(%s)", this.contractId);
    }
  }

  public static class Contract implements com.daml.ledger.javaapi.data.Contract {
    public final ContractId id;

    public final TradeCreated data;

    public final Optional<String> agreementText;

    public Contract(ContractId id, TradeCreated data, Optional<String> agreementText) {
      this.id = id;
      this.data = data;
      this.agreementText = agreementText;
    }

    public static Contract fromIdAndRecord(String contractId, Record record$,
        Optional<String> agreementText) {
      ContractId id = new ContractId(contractId);
      TradeCreated data = TradeCreated.fromValue(record$);
      return new Contract(id, data, agreementText);
    }

    @Deprecated
    public static Contract fromIdAndRecord(String contractId, Record record$) {
      ContractId id = new ContractId(contractId);
      TradeCreated data = TradeCreated.fromValue(record$);
      return new Contract(id, data, Optional.empty());
    }

    public static Contract fromCreatedEvent(CreatedEvent event) {
      return fromIdAndRecord(event.getContractId(), event.getArguments(), event.getAgreementText());
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object == null) {
        return false;
      }
      if (!(object instanceof Contract)) {
        return false;
      }
      Contract other = (Contract) object;
      return this.id.equals(other.id) && this.data.equals(other.data) && this.agreementText.equals(other.agreementText);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.id, this.data, this.agreementText);
    }

    @Override
    public String toString() {
      return String.format("com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeCreated.Contract(%s, %s, %s)", this.id, this.data, this.agreementText);
    }
  }
}
