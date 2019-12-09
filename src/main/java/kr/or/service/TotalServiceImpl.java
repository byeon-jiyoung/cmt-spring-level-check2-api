package kr.or.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.dao.CustomerDao;
import kr.or.dao.TotalDao;
import kr.or.domain.Customer;
import kr.or.domain.Total;

@Service
public class TotalServiceImpl implements TotalService {
	
	@Autowired
	TotalDao totalDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public List<Total> selectTotalList() {
		return totalDao.selectTotalList();
	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		customerDao.updateCustomer(customer);
	}

}
