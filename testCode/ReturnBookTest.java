package testCode;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import library.management.system.Model.ReturnBookModel;
import library.management.system.Model.connectDB;

class ReturnBookTest {

	JTextField shelfNo,title,libCardNo;
	JTextField stLibID, bookTitle, stdName, Issue_date;
	ReturnBookModel model;
	@Test
    void testGetBookRecord() {
    	try {
    		connectDB connect = new connectDB();
			model = new ReturnBookModel();
			ResultSet rs = null;
			
			PreparedStatement st1 = null;
			shelfNo = new JTextField();
			shelfNo.setText("x125");
			
			rs = model.getBookRecord(st1, shelfNo.getText());
			if(rs.next()) {
				assertEquals("How to Solve It by Computer", rs.getString("title"), "Title Mismatch");
			}
			rs.close();
			connect.conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	
	@Test
	void testGetStudentRecord() {
	try {
		connectDB connect = new connectDB();
		model = new ReturnBookModel();
		ResultSet rs = null;
		
		PreparedStatement st1 = null;
		libCardNo = new JTextField();
		libCardNo.setText("9997");
		
		rs = model.getStudentRecord(libCardNo.getText());
		if(rs.next()) {
			assertEquals("yagami", rs.getString("username"), "Username Mismatch");
		}
		rs.close();
		connect.conn.close();
	} catch(Exception e) {
		// TODO: handle exception
	}
	}
	
	@Test
	void testReturnTheBook() {
		try {
			connectDB connect = new connectDB();
			shelfNo = new JTextField();
			shelfNo.setText("x125");
			String testQuery = "select * from issuedbooks where shelfNo =?";
			PreparedStatement st = connect.conn.prepareStatement(testQuery);
			st.setString(1, shelfNo.getText());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				assertEquals("x125",rs.getString("shelfNo"));
				assertEquals("10007",rs.getString("stLibID"));
				assertEquals("How to Solve It by Computer",rs.getString("bookTitle"));
				assertEquals("cc",rs.getString("stName"));
				assertEquals("2021-09-03",rs.getString("Issue_date"));
				//assertEquals("2021-09-17",rs.getString("Due_Date"));
			}
			st.close();
			rs.close();
			connect.conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
