package com.digitalasset.damlpoc.trade;

import com.daml.ledger.rxjava.DamlLedgerClient;
import com.daml.ledger.rxjava.LedgerClient;
import com.daml.ledger.rxjava.PackageClient;
import com.daml.ledger.javaapi.data.*;
import com.digitalasset.damlpoc.model.da.tectoro.b2eagree.BrokerTradeIssueAgreement;
import com.digitalasset.damlpoc.model.da.tectoro.b2eagree.BrokerTradeIssueProposal;
import com.digitalasset.damlpoc.model.da.tectoro.b2eagree.BrokerTradeMaster;
import com.digitalasset.damlpoc.model.da.tectoro.b2eagree.ExchangeAccept;
import com.digitalasset.damlpoc.model.da.tectoro.c2btrade.Trade;
import com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeCreated;
import com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeExecuted;
import com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeIssueAgreement;
import com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeIssueProposal;
import com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeMaster;
import com.digitalasset.damlpoc.model.da.tectoro.c2btrade.TradeMaster.ContractId;
import com.digitalasset.damlpoc.model.da.tectoro.facts.OrderTerms;
import com.google.common.collect.BiMap;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.protobuf.Empty;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sun.tools.jdi.LinkedHashMap;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;



public class ApiMain {

    private final static Logger logger = LoggerFactory.getLogger(ApiMain.class);

    // application id used for sending commands
    public static final String APP_ID = "IouApp";
	/*
	 * public static DamlLedgerClient client = null; public static TransactionFilter
	 * iouFilter = null;
	 */
    
