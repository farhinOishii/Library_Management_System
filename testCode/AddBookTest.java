package testCode;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import library.management.system.Model.AddBookModel;
import library.management.system.Model.BookDetailsModel;
import library.management.system.Model.IssueBookModel;
import library.management.system.Model.RenewBookModel;
import library.management.system.Model.connectDB;

public class AddBookTest {
	
	AddBookModel model;
	
	
    JTextField shelfNo,title,isbn,author,quantity;
    
	

    @Test
    void testAddBookRecord() {
        
        try{
        	connectDB connect = new connectDB();
        	model = new AddBookModel();
        	shelfNo = new JTextField();
        	title = new JTextField();
        	isbn = new JTextField();
        	author = new JTextField();
        	quantity = new JTextField();
        	
            shelfNo.setText("a123");
            title.setText("TestBookName");
            isbn.setText("1231231231231");
            author.setText("TestAuthorName");
            quantity.setText("123");
            model.addBookRecord(title, isbn, author, shelfNo, quantity);
            
            String testQuery = "select * from bookdetails where shelfNo=?";
            PreparedStatement st = connect.conn.prepareStatement(testQuery);
            st.setString(1, shelfNo.getText());
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
            	assertEquals(title.getText(), rs.getString("title"), "Title Mismatch");
            	assertEquals(isbn.getText(), rs.getString("isbn"), "ISBN Mismatch");
            	assertEquals(author.getText(), rs.getString("author"), "Author Mismatch");
            	assertEquals(quantity.getText(), rs.getString("quantity"), "Quantity Mismatch");
            }
            
            st.close();
            rs.close();
            connect.conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
    
    

}
