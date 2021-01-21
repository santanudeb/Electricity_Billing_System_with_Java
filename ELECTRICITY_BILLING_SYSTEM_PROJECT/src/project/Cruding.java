package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cruding
{
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String user="TIRTHANKAR";
	String password="12345";
	public int billno;
	public String readdate;
	public String duedate;
	public int unit;
	public int rebate;
	public String fname;
	public String lname;
	public String address;
	public String cotype;
	
	void DriverMan() throws SQLException
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	}

	public boolean createid(String id) throws SQLException
	{	
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		String sql="Select max(bill_no) from ConsumerBill";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		rs.next();
		billno=rs.getInt("max(bill_no)");
		billno++;
		
		sql="Insert into ConsumerBill (co_id,unit_consumed,rebate,bill_no) values(?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setInt(2, 0);
		ps.setInt(3, 0);
		ps.setInt(4, billno);
		
		int xx=ps.executeUpdate();
		con.close();
		return xx>0;
		
	}
	
	public void showid(String id) throws SQLException 
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Select unit_consumed,rebate,readdate,due_date,bill_no from ConsumerBill where Co_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		unit=rs.getInt("unit_consumed");
		rebate=rs.getInt("rebate");
		readdate=rs.getString("readdate");
		duedate=rs.getString("due_date");
		billno=rs.getInt("bill_no");
		con.close();
	}
	
	public boolean updateid(int newunit, int newrebate, String newreaddate, String newduedate, String id) throws SQLException
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		String sql="Update ConsumerBill set unit_consumed=?,rebate=?,readdate=?,due_date=? where Co_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, newunit);
		ps.setInt(2, newrebate);
		ps.setString(3, newreaddate);
		ps.setString(4, newduedate);
		ps.setString(5, id);
		
		int xx=ps.executeUpdate();
		if(xx>0&&validcheck(id))
		{
			sql="Update ConsumerLogin set paid=? where user_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, "false");
			ps.setString(2, id);
			
			xx=ps.executeUpdate();
		}
		con.close();
		return xx>0;
	}
	
	public boolean deleteid(String id) throws SQLException
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Delete from ConsumerBill where Co_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		
		int xx=ps.executeUpdate();
		return xx>0;
	}
	
	public boolean createconsumer(String id,String pass,String fname,String lname,String bdate,String address,String email) throws SQLException
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Insert into ConsumerLogin (user_id,password,paid) values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pass);
		ps.setString(3, "false");
		
		int xx=ps.executeUpdate();
		
		if (xx>0)
		{
			sql="Insert into ConsumerInfo (Cust_id,fname,lname,bdate,address,email) values(?,?,?,?,?,?)";
			PreparedStatement ps1=con.prepareStatement(sql);
			ps1.setString(1, id);
			ps1.setString(2, fname);
			ps1.setString(3, lname);
			ps1.setString(4, bdate);
			ps1.setString(5, address);
			ps1.setString(6, email);
			
			xx=ps1.executeUpdate();
			con.close();
			return xx>0;
		}
		else
		{
			con.close();
			return (xx>0);
		}
	}

	public void showinfo(String id) throws SQLException 
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Select unit_consumed,rebate,readdate,due_date,bill_no,fname,lname,address from ConsumerBill,ConsumerInfo where Cust_id=? and Cust_id=Co_id";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		fname=rs.getString("fname");
		lname=rs.getString("lname");
		address=rs.getString("address");
		unit=rs.getInt("unit_consumed");
		rebate=rs.getInt("rebate");
		readdate=rs.getString("readdate");
		duedate=rs.getString("due_date");
		billno=rs.getInt("bill_no");
		cotype="Domestic";
	}

	public boolean checkpaid(String id) throws SQLException 
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Select paid from CONSUMERLOGIN where USER_ID=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		
		if(rs.getString("paid").equals("false"))
		{
			con.close();
			return false;
		}
		else
		{
			con.close();
			return true;
		}
	}

	public boolean paydone(String id) throws SQLException
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Update ConsumerLogin set paid=? where user_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, "true");
		ps.setString(2, id);
		int xx=ps.executeUpdate();
		
		if(xx>0)
		{
			sql="Update ConsumerBill set unit_consumed=?,rebate=?,readdate=?,due_date=? where Co_id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setInt(2, 0);
			ps.setString(3, "");
			ps.setString(4, "");
			ps.setString(5, id);
			xx=ps.executeUpdate();
		}
		con.close();
		return xx>0;
	}
	
	public boolean check(String id) throws SQLException
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Select * from ConsumerBill where co_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		
		int xx=ps.executeUpdate();
		return xx>0;
	}

	public boolean validcheck(String id) throws SQLException
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Select * from CONSUMERBILL where co_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		
		int xx=ps.executeUpdate();
		return xx>0;
	}
	
	public String validlogin(String id) throws SQLException
	{
		DriverMan();
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="Select password from ConsumerLogin where user_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		
		ResultSet rs=ps.executeQuery();
		rs.next();
		
		return rs.getString("password");
	}
	
	public ResultSet fetchBillDetails(String id) throws SQLException
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="TIRTHANKAR";
		String password="12345";
		
		Connection con=DriverManager.getConnection(url,user,password);
		
		String sql="select fname,lname,address,unit_consumed,rebate,readdate,due_date,bill_no from consumerinfo ci, consumerbill cb where ci.cust_id=? and cb.co_id=?";
	
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, id);
		
		ResultSet rs=ps.executeQuery();
		
		
		return rs;
		
	}
}