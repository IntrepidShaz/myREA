package ind.intrepidshaz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ind.intrepidshaz.Transaction.PaymentMode;
import ind.intrepidshaz.Transaction.TransactionType;

public class TransactionLineParser {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
	private Transaction transaction = null;

	PaymentMode getPaymentMode(String mode) {
		if(mode.equalsIgnoreCase("pt")) {
			return PaymentMode.PayTm;
		} else if(mode.equalsIgnoreCase("cc")) {
			return PaymentMode.CreditCard;
		} else if (mode.equalsIgnoreCase("dc")) {
			return PaymentMode.DebitCard;
		} else if (mode.equalsIgnoreCase("nb")) {
			return PaymentMode.NetBanking;
		} else if (mode.equalsIgnoreCase("Shru dc")) {
			return PaymentMode.ShruDC;
		}
		return PaymentMode.Unknown;
	}

	public TransactionLineParser parseLine(String lineItem) {
		resetTransaction();
		if (isBlankLine(lineItem)) {
			return this;
		}
    	
		Date txDate = parseDate(lineItem);
    	if(txDate != null) {
    		TransactionDateContext.getInstance().setTransactionDate(txDate);
    		return this;
    	}
    	
    	transaction = parseTransaction(lineItem);
    	return this;

	}

	private Transaction parseTransaction(String lineItem) {
		
		String[] txdetails = lineItem.split(" ");		
		String[] money = txdetails[0].split("[(]");
		int moneyValue;
		
		try {
			moneyValue = Integer.parseInt(money[0].replaceAll("[,]", ""));
		} catch (NumberFormatException e) {
			return null;  //ignore this lineItem
		}
		
		Transaction transaction = new Transaction();
		transaction.setMoney(moneyValue);
		
		if(money.length ==1) {
			transaction.setPaymentMode(PaymentMode.Cash);
		}
		else {
			transaction.setPaymentMode(getPaymentMode(money[1].replaceAll("[)]", "")));
		}
		
		transaction.setTransactionType(TransactionType.Expense);
		transaction.setDate(TransactionDateContext.getInstance().getTransactionDate());
		
		return transaction;		
	}

	private void resetTransaction() {
		transaction = null;
	}
	
	public Transaction getTransaction() {
		return this.transaction;
	}

	private Date parseDate(String lineItem) {
		Date txDate = null;
    	try {
			 txDate = dateFormat.parse(lineItem);
		} catch (ParseException e1) {
			// ignore exception
		}
		return txDate;
	}

	private boolean isBlankLine(String lineItem) {
		if (lineItem.trim().length() == 0) {
			return true;
		}
		return false;
	}

}