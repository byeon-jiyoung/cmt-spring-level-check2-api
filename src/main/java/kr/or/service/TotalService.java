package kr.or.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.domain.Customer;
import kr.or.domain.Total;

public interface TotalService {
	//total
	public List<Total> selectTotalList();
	
	//customer
	public void updateCustomer(Customer customer) throws SQLException;
}
