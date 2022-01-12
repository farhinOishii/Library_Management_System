package testCode;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

import library.management.system.Model.BookDetailsModel;

public class BookDetailsTest {
	BookDetailsModel model;
	@Test
	void testBook() {
		try {
			model = new BookDetailsModel();
			
			ResultSet rs = null;
			rs = model.Book(rs);
			if(rs.next()) {
				assertEquals("How to Solve It by Computer", rs.getString(1));
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
