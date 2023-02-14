package kr.edu.mit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.SetOfIntegerSyntax;

public class FruitStoreDAOImpl implements FruitStoreDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet result = null;

	@Override
	public void insertFruit(FruitVO vo) {
		// DB �����ؼ� ���ϵ��
		// JAVA���� DB�����ϴ� ��� JDBC
		// 1. DB����
		// 1-1. JDBC����̹� �ε�
		// 1-2. �����ؼ� Connection ��ü����
		// 2. ����ó��
		// 2-1. Ŀ�ؼǰ�ü�� ������ Statement ��ü����
		// 2-2. ������Ʈ��Ʈ ��ü�� ������ query �۾�
		// select ���ǰ���� ResultSet ��ü�� �޾Ƽ� �۾�
		dbConn();
		try {
			pstmt = conn.prepareStatement("insert into fruit(fruit_name,price,quantity) value(?,?,?)");
			pstmt.setString(1, vo.getFruit_name());// ?-> ä���
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getQuantity());

			pstmt.executeUpdate();// ����, ����, �����ÿ��� exceuteUpdate()�� -��ȯ�� int ó���� ���ǰ���
									// read(select)�ÿ��� exceueteQuery() �̿� - ��ȯ�� ResultSet ��ü�� ������� �����ش�

			// String query = "insert into fruit(fruit_name,price,quantity) value('" + name
			// + "'," + price + "," + quantity
			// + ")";
			// stmt.executeUpdate(query);
			// int count = stmt.executeUpdate("insert into fruit(fruit_name,price,quantity)
			// value('������',1000,150)");
			// int count = stmt.executeUpdate(query);
			// System.out.println("�������� ���� ó���� ����: " + count);
		} catch (Exception e) {
			System.out.println("DB�������");
			e.printStackTrace();
		}

		// 2. ����ó��
		// 2-1. Ŀ�ؼǰ�ü�� ������ Statement ��ü����

		// 2-2. ������Ʈ��Ʈ ��ü�� ������ query �۾�
		// select ���ǰ���� ResultSet ��ü�� �޾Ƽ� �۾�

		// 3. ��� �� DB���� ����
		// ResultSet, Statement, Connection ��ü �ݾ��ֱ�
		dbClose();

	}

	// insert into fruit(fruit_name,price,quantity) value('����',15000,10);
	// ==>value(vo.get..)

	@Override
	public ArrayList<FruitVO> listFruit() {
		ArrayList<FruitVO> list = new ArrayList<>();
		// 1.db����
		dbConn();
		// 2.�����۾��� �����������(ResultSet)
		try {
			pstmt = conn.prepareStatement("select * from fruit order by fruit_code");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// next() �������� ����Ŵ, ������ �����డ��Ű�� �����̸� true ������ false
				// int code =rs.getInt("fruit_code");
				// String name =rs.getString("fruit_name");
				// int price = rs.getInt("price");
				// int quantity =rs.getInt("quantity");
				// �׽�Ʈ�� System.out.println(code+" "+name+" "+price+" "+quantity);
				FruitVO vo = new FruitVO();// �ۿ� �ۼ��ϸ� �ȵȴ�. ���� ��� ���̱� ������
				vo.setFruit_code(rs.getInt("fruit_code"));
				vo.setFruit_name(rs.getString("fruit_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setQuantity(rs.getInt("quantity"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 3. ����Ÿ������ ��ȯ�ϱ�
		// 4. db�ݱ�
		dbClose();
		// 5. ��ȯ�Ѱ� ����
		return list;
	}

	// DB����
	void dbConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "aaa", "Tnwls91471250!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// db�ݱ�
	void dbClose() {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateQuantityFruit(FruitVO vo) {
		dbConn();
		try {
			pstmt = conn.prepareStatement("update fruit set quantity= quantity+? where fruit_code=?");
			pstmt.setInt(1, vo.getQuantity());
			pstmt.setInt(2, vo.getFruit_code());

			pstmt.executeUpdate();// ����, ����, �����ÿ��� exceuteUpdate()�� -��ȯ�� int ó���� ���ǰ���
									// read(select)�ÿ��� exceueteQuery() �̿� - ��ȯ�� ResultSet ��ü�� ������� �����ش�

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		dbClose();

	}
	

	@Override
	public int totalFruit(FruitVO vo) {
		dbConn();
		int total = -1;
		try {
			pstmt = conn.prepareStatement("select price*? from fruit where fruit_code=?");
			pstmt.setInt(1, vo.getQuantity());
			pstmt.setInt(2, vo.getFruit_code());// Query �ϼ�
			result = pstmt.executeQuery();
			result.next();
			total = result.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return total;
	}

	@Override
	public void insertSales(int fruit_code, int quantity) {// �Ǹų��� �߰�
		dbConn();
		try {
			conn.setAutoCommit(false);// ����Ŀ�� ����
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement("insert into sales(sales_quantity) value(?)");
			pstmt.setInt(1, quantity);
			pstmt.executeUpdate();
			result = pstmt.executeQuery("select last_insert_id()");// �Էµ� Ű�� Ȯ��
			result.next();
			int key = result.getInt(1);
			// insert into fruit_has_sales values(2,5);#2�� ������ 5�� �ȷȴٴ� ��
			// pstmt.executeLargeUpdate("insert into fruit_has_sales values(" +fruit_code+
			// ","+key+ ")");
			// or
			pstmt.close();
			pstmt = conn.prepareStatement("insert into fruit_has_sales values(?,?)");
			pstmt.setInt(1, fruit_code);
			pstmt.setInt(2, key);
			pstmt.executeUpdate();

			pstmt.close();

			// 4.������ó��
			pstmt = conn.prepareStatement("update fruit set quantity= quantity-? where fruit_code=?");
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, fruit_code);
			pstmt.executeUpdate();

			pstmt.close();

			conn.commit();// �����̸� Ŀ��
		} catch (Exception e) {
			System.out.println("�ǸŽ���");
			try {
				conn.rollback(); // �߰��� ������ ����� �ѹ�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		dbClose();
	}

	@Override
	public long totalPrice() {
		dbConn();
		long totalPrice = -1;
		try {
			// ������ ������ �����
			String str="select sum(price*sales_quantity)" + "from fruit join" + 
					"	 (select fruit_fruit_code, sales_date, sales_quantity from fruit_has_sales" + 
					"		   join sales " + 
					" on fruit_has_sales.sales_sales_code=sales.sales_code) t1" + 
					" on fruit.fruit_code=t1.fruit_fruit_code";
			System.out.println(str);
			pstmt = conn.prepareStatement(str);

			result = pstmt.executeQuery();// ������ �����ؼ� ����� ��������
			result.next();// ����� ù��° ���� ����Ű��
			totalPrice = result.getLong(1); // ù��° �Ӽ����� longŸ������ ��ȭ�ؼ� �о�´�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return totalPrice;
	}

	@Override
	public List<SalesVO> listSales() {
		List<SalesVO> list = new ArrayList<>();
		dbConn();
		try {
//			String str="select fruit_name, fruit_code, sales_date, sales_quantity, price*sales_quantity " + "from fruit " + "     join" + 
//					"	 (select fruit_fruit_code, sales_date, sales_quantity" + "	  from fruit_has_sales" + 
//					"		   join " + "sales" + 
//					"           on fruit_has_sales.sales_sales_code=sales.sales_code" + "      ) t1" + 
//					"       on fruit.fruit_code=t1.fruit_fruit_code";
//			System.out.println(str);
			pstmt = conn.prepareStatement("select fruit_name, fruit_code, sales_date, sales_quantity, price*sales_quantity " + "from fruit " + "     join"
					+ "	 (select fruit_fruit_code, sales_date, sales_quantity" + "	  from fruit_has_sales"
					+ "		   join " + "sales "
					+ "           on fruit_has_sales.sales_sales_code=sales.sales_code" + "      ) t1"
					+ "      on fruit.fruit_code=t1.fruit_fruit_code  ");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// next() �������� ����Ŵ, ������ �����డ��Ű�� �����̸� true ������ false
				SalesVO vo = new SalesVO();// �ۿ� �ۼ��ϸ� �ȵȴ�. ���� ��� ���̱� ������
				vo.setFruit_code(rs.getInt("fruit_code"));
				vo.setFruit_name(rs.getString("fruit_name"));
				vo.setSales_date(rs.getDate("sales_date"));			
				vo.setSales_quantity(rs.getInt("sales_quantity"));
				vo.setTotal(rs.getInt("price*sales_quantity"));
				list.add(vo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}


}
