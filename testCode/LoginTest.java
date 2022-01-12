package testCode;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import library.management.system.Model.LoginModel;
import library.management.system.Model.connectDB;

class LoginTest {
	
	LoginModel model;
	JTextField username;
	JPasswordField password;

	@Test
	void testLogin() {
		try {
			connectDB connect = new connectDB();
			
			username.setText("ryuk");
			password.setText("apple");
			model.login(username, password);
			String testQuery = "select * from account where username=?";
			PreparedStatement st = connect.conn.prepareStatement(testQuery);
			st.setString(1, username.getText());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				assertEquals(username.getText(),rs.getString("username"));
				assertEquals(password.getText(),rs.getString("password"));
			}
			st.close();
			rs.close();
			connect.conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
