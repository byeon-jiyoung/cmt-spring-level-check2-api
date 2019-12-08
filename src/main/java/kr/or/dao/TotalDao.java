package kr.or.dao;

import java.sql.SQLException;
import java.util.List;
import kr.or.domain.Customer;
import kr.or.domain.Total;

public interface TotalDao {
	public List<Total> selectTotalList();
	public void updateCustomer(Customer customer) throws SQLException;
}
