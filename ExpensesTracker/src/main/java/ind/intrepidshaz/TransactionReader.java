package ind.intrepidshaz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;

import ind.intrepidshaz.Transaction.PaymentMode;
import ind.intrepidshaz.Transaction.TransactionType;

public class TransactionReader {
	
	private BufferedReader bufferedReader;
	private String transactionFile;
	private TransactionLineParser transactionLineParser;
	
	public TransactionReader(String transactionFile) throws FileNotFoundException {
		
		this.bufferedReader = new BufferedReader(new FileReader(transactionFile));
		this.transactionFile = transactionFile;
	}
	
	public String getTransactionFile() {
		return transactionFile;
	}
	
	public void setTransactionLineParser(TransactionLineParser transactionLineParser) {
		this.transactionLineParser = transactionLineParser;
	}
	
	public Transaction Read() throws TransactionLineParserNotFound {
		if(transactionLineParser == null) {
			throw new TransactionLineParserNotFound("Set the TransactionLineParser to read the transactions file");
		}
		try {
			String lineItem = bufferedReader.readLine();			
			while (lineItem != null) {
				this.transactionLineParser.parseLine(lineItem);
				if (this.transactionLineParser.getTransaction() != null) {
					return this.transactionLineParser.getTransaction();
				}
				lineItem = bufferedReader.readLine();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
