package kr.or.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public int updateCustomer(Customer customer) throws SQLException {
		String sql = "update customer set customer_number=?, customer_name=? where customer_number=?";
		int result = -1;
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setInt(1, customer.getCustomerNumber());
			preparedStatement.setString(2, customer.getCustomerName());
			preparedStatement.setInt(3, customer.getCustomerNumber());
			
			result = preparedStatement.executeUpdate();
		}
		
		return result;
	}
}
