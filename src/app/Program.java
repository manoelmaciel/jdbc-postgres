package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {

		Connection conn = DB.getConnection();

		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(
				"SELECT * FROM tb_order " + 
						"INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id " + 
						"INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");

		Map<Long, Order> map = new HashMap<>();
		Map<Long, Product> prods = new HashMap<>();
		while (rs.next()) {
			
			Long orderId = rs.getLong("order_Id");
			if(map.get(orderId) == null) {
				Order order = instantiateOrder(rs);
				map.put(orderId, order);
				// System.out.println(order);
			}
			
			Long productId = rs.getLong("product_id");
			if(prods.get(productId) == null) {
				Product p = instantiateProduct(rs);
				prods.put(productId, p);
				// System.out.println(p);
			}
			map.get(orderId).getProducts().add(prods.get(productId));
			
		}
		
		for(long OrderId : map.keySet()) {			
			System.out.println(map.get(OrderId));
			for(Product p : map.get(OrderId).getProducts()) {
				System.out.println(p);
			}
			System.out.println();
		}
		
	}

	public static Product instantiateProduct(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong("product_id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getDouble("price"));
		product.setImageUri(rs.getString("image_uri"));
		return product;
	}

	public static Order instantiateOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("order_id"));
		order.setLatitude(rs.getDouble("latitude"));
		order.setLongitude(rs.getDouble("longitude"));
		order.setMoment(rs.getTimestamp("moment").toInstant());
		order.setStatus(OrderStatus.values()[rs.getInt("status")]);
		return order;
	}

}
