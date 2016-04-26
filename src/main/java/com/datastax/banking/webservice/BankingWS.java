package com.datastax.banking.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.datastax.banking.model.Aggregate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.banking.model.Transaction;
import com.datastax.banking.service.SearchService;
import com.datastax.banking.service.SearchServiceImpl;

@WebService
@Path("/")
public class BankingWS {

	private Logger logger = LoggerFactory.getLogger(BankingWS.class);
	private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyyMMdd");

	//Service Layer.
	private SearchService service = new SearchServiceImpl();
	
//	@GET
//	@Path("/getalltransactionsbyccnoanddates/{creditcardno}/{from}/{to}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAllTransactionsByCCnoAndDates(@PathParam("creditcardno") String ccNo, @PathParam("from") String fromDate,
//			@PathParam("to") String toDate) {
//
//		DateTime from = DateTime.now();
//		DateTime to = DateTime.now();
//		try {
//			from = new DateTime(inputDateFormat.parse(fromDate));
//			to = new DateTime(inputDateFormat.parse(toDate));
//		} catch (ParseException e) {
//			String error = "Caught exception parsing dates " + fromDate + "-" + toDate;
//
//			logger.error(error);
//			return Response.status(Status.BAD_REQUEST).entity(error).build();
//		}
//
//		List<Transaction> result = service.getAllTransactionsByCCnoAndDates(ccNo, null, from, to);
//
//		return Response.status(Status.OK).entity(result).build();
//	}

    @GET
	@Path("/getalltransactions/")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTransactions() {
	   List<Transaction> result = service.getAllTransactions();

	   return Response.status(Status.OK).entity(result).build();
    }

	@GET
  	@Path("/getdailytransactionsbymerchant/{merchant}/{day}")
  	@Produces(MediaType.APPLICATION_JSON)
  	public Response getDailyTransactionsByMerchant(@PathParam("merchant") String merchant, @PathParam("day") int day) {
		logger.info("WebService: " + merchant + "," + day);

  		List<Transaction> result = service.getDailyTransactionsByMerchant(merchant, day);

  		return Response.status(Status.OK).entity(result).build();
  	}

	@GET
	@Path("/getyearlytransactionsbyccno/{ccNo}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getYearlyTransactionsByccNo(@PathParam("ccNo") String ccNo, @PathParam("year") int year) {
		logger.info("WebService: " + ccNo + "," + year);

		List<Aggregate> result = service.getYearlyTransactionsByccNo(ccNo, year);

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getallrejectedtransactions/")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRejectedTransactions() {
		List<Transaction> result = service.getAllRejectedTransactions();

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getalldeclinedtransactions/")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDeclinedTransactions() {
		List<Transaction> result = service.getAllDeclinedTransactions();

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getfacetedtransactionsbymerchant/")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFacetedTransactionsByMerchant() {

		String result = service.getFacetedTransactionsByMerchant();

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getfacetedtransactionsbystatusinlastperiod/{period}")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFacetedTransactionsByStatusInLastPeriod(@PathParam("period") String lastPeriod) {

		String result = service.getFacetedTransactionsByStatusInLastPeriod(lastPeriod);

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getfacetedtransactionsbyccnoandstatusinlastperiod/{creditcardno}/{period}")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFacetedTransactionsByStatusInLastPeriod(@PathParam("creditcardno") String ccNo, @PathParam("period") String lastPeriod) {

		String result = service.getFacetedTransactionsByCCnoAndStatusInLastPeriod(ccNo, lastPeriod);

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getalltransactionsbyccno/{creditcardno}")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTransactionsByCCno(@PathParam("creditcardno") String ccNo) {

		logger.info("WebService: " + ccNo);
		List<Transaction> result = service.getAllTransactionsByCCno(ccNo);

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getallfraudulenttransactionsbyccno/{creditcardno}")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFraudulentTransactionsByCCno(@PathParam("creditcardno") String ccNo) {

		logger.info("WebService: " + ccNo);
		List<Transaction> result = service.getAllFraudulentTransactionsByCCno(ccNo);

		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/getallfraudulenttransactionsinlastperiod/{period}")    // SA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFraudulentTransactionsInLastPeriod(@PathParam("period") String lastPeriod) {

		logger.info("WebService: " + lastPeriod);
		List<Transaction> result = service.getAllFraudulentTransactionsInLastPeriod(lastPeriod);

		return Response.status(Status.OK).entity(result).build();
	}

}
