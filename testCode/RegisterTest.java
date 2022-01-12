package testCode;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import library.management.system.Model.RegisterModel;
import library.management.system.Model.connectDB;

class RegisterTest {
	RegisterModel model;
	JTextField name, username;
	JPasswordField password;
	@Test
	void testSignUp() {
		try {
			connectDB connect = new connectDB();
			name.setText("testName");
			username.setText("testUsername");
			password.setText("testPass");
			model.signup(name, username, password);
			String testQuery = "select * from account where name=?";
			PreparedStatement st = connect.conn.prepareStatement(testQuery);
			st.setString(1, name.getText());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				assertEquals("testName",rs.getString("name"));
				assertEquals("testUsername",rs.getString("username"));
				assertEquals("testPass",rs.getString("password"));
			}
			st.close();
			rs.close();
			connect.conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
