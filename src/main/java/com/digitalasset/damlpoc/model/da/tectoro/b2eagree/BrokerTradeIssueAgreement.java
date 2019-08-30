package com.digitalasset.damlpoc.model.da.tectoro.b2eagree;

import com.daml.ledger.javaapi.data.CreateAndExerciseCommand;
import com.daml.ledger.javaapi.data.CreateCommand;
import com.daml.ledger.javaapi.data.CreatedEvent;
import com.daml.ledger.javaapi.data.Date;
import com.daml.ledger.javaapi.data.Decimal;
import com.daml.ledger.javaapi.data.ExerciseByKeyCommand;
import com.daml.ledger.javaapi.data.ExerciseCommand;
import com.daml.ledger.javaapi.data.Identifier;
import com.daml.ledger.javaapi.data.Int64;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Record;
import com.daml.ledger.javaapi.data.Template;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.digitalasset.damlpoc.model.da.types.Tuple2;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class BrokerTradeIssueAgreement extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("b92699ad2b22b8c128f4ace48762017ea8e8f5c83a0b57d8614b0ef631ef2f11", "DA.Tectoro.B2EAgree", "BrokerTradeIssueAgreement");

  public final String broker;

  public final String exchange;

  public final Long tradingLimit;

  public final String volumeLimit;

  public final BigDecimal exchangeFee;

  public final LocalDate agreement_date;

  public BrokerTradeIssueAgreement(String broker, String exchange, Long tradingLimit,
      String volumeLimit, BigDecimal exchangeFee, LocalDate agreement_date) {
    this.broker = broker;
    this.exchange = exchange;
    this.tradingLimit = tradingLimit;
    this.volumeLimit = volumeLimit;
    this.exchangeFee = exchangeFee;
    this.agreement_date = agreement_date;
  }

  public CreateCommand create() {
    return new CreateCommand(BrokerTradeIssueAgreement.TEMPLATE_ID, this.toValue());
  }

  public static ExerciseByKeyCommand exerciseByKeyArchive(Tuple2<String, String> key, Unit arg) {
    return new ExerciseByKeyCommand(BrokerTradeIssueAgreement.TEMPLATE_ID, key.toValue(v$0 -> new Party(v$0),v$1 -> new Party(v$1)), "Archive", arg);
  }

  public CreateAndExerciseCommand createAndExerciseArchive(Unit arg) {
    return new CreateAndExerciseCommand(BrokerTradeIssueAgreement.TEMPLATE_ID, this.toValue(), "Archive", arg);
  }

  public static CreateCommand create(String broker, String exchange, Long tradingLimit,
      String volumeLimit, BigDecimal exchangeFee, LocalDate agreement_date) {
    return new BrokerTradeIssueAgreement(broker, exchange, tradingLimit, volumeLimit, exchangeFee, agreement_date).create();
  }

  public static BrokerTradeIssueAgreement fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 6) {
      throw new IllegalArgumentException("Expected 6 arguments, got " + numberOfFields);
    }
    String broker = fields$.get(0).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected broker to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    String exchange = fields$.get(1).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected exchange to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    Long tradingLimit = fields$.get(2).getValue().asInt64().orElseThrow(() -> new IllegalArgumentException("Expected tradingLimit to be of type com.daml.ledger.javaapi.data.Int64")).getValue();
    String volumeLimit = fields$.get(3).getValue().asText().orElseThrow(() -> new IllegalArgumentException("Expected volumeLimit to be of type com.daml.ledger.javaapi.data.Text")).getValue();
    BigDecimal exchangeFee = fields$.get(4).getValue().asDecimal().orElseThrow(() -> new IllegalArgumentException("Expected exchangeFee to be of type com.daml.ledger.javaapi.data.Decimal")).getValue();
    LocalDate agreement_date = fields$.get(5).getValue().asDate().orElseThrow(() -> new IllegalArgumentException("Expected agreement_date to be of type com.daml.ledger.javaapi.data.Date")).getValue();
    return new com.digitalasset.damlpoc.model.da.tectoro.b2eagree.BrokerTradeIssueAgreement(broker, exchange, tradingLimit, volumeLimit, exchangeFee, agreement_date);
  }

  public Record toValue() {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(6);
    fields.add(new Record.Field("broker", new Party(this.broker)));
    fields.add(new Record.Field("exchange", new Party(this.exchange)));
    fields.add(new Record.Field("tradingLimit", new Int64(this.tradingLimit)));
    fields.add(new Record.Field("volumeLimit", new Text(this.volumeLimit)));
    fields.add(new Record.Field("exchangeFee", new Decimal(this.exchangeFee)));
    fields.add(new Record.Field("agreement_date", new Date((int) this.agreement_date.toEpochDay())));
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
    if (!(object instanceof BrokerTradeIssueAgreement)) {
      return false;
    }
    BrokerTradeIssueAgreement other = (BrokerTradeIssueAgreement) object;
    return this.broker.equals(other.broker) && this.exchange.equals(other.exchange) && this.tradingLimit.equals(other.tradingLimit) && this.volumeLimit.equals(other.volumeLimit) && this.exchangeFee.equals(other.exchangeFee) && this.agreement_date.equals(other.agreement_date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.broker, this.exchange, this.tradingLimit, this.volumeLimit, this.exchangeFee, this.agreement_date);
  }

  @Override
  public String toString() {
    return String.format("com.digitalasset.damlpoc.model.da.tectoro.b2eagree.BrokerTradeIssueAgreement(%s, %s, %s, %s, %s, %s)", this.broker, this.exchange, this.tradingLimit, this.volumeLimit, this.exchangeFee, this.agreement_date);
  }

  public static final class ContractId {
    public final String contractId;

    public ContractId(String contractId) {
      this.contractId = contractId;
    }

    public ExerciseCommand exerciseArchive(Unit arg) {
      return new ExerciseCommand(BrokerTradeIssueAgreement.TEMPLATE_ID, this.contractId, "Archive", arg);
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
      return String.format("com.digitalasset.damlpoc.model.da.tectoro.b2eagree.BrokerTradeIssueAgreement.ContractId(%s)", this.contractId);
    }
  }

  public static class Contract implements com.daml.ledger.javaapi.data.Contract {
    public final ContractId id;

    public final BrokerTradeIssueAgreement data;

    public final Optional<String> agreementText;

    public final Optional<Tuple2<String, String>> key;

    public Contract(ContractId id, BrokerTradeIssueAgreement data, Optional<String> agreementText,
        Optional<Tuple2<String, String>> key) {
      this.id = id;
      this.data = data;
      this.agreementText = agreementText;
      this.key = key;
    }

    public static Contract fromIdAndRecord(String contractId, Record record$,
        Optional<String> agreementText, Optional<Tuple2<String, String>> key) {
      ContractId id = new ContractId(contractId);
      BrokerTradeIssueAgreement data = BrokerTradeIssueAgreement.fromValue(record$);
      return new Contract(id, data, agreementText, key);
    }

    @Deprecated
    public static Contract fromIdAndRecord(String contractId, Record record$) {
      ContractId id = new ContractId(contractId);
      BrokerTradeIssueAgreement data = BrokerTradeIssueAgreement.fromValue(record$);
      return new Contract(id, data, Optional.empty(), Optional.empty());
    }

    public static Contract fromCreatedEvent(CreatedEvent event) {
      return fromIdAndRecord(event.getContractId(), event.getArguments(), event.getAgreementText(), event.getContractKey().map(e -> Tuple2.<java.lang.String, java.lang.String>fromValue(e, v$0 -> v$0.asParty().orElseThrow(() -> new IllegalArgumentException("Expected e to be of type com.daml.ledger.javaapi.data.Party")).getValue(), v$1 -> v$1.asParty().orElseThrow(() -> new IllegalArgumentException("Expected e to be of type com.daml.ledger.javaapi.data.Party")).getValue())));
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
      return String.format("com.digitalasset.damlpoc.model.da.tectoro.b2eagree.BrokerTradeIssueAgreement.Contract(%s, %s, %s)", this.id, this.data, this.agreementText);
    }
  }
}
