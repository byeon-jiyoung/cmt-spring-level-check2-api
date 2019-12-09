package kr.or.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.mariadb.jdbc.internal.queryresults.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.domain.Customer;
import kr.or.domain.Orders;
import kr.or.domain.Total;

@Repository
public class TotalDaoImpl implements TotalDao {
	// ★ResultSet - select할 때만 사용한다!!!!!!!
	// ★Connection - 디비연결, PreparedStatement - 디비 sql넘기는거, ResultSet - 상태반환
	// ★ResultSet은 디비에서 커서의 역할과 같다고 보면 된다.

	@Autowired
	DataSource dataSource;

	@Override
	public List<Total> selectTotalList() {
		List<Total> totalList = new ArrayList<Total>();
		
		String sql = "select orders.orders_number, orders.customer_number, customer.customer_name, orders.product_number, product.product_name\r\n"
				+ "from orders join product on product.product_number = orders.product_number join customer on customer.customer_number = orders.customer_number";

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
			 ResultSet resultSet = preparedStatement.executeQuery();) {
			
			while (resultSet.next()) {
				totalList.add(getTotal(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return totalList;
	}
	
	private Total getTotal(ResultSet resultSet) throws SQLException {
		return new Total(resultSet.getInt("orders.orders_number"), resultSet.getInt("orders.customer_number"),
						 resultSet.getString("customer.customer_name"), resultSet.getInt("orders.product_number"), resultSet.getString("product.product_name"));
	}
}
