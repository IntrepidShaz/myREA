package ind.intrepidshaz;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {    	
    	TransactionReader reader;
    	FileWriter fw = null;
        try {
        	reader = new TransactionReader("C:\\Users\\IntrepidShaz\\Desktop\\expenses.txt");
        	reader.setTransactionLineParser(new TransactionLineParser());
        	fw = new FileWriter("C:\\Users\\IntrepidShaz\\Desktop\\expenses.csv");
        	
        	Transaction tx = null;
        	while((tx = reader.Read()) != null) {
        		fw.write(Integer.toString(tx.getMoney()) + "," +
        				 tx.getPaymentMode().toString() + "," + 
        				 Integer.toString(tx.getDate().getYear() + 1900) + "," + 
        				 Integer.toString(tx.getDate().getMonth() + 1) + "," +
        				 tx.getDate().toString() + 
        				 System.lineSeparator());
        		fw.flush();
        	}
        	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransactionLineParserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }    
}
