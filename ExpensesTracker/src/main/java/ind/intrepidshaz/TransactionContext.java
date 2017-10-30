package ind.intrepidshaz;

import java.util.Date;
import java.util.List;

import ind.intrepidshaz.Transaction.PaymentMode;
import ind.intrepidshaz.Transaction.TransactionType;

public class TransactionContext {

	public enum ForWith {
		For,
		With
	}
	
	private ForWith forWith;
	private List<String> people;
	private String category;
	private String subCategory;

}
