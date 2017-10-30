package ind.intrepidshaz;

import java.util.Date;

class TransactionDateContext {
	private static TransactionDateContext transactionDateContext;
	private Date transactionDate;

	static TransactionDateContext getInstance() {
		if (transactionDateContext == null) {
			transactionDateContext = new TransactionDateContext();
		}
		return transactionDateContext;
	}
	
	Date getTransactionDate() {
		return transactionDate;
	}

	void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}	
}
