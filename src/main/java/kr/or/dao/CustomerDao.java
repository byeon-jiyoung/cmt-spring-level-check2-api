package kr.or.dao;

import java.sql.SQLException;

import kr.or.domain.Customer;

public interface CustomerDao {
	public int updateCustomer(Customer customer) throws SQLException;
}
