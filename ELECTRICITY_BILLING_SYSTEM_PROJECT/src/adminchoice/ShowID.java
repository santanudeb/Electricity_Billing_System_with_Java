package adminchoice;

import java.awt.Dimension;
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
import javax.swing.JTextField;


import project.BillDetails;
import project.Cruding;


public class ShowID extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JLabel enter;
	private JTextField txt;
	private JLabel panel;

	private JButton submit;
	private String id;
	private JButton cancel;
	
	
	public ShowID()
	{
		
		enter=new JLabel("Enter Consumer ID");
		txt=new JTextField(11);
		
		submit=new JButton("Submit");
		cancel=new JButton("Cancel");
		
		panel=new JLabel(new ImageIcon("Image/show.jpg"));
		panel.setLayout(null);
		
		enter.setBounds(80,20,150,20);
		txt.setBounds(60,50,150,20);
		submit.setBounds(30,90,80,30);
		cancel.setBounds(170,90,80,30);
		
		panel.add(enter);
		panel.add(txt);
		panel.add(submit);
		panel.add(cancel);
		
		add(panel);
		
		setSize(280,180);
		setVisible(true);
		txt.requestFocusInWindow();
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Confirm Valid Consumer ID");
		
		txt.addActionListener(this);
		submit.addActionListener(this);
		cancel.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		id=txt.getText();
		if(e.getSource()==submit)
		{
			Cruding c=new Cruding();
			try
			{
				if(c.validcheck(id))
				{
					if(c.checkpaid(id))
					{
						JOptionPane.showMessageDialog(null, "Bill Paid by Consumer", "Paid", ICONIFIED);
					}
					else
					{
						/*new ShowBill(id,"A");
						dispose();*/
						try
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
							
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Account not created or\nConsumer did not Sign Up till now", "No data to show", DO_NOTHING_ON_CLOSE);
				}
				
			}
			catch (SQLException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid ID", JOptionPane.OK_OPTION);
			}
		}
		if(e.getSource()==cancel)
		{
			AdminSelection as=new AdminSelection();
			as.selection();
			dispose();
		}
	}	
	
}