    public static void main(String[] args) {    

                
        Gson g = new Gson();
		
            // create a client object to access services on the ledger
            DamlLedgerClient client = DamlLedgerClient.forHostWithLedgerIdDiscovery("localhost", 6865, Optional.empty());

            // Connects to the ledger and runs initial validationiou
            client.connect();

            String ledgerId = client.getLedgerId();

            logger.info("ledger-id: {}", ledgerId);

            //TransactionFilter iouFilter = filterFor(TradeIssueAgreement.TEMPLATE_ID, party);
            
           

        Spark.port(8080);
        
        Spark.options("*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        
        before("*", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Content-Type", "application/json");
		});
        
        
		
        //Broker to Client
        Spark.get("/getCreateClientAgreement/:name", "application/json", (req, res) -> g.toJson(getCreateClientAgreement(client,req.params("name"))));
        
        Spark.get("/getClientInviteAgreement/:name",  "application/json", (req, res) -> g.toJson(getClientInviteAgreement(client,req.params("name"))));
        
        Spark.get("/getClientAgreements/:name", "application/json", (req, res) -> g.toJson(getClientAgreement(client,req.params("name"))));
        
        //Broker to Exchange
        Spark.get("/getCreateExchangeAgreement/:name", "application/json", (req, res) -> g.toJson(getCreateExchangeAgreement(client,req.params("name"))));
        
        Spark.get("/getExchangeAcceptedAgreement/:name",  "application/json", (req, res) -> g.toJson(getExchangeAcceptedAgreement(client,req.params("name"))));
        
        Spark.get("/getExchangeAgreement/:name", "application/json", (req, res) ->  g.toJson(getExchangeAgreement(client,req.params("name"))));
        
        //Orders
       // Spark.get("/getPendingOrders/:name", "application/json", (req, res) -> g.toJson(getPendingOrders(client,req.params("name"))));
        
        Spark.get("/getOrders/:name", "application/json", (req, res) -> g.toJson(getOrders(client,req.params("name"))));   
        
        
        //EXECUTIONS
        
        Spark.get("/getTradeExecuted/:name", "application/json", (req, res) -> g.toJson(getTradeExecuted(client,req.params("name"))));
        
        //TRADES
                
        Spark.get("/getTradeCreated/:name", "application/json", (req, res) -> g.toJson(getTradeCreated(client,req.params("name"))));
        
        //Spark.get("/getClientAgreements/:name", "application/json", (req, res) -> g.toJson(getClientAgreement(client,igetOrdersForExchangeouFilter,req.params("name"))) );
		/*
		 * Spark.get("/iou/:id", "application/json", (req, res) ->
		 * g.toJson(contracts.getOrDefault(Long.parseLong(req.params("id")), null)) );
		 */
        
        
        // Run until user terminates
        
        //Create Broker to client agreement starts here
        Spark.put("/brokeCreateClientAgreement/:name", (req, res) -> {
        	System.out.println(req.body());
        	TradeMaster iou = g.fromJson(req.body(), TradeMaster.class);         	
            CreateCommand iouCreate = iou.create();
            submit(client, req.params("name"), iouCreate);
            return "Iou creation submitted.";
        }, g::toJson);
        
        
        Spark.post("/brokerInviteClientAgreement/:name", (req, res) -> {   	
        	System.out.println(req.body());
        	TransactionFilter iouFilter = filterFor(TradeMaster.TEMPLATE_ID, req.params("name"));       	
            
            BiMap<String, TradeMaster.ContractId> idMap = Maps.synchronizedBiMap(HashBiMap.create());
            AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(TradeMaster.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                    idMap.put(contract.id.contractId, contract.id);
                                });
                    });
        	Map m = g.fromJson(req.body(), Map.class);        	
        	TradeMaster.ContractId contractId = (ContractId) idMap.get(m.get("id"));         	
        	ExerciseCommand iouCreate = contractId.exerciseInvite(String.valueOf(m.get("client")));
            submit(client, req.params("name"), iouCreate);
            return "Iou creation submitted.";
        }, g::toJson);
        
        
        Spark.post("/clientAcceptBrokerAgreement/:name", (req, res) -> {   	
        	System.out.println(req.body());
        	TransactionFilter iouFilter = filterFor(TradeIssueProposal.TEMPLATE_ID, req.params("name"));       	
            
            BiMap<String, TradeIssueProposal.ContractId> idMap = Maps.synchronizedBiMap(HashBiMap.create());
            AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(TradeIssueProposal.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                    idMap.put(contract.id.contractId, contract.id);
                                });
                    });
        	Map m = g.fromJson(req.body(), Map.class);        	
        	TradeIssueProposal.ContractId contractId = (TradeIssueProposal.ContractId) idMap.get(m.get("id"));         	
        	ExerciseCommand iouCreate = contractId.exerciseAcceptTradeProposal();
            submit(client, req.params("name"), iouCreate);
            return "Iou creation submitted.";
        }, g::toJson);        
		//Broker to client agreement starts here
        
        
        //broker to exchange agreement starts here
        Spark.put("/brokerCreateExchangeAgreement/:name", (req, res) -> {        	
        	BrokerTradeMaster iou = g.fromJson(req.body(), BrokerTradeMaster.class);       	
            CreateCommand iouCreate = iou.create();
            submit(client, req.params("name"), iouCreate);
            return "Iou creation submitted.";
        }, g::toJson);
        
        Spark.post("/exchangeAcceptBrokerAgreement/:name", (req, res) -> {   	
        	System.out.println(req.body());
        	TransactionFilter iouFilter = filterFor(BrokerTradeMaster.TEMPLATE_ID, req.params("name"));       	
            
            BiMap<String, BrokerTradeMaster.ContractId> idMap = Maps.synchronizedBiMap(HashBiMap.create());
            AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(BrokerTradeMaster.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                    idMap.put(contract.id.contractId, contract.id);
                                });
                    });
        	Map m = g.fromJson(req.body(), Map.class);        	
        	BrokerTradeMaster.ContractId contractId = (BrokerTradeMaster.ContractId) idMap.get(m.get("id")); 
            BigDecimal big=new BigDecimal((String) (m.get("exchangeFee")));
        	ExchangeAccept ec = new ExchangeAccept(Long.parseLong((String)m.get("tradingLimit")),(String)m.get("volumeLimit"), big);
        	System.out.println(ec.toString());
        	System.out.println(big);
        	ExerciseCommand iouCreate = contractId.exerciseExchangeAccept(ec);
            submit(client, req.params("name"), iouCreate);
            return "Iou creation submitted.";
        }, g::toJson);
        
        
        Spark.post("/brokerAcceptExchangeAgreement/:name", (req, res) -> {   	
        	System.out.println(req.body());
        	TransactionFilter iouFilter = filterFor(BrokerTradeIssueProposal.TEMPLATE_ID, req.params("name"));       	
            
            BiMap<String, BrokerTradeIssueProposal.ContractId> idMap = Maps.synchronizedBiMap(HashBiMap.create());
            AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(BrokerTradeIssueProposal.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                    idMap.put(contract.id.contractId, contract.id);
                                });
                    });
        	Map m = g.fromJson(req.body(), Map.class);        	
        	BrokerTradeIssueProposal.ContractId contractId = (BrokerTradeIssueProposal.ContractId) idMap.get(m.get("id")); 
        	ExerciseCommand iouCreate = contractId.exerciseAcceptExchangeTradeProposal();
            submit(client, req.params("name"), iouCreate);
            return "Iou creation submitted.";
        }, g::toJson);
        
      //broker to exchange agreement ends here
      
      //orders starts here
        Spark.post("/clientOrderBrokerAgrement/:name", (req, res) -> { 
        	
        	boolean isExchageHaveBrokerAgreement = false;
        	
        	//get brokers for client      	
        	TransactionFilter iouFilter2 = filterFor(TradeIssueAgreement.TEMPLATE_ID, req.params("name"));
        	ArrayList<String> clientBrokers  = new ArrayList<String>();
            AtomicReference<LedgerOffset> acsOffset2 = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter2, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset2.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(TradeIssueAgreement.Contract::fromCreatedEvent)
                                .forEach(contract -> {                            	
                                	clientBrokers.add(contract.data.broker);
                                });
                    });
        	
            //get brokers for exchange
            
            JsonObject convertedObject1 = new Gson().fromJson(req.body(), JsonObject.class);        	
            JsonObject orderterms = convertedObject1.get("orderterms").getAsJsonObject();
            
            TransactionFilter iouFilter3= filterFor(BrokerTradeIssueAgreement.TEMPLATE_ID, orderterms.get("exchange").getAsString());
        	ArrayList<String> exchangeBrokers  = new ArrayList<String>();
            AtomicReference<LedgerOffset> acsOffset3 = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter3, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset3.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(BrokerTradeIssueAgreement.Contract::fromCreatedEvent)
                                .forEach(contract -> {                            	
                                	exchangeBrokers.add(contract.data.broker);
                                });
                    });          
            
            if(clientBrokers.stream() 
            .filter(exchangeBrokers::contains) 
            .collect(Collectors 
                         .toList()).size()>0) {
            	isExchageHaveBrokerAgreement = true;
            }
            
            
        	if(isExchageHaveBrokerAgreement) {
        	TransactionFilter iouFilter1 = filterFor(Trade.TEMPLATE_ID, req.params("name"));       	
            TreeSet<String> tradeSet = new TreeSet<String>();
            AtomicReference<LedgerOffset> acsOffset1 = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter1, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset1.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(Trade.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                	tradeSet.add(contract.data.terms.orderId);
                                });
                    });
            
        	  String OrderId = "Order-"+req.params("name").replace("_", "")+"_"+"1";
        	  if(tradeSet.size()>0) {
        		
        		int in = tradeSet.last().indexOf("_") + 1;
        		int tradenumber = Integer.parseInt(tradeSet.last().substring(in, tradeSet.last().length()))+1; 
        		OrderId = "Order-"+req.params("name").replace("_", "")+"_"+String.valueOf(tradenumber);			
        	  }
        	
        	JsonObject convertedObject = new Gson().fromJson(req.body(), JsonObject.class);        	
        	((JsonObject) convertedObject.get("orderterms")).addProperty("orderId" , OrderId);
        	TransactionFilter iouFilter = filterFor(TradeIssueAgreement.TEMPLATE_ID, req.params("name"));       	
            
            BiMap<String, TradeIssueAgreement.ContractId> idMap = Maps.synchronizedBiMap(HashBiMap.create());
            AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(TradeIssueAgreement.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                    idMap.put(contract.id.contractId, contract.id);
                                });
                    });
        	Map m = g.fromJson(convertedObject.toString(), Map.class);
            String jsonString = new Gson().toJson(m.get("orderterms"), Map.class); 
            System.out.println(jsonString);
        	OrderTerms orderTerms = g.fromJson(jsonString,OrderTerms.class);
        	TradeIssueAgreement.ContractId contractId = (TradeIssueAgreement.ContractId) idMap.get(m.get("id"));
        	ExerciseCommand iouCreate = contractId.exerciseOrder(orderTerms);
            submit(client, req.params("name"), iouCreate);
            return "Iou creation submitted.";
        }else {
        	
        	return "error";
        }
        }, g::toJson);
        
        
        
      //Execution process
        Spark.post("/exchangeTradeExecution/:name", (req, res) -> {   
            AtomicReference<LedgerOffset> acsOffset1 = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());
        	  String newExeId = "Exchange-"+req.params("name")+"_"+"1";
        	
        	
        	
        	System.out.println(req.body());
        	TransactionFilter iouFilter = filterFor(Trade.TEMPLATE_ID, req.params("name"));       	
            BiMap<String, Trade.ContractId> idMap = Maps.synchronizedBiMap(HashBiMap.create());
            AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());
            client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(Trade.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                    idMap.put(contract.id.contractId, contract.id);
                                });
                    });
            
            
        	Map m = g.fromJson(req.body(), Map.class);  
        	
        ArrayList<String> indivu=  (ArrayList<String>) m.get("id");
        TreeSet<String> tradeSet = new TreeSet<String>();
   for(String ing:indivu) {
	   TransactionFilter iouFilter1 = filterFor(TradeExecuted.TEMPLATE_ID,req.params("name") );  
	   Trade.ContractId contractId = (Trade.ContractId) idMap.get(ing.trim());
	   client.getActiveContractSetClient().getActiveContracts(iouFilter1, true)
       .blockingForEach(response -> {
           response.getOffset().ifPresent(offset -> acsOffset1.set(new LedgerOffset.Absolute(offset)));
           response.getCreatedEvents().stream()
                   .map(TradeExecuted.Contract::fromCreatedEvent)
                   .forEach(contract -> {                                    
                   	tradeSet.add(contract.data.executionId);
                   });
       });
	   
	   
	   System.out.println(tradeSet.size());
	   if(tradeSet.size()>0) {
   		int in = tradeSet.last().trim().indexOf("_") + 1;
   		int tradenumber = Integer.parseInt(tradeSet.last().substring(in, tradeSet.last().length()))+1; 
   		newExeId = "Exchange-"+req.params("name")+"_"+ String.valueOf(tradenumber);			
   	  }
	   ExerciseCommand iouCreate = contractId.exerciseExecute(newExeId); 
	   submit(client, req.params("name"), iouCreate);
   }
        	
        	
        	          
        	
            return "Iou creation submitted.";
        }, g::toJson);
        
            
        
        // POST METHOD ORDER TO TRADE STARTS HERE
     
        Spark.post("/executionToTradeGenerate/:name", (req, res) -> {   	
        	System.out.println(req.body());
        	TransactionFilter iouFilter = filterFor(TradeExecuted.TEMPLATE_ID, req.params("name"));       	
            
            BiMap<String, TradeExecuted.ContractId> idMap = Maps.synchronizedBiMap(HashBiMap.create());
            AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

            client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                    .blockingForEach(response -> {
                        response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                        response.getCreatedEvents().stream()
                                .map(TradeExecuted.Contract::fromCreatedEvent)
                                .forEach(contract -> {                                    
                                    idMap.put(contract.id.contractId, contract.id);
                                });
                   });
        	Map m = g.fromJson(req.body(), Map.class);  
        	 ArrayList<String> indivu=  (ArrayList<String>) m.get("id");
        	 TreeSet<String> tradeSet = new TreeSet<String>();
        	 for(String ing:indivu) {
        		 TransactionFilter iouFilter1 = filterFor(TradeCreated.TEMPLATE_ID,req.params("name") );
        		 TradeExecuted.ContractId contractId = (TradeExecuted.ContractId) idMap.get(ing.trim());
        		 client.getActiveContractSetClient().getActiveContracts(iouFilter1, true)
               .blockingForEach(response -> {
                   response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                   response.getCreatedEvents().stream()
                           .map(TradeCreated.Contract::fromCreatedEvent)    
                           .forEach(contract -> {                                    
                        		tradeSet.add(contract.data.executionId);
                           });
               });
        		   System.out.println(tradeSet.size());
        		   if(tradeSet.size()>0) {
        	   		int in = tradeSet.last().trim().indexOf("_") + 1;
               }  	
        	ExerciseCommand iouCreate = contractId.exerciseGenerate_Trade();
            submit(client, req.params("name"), iouCreate);
        }
            return "Trade Generated.";
        }, g::toJson);   
        

        while (true)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    
   // Agreement between broker and client starts here 
    
    private static ConcurrentHashMap<String, TradeMaster.Contract> getCreateClientAgreement(DamlLedgerClient client,String name) {
    	TransactionFilter iouFilter = filterFor(TradeMaster.TEMPLATE_ID, name);
    	ConcurrentHashMap<String, TradeMaster.Contract> contracts = new ConcurrentHashMap<>();
       
        AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

        client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                .blockingForEach(response -> {
                    response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                    response.getCreatedEvents().stream()
                            .map(TradeMaster.Contract::fromCreatedEvent)
                            .forEach(contract -> {                                
                                contracts.put(contract.id.contractId, contract);
                        });
                });
        return contracts;
    
    }    
    
    private static ConcurrentHashMap<String, TradeIssueProposal.Contract> getClientInviteAgreement(DamlLedgerClient client,String name) {
    	TransactionFilter iouFilter = filterFor(TradeIssueProposal.TEMPLATE_ID, name);
    	ConcurrentHashMap<String, TradeIssueProposal.Contract> contracts = new ConcurrentHashMap<>();
        AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

        client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                .blockingForEach(response -> {
                    response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                    response.getCreatedEvents().stream()
                            .map(TradeIssueProposal.Contract::fromCreatedEvent)
                            .forEach(contract -> {
                            	contracts.put(contract.id.contractId, contract);

                            });
                });
        
        return contracts;
    
    } 
    
     
    private static ConcurrentHashMap<String, TradeIssueAgreement.Contract> getClientAgreement(DamlLedgerClient client,String name) {
    	TransactionFilter iouFilter = filterFor(TradeIssueAgreement.TEMPLATE_ID, name);
    	ConcurrentHashMap<String, TradeIssueAgreement.Contract> contracts = new ConcurrentHashMap<>();
        AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

        client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                .blockingForEach(response -> {
                    response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                    response.getCreatedEvents().stream()
                            .map(TradeIssueAgreement.Contract::fromCreatedEvent)
                            .forEach(contract -> {                            	
                            	contracts.put(contract.id.contractId, contract);
                            });
                });
        
         return contracts;
    
    }    
    
  // Agreement between broker and client ends here
    
  // Agreement between broker and exchange starts here
    private static ConcurrentHashMap<String, BrokerTradeMaster.Contract> getCreateExchangeAgreement(DamlLedgerClient client,String name) {
    	TransactionFilter iouFilter = filterFor(BrokerTradeMaster.TEMPLATE_ID, name);
    	ConcurrentHashMap<String, BrokerTradeMaster.Contract> contracts = new ConcurrentHashMap<>();
        AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

        client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                .blockingForEach(response -> {
                    response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                    response.getCreatedEvents().stream()
                            .map(BrokerTradeMaster.Contract::fromCreatedEvent)
                            .forEach(contract -> {                                
                                contracts.put(contract.id.contractId, contract);

                            });
                });
        return contracts;
    
    } 
    
    private static ConcurrentHashMap<String, BrokerTradeIssueProposal.Contract> getExchangeAcceptedAgreement(DamlLedgerClient client,String name) {
    	TransactionFilter iouFilter = filterFor(BrokerTradeIssueProposal.TEMPLATE_ID, name);
    	ConcurrentHashMap<String, BrokerTradeIssueProposal.Contract> contracts = new ConcurrentHashMap<>();
    	
        AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

        client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                .blockingForEach(response -> {
                    response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                    response.getCreatedEvents().stream()
                            .map(BrokerTradeIssueProposal.Contract::fromCreatedEvent)
                            .forEach(contract -> {
                            	contracts.put(contract.id.contractId, contract);

                            });
                });
        return contracts;
    
    } 
    
    private static ConcurrentHashMap<String, BrokerTradeIssueAgreement.Contract> getExchangeAgreement(DamlLedgerClient client,String name) {
    	TransactionFilter iouFilter = filterFor(BrokerTradeIssueAgreement.TEMPLATE_ID, name);
    	ConcurrentHashMap<String, BrokerTradeIssueAgreement.Contract> contracts = new ConcurrentHashMap<>();
        AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

        client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                .blockingForEach(response -> {
                    response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                    response.getCreatedEvents().stream()
                            .map(BrokerTradeIssueAgreement.Contract::fromCreatedEvent)
                            .forEach(contract -> {
                            	contracts.put(contract.id.contractId, contract);
                            });
                });
        return contracts;
    
    } 
    
    // Agreement between broker and exchange ends here 
    
    
    //Orders starts here   
   
    
     private static ConcurrentHashMap<String, Trade.Contract> getOrders(DamlLedgerClient client,String name) {
    	TransactionFilter iouFilter = filterFor(Trade.TEMPLATE_ID, name);
    	ConcurrentHashMap<String, Trade.Contract> contracts = new ConcurrentHashMap<>();
        AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

        client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                .blockingForEach(response -> {
                    response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                    response.getCreatedEvents().stream()
                            .map(Trade.Contract::fromCreatedEvent)
                            .forEach(contract -> {
                            	contracts.put(contract.id.contractId, contract);

                            });
                });
        
        return contracts;
    
    }   
       
  //ORDERS ENDS HERE  
     
  //EXECUTION STARTS HERE
     
     private static ConcurrentHashMap<String, TradeExecuted.Contract> getTradeExecuted(DamlLedgerClient client,String name) {
      	TransactionFilter iouFilter = filterFor(TradeExecuted.TEMPLATE_ID, name);
      	ConcurrentHashMap<String, TradeExecuted.Contract> contracts = new ConcurrentHashMap<>();
          AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

          client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                  .blockingForEach(response -> {
                      response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                      response.getCreatedEvents().stream()
                              .map(TradeExecuted.Contract::fromCreatedEvent)
                              .forEach(contract -> {
                            	  contracts.put(contract.id.contractId, contract);

                              });
                  });
          
          return contracts;
      
      }   
     
 
   //GENERATION OF TRADES STARTS HERE
     
     private static ConcurrentHashMap<String, TradeCreated.Contract> getTradeCreated(DamlLedgerClient client,String name) {
       	TransactionFilter iouFilter = filterFor(TradeCreated.TEMPLATE_ID, name);
       	ConcurrentHashMap<String, TradeCreated.Contract> contracts = new ConcurrentHashMap<>();
           AtomicReference<LedgerOffset> acsOffset = new AtomicReference<>(LedgerOffset.LedgerBegin.getInstance());

           client.getActiveContractSetClient().getActiveContracts(iouFilter, true)
                   .blockingForEach(response -> {
                       response.getOffset().ifPresent(offset -> acsOffset.set(new LedgerOffset.Absolute(offset)));
                       response.getCreatedEvents().stream()
                               .map(TradeCreated.Contract::fromCreatedEvent)
                               .forEach(contract -> {
                             	  contracts.put(contract.id.contractId, contract);

                               });
                   });
           
           return contracts;
       
       } 
     
    private static Empty submit(LedgerClient client, String party, Command c) {
        return client.getCommandSubmissionClient().submit(
                UUID.randomUUID().toString(),
                "IouApp",
                UUID.randomUUID().toString(),
                party,
                Instant.EPOCH,
                Instant.EPOCH.plusSeconds(10),
                Collections.singletonList(c))
                .blockingGet();
    }

    private static TransactionFilter filterFor(Identifier templateId, String party) {
        InclusiveFilter inclusiveFilter = new InclusiveFilter(Collections.singleton(templateId));
        Map<String, Filter> filter = Collections.singletonMap(party, inclusiveFilter);
        return new FiltersByParty(filter);
    }
    
    
	
}