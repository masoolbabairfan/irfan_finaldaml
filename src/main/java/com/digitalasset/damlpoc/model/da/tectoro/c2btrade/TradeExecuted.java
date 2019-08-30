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

public final class TradeExecuted extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("b92699ad2b22b8c128f4ace48762017ea8e8f5c83a0b57d8614b0ef631ef2f11", "DA.Tectoro.C2BTrade", "TradeExecuted");

  public final String broker;

  public final String regulator;

  public final String client;

  public final String executionId;

  public final OrderTerms terms;

  public final BigDecimal commissionRate;

  public TradeExecuted(String broker, String regulator, String client, String executionId,
      OrderTerms terms, BigDecimal commissionRate) {
    this.broker = broker;
    this.regulator = regulator;
    this.client = client;
    this.executionId = executionId;
    this.terms = terms;
    this.commissionRate = commissionRate;
  }

  public CreateCommand create() {
    return new CreateCommand(TradeExecuted.TEMPLATE_ID, this.toValue());
  }

  public CreateAndExerciseCommand createAndExerciseArchive(Unit arg) {
    return new CreateAndExerciseCommand(TradeExecuted.TEMPLATE_ID, this.toValue(), "Archive", arg);
  }

  public CreateAndExerciseCommand createAndExerciseGenerate_Trade(Generate_Trade arg) {
    return new CreateAndExerciseCommand(TradeExecuted.TEMPLATE_ID, this.toValue(), "Generate_Trade", arg.toValue());
  }

  public CreateAndExerciseCommand createAndExerciseGenerate_Trade() {
    return createAndExerciseGenerate_Trade(new Generate_Trade());
  }

  public static CreateCommand create(String broker, String regulator, String client,
      String executionId, OrderTerms terms, BigDecimal commissionRate) {
    return new TradeExecuted(broker, regulator, client, executionId, terms, commissionRate).create();
  }

  public static TradeExecuted fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    List<Record.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 6) {
      throw new IllegalArgumentException("Expected 6 arguments, got " + numberOfFields);
    }
    String broker = fields$.get(0).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected broker to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    String regulator = fields$.get(1).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected regulator to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    String client = fields$.get(2).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected client to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    String executionId = fields$.get(3).getValue().asText().orElseThrow(() -> new IllegalArgumentException("Expected executionId to be of type com.daml.ledger.javaapi.data.Text")).getValue();
    OrderTerms terms = OrderTerms.fromValue(fields$.get(4).getValue());
    BigDecimal commissionRate = fields$.get(5).getValue().asDecimal().orElseThrow(() -> new IllegalArgumentException("Expected commissionRate to be of type com.daml.ledger.javaapi.data.Decimal")).getValue();
    return new com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeExecuted(broker, regulator, client, executionId, terms, commissionRate);
  }

  public Record toValue() {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(6);
    fields.add(new Record.Field("broker", new Party(this.broker)));
    fields.add(new Record.Field("regulator", new Party(this.regulator)));
    fields.add(new Record.Field("client", new Party(this.client)));
    fields.add(new Record.Field("executionId", new Text(this.executionId)));
    fields.add(new Record.Field("terms", this.terms.toValue()));
    fields.add(new Record.Field("commissionRate", new Decimal(this.commissionRate)));
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
    if (!(object instanceof TradeExecuted)) {
      return false;
    }
    TradeExecuted other = (TradeExecuted) object;
    return this.broker.equals(other.broker) && this.regulator.equals(other.regulator) && this.client.equals(other.client) && this.executionId.equals(other.executionId) && this.terms.equals(other.terms) && this.commissionRate.equals(other.commissionRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.broker, this.regulator, this.client, this.executionId, this.terms, this.commissionRate);
  }

  @Override
  public String toString() {
    return String.format("com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeExecuted(%s, %s, %s, %s, %s, %s)", this.broker, this.regulator, this.client, this.executionId, this.terms, this.commissionRate);
  }

  public static final class ContractId {
    public final String contractId;

    public ContractId(String contractId) {
      this.contractId = contractId;
    }

    public ExerciseCommand exerciseArchive(Unit arg) {
      return new ExerciseCommand(TradeExecuted.TEMPLATE_ID, this.contractId, "Archive", arg);
    }

    public ExerciseCommand exerciseGenerate_Trade(Generate_Trade arg) {
      return new ExerciseCommand(TradeExecuted.TEMPLATE_ID, this.contractId, "Generate_Trade", arg.toValue());
    }

    public ExerciseCommand exerciseGenerate_Trade() {
      return exerciseGenerate_Trade(new Generate_Trade());
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
      return String.format("com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeExecuted.ContractId(%s)", this.contractId);
    }
  }

  public static class Contract implements com.daml.ledger.javaapi.data.Contract {
    public final ContractId id;

    public final TradeExecuted data;

    public final Optional<String> agreementText;

    public Contract(ContractId id, TradeExecuted data, Optional<String> agreementText) {
      this.id = id;
      this.data = data;
      this.agreementText = agreementText;
    }

    public static Contract fromIdAndRecord(String contractId, Record record$,
        Optional<String> agreementText) {
      ContractId id = new ContractId(contractId);
      TradeExecuted data = TradeExecuted.fromValue(record$);
      return new Contract(id, data, agreementText);
    }

    @Deprecated
    public static Contract fromIdAndRecord(String contractId, Record record$) {
      ContractId id = new ContractId(contractId);
      TradeExecuted data = TradeExecuted.fromValue(record$);
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
      return String.format("com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeExecuted.Contract(%s, %s, %s)", this.id, this.data, this.agreementText);
    }
  }
}
