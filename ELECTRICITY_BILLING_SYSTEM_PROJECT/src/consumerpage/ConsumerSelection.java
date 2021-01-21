package consumerpage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import paymentchoice.PaymentPage;
import project.BillDetails;
import project.Cruding;
import project.HomePage;
import project.ShowBill;
import project.BillDetails;

@SuppressWarnings("unused")
public class ConsumerSelection extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JButton option[]=new JButton[2];
	private JLabel panel;
	private JButton logout;

	private String id;
	
	public boolean check(String user, String password) throws HeadlessException, SQLException
	{
		id=user;
		Cruding c=new Cruding();
		if(c.validlogin(user).equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void selection(String id)
	{
		this.id=id;
		
		option[0]=new JButton("Show details of a your Bill");
		option[1]=new JButton("Pay your Bill");
		
		logout=new JButton("Logout");
		
		panel=new JLabel(new ImageIcon("Image/selection2.jpg"));
		panel.setLayout(null);
			
		option[0].setBounds	(30,10,220,20);
		option[1].setBounds	(30,50,220,20);
		logout.setBounds	(170,90,80,30);
		
		option[0].setBackground(Color.WHITE);
		option[1].setBackground(Color.WHITE);
		
		panel.add(option[0]);
		panel.add(option[1]);
		panel.add(logout);
		
		add(panel);
		
		option[0].setContentAreaFilled(false);
		option[1].setContentAreaFilled(false);
				
		setSize(280,170);
		setVisible(true);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Welcome");
		logout.requestFocusInWindow();

		
		option[0].addActionListener(this);
		option[1].addActionListener(this);
		logout.addActionListener(this);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==option[0])
		{
			Cruding c=new Cruding();
			try 
			{
				if(c.checkpaid(id))
				{
					JOptionPane.showMessageDialog(null, "Bill Already Paid", "Paid", ICONIFIED);
				}
				else
				{try
				{
					ResultSet rs=c.fetchBillDetails(id);
					while(rs.next())
					{
						String fname=rs.getString("fname");
						String lname=rs.getString("lname");
						String address=rs.getString("address");
						int unit_consumed=Integer.parseInt(rs.getString("unit_consumed"));
						int rebate=Integer.parseInt(rs.getString("rebate"));
						String readdate=rs.getString("readdate");
						String duedate=rs.getString("due_date");
						int bill_no=Integer.parseInt(rs.getString("bill_no"));
						String name=fname+" "+lname;
						
						
					
					BillDetails bd=new BillDetails();
					bd.billdetails(id,name,address,unit_consumed,rebate,readdate,duedate,bill_no);
					dispose();
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				}
			}
			catch (SQLException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", DO_NOTHING_ON_CLOSE);
			}
		}
		
		if(e.getSource()==option[1])
		{
			try 
			{
				Cruding c=new Cruding();
				c.showid(id);
				if(c.checkpaid(id))
				{
					JOptionPane.showMessageDialog(null, "Bill Already Paid", "Paid", ICONIFIED);
				}
				else
				{
					if(((c.unit*6)-c.rebate)>0)
					{
						new PaymentPage(id);
						dispose();
					}
					else if(((c.unit*6)-c.rebate)<0)
					{
						JOptionPane.showMessageDialog(null, "Bill Paid by Previous Payment", "Paid", ICONIFIED);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Bill not Generated", "No Payment", ICONIFIED);
					}
				}
				
			}
			catch (SQLException e1) 
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", DO_NOTHING_ON_CLOSE);
			}
		}
	
		if(e.getSource()==logout)
		{
			new HomePage();
			dispose();
		}
	}

	
}
