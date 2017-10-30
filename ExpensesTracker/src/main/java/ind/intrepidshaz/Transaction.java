package ind.intrepidshaz;

import java.util.Date;

public class Transaction {

	public enum TransactionType {
		Income, Expense
	}

	public enum PaymentMode {
		Cash, CreditCard, DebitCard, NetBanking, PayTm, ShruDC, Unknown
	}

	private Date date;
	private int money;
	private TransactionType transactionType;
	private PaymentMode paymentMode;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

}
