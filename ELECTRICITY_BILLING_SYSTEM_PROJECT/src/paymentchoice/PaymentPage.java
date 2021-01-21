package paymentchoice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import consumerpage.ConsumerSelection;
import project.Cruding;
import project.HomePage;
import project.ShowBill;
import project.BillDetails;

@SuppressWarnings("unused")
public class PaymentPage extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JButton option[]=new JButton[2];
	private JLabel panel,bank;
	private JButton logout,cont;
	private String bankname[]={"Credit Card","Debit Card"};
	@SuppressWarnings("rawtypes")
	private JComboBox bankchoice;
	private String id;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PaymentPage(String id)
	{	
		this.id=id;
		
		option[0]=new JButton("Show details of a your Bill");
		option[1]=new JButton("Pay your Bill");
		
		bank=new JLabel("Mode of Payment");
		bankchoice=new JComboBox(bankname);
		
		logout=new JButton("Logout");
		cont=new JButton("Continue");

		panel=new JLabel(new ImageIcon("Image/paymentpage.jpg"));
		panel.setLayout(null);
	
		option[0].setBounds	(30,10,220,20);
		option[1].setBounds	(30,50,220,20);
		bank.setBounds		(90,80,140,20);
		bankchoice.setBounds(70,100,140,20);
		cont.setBounds		(30,140,100,30);
		logout.setBounds	(160,140,80,30);
		
		option[0].setBackground(Color.WHITE);
		option[1].setBackground(Color.WHITE);
		
		option[0].setContentAreaFilled(false);
		option[1].setContentAreaFilled(false);
		
		panel.add(option[0]);
		panel.add(option[1]);
		panel.add(bank);
		panel.add(bankchoice);
		panel.add(cont);
		panel.add(logout);
		
		add(panel);
		
		setSize(280,220);
		setVisible(true);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Welcome");
		
		cont.requestFocusInWindow();
		
		option[0].addActionListener(this);
		logout.addActionListener(this);
		cont.addActionListener(this);
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
					ConsumerSelection cs=new ConsumerSelection();
					cs.selection(id);
					dispose();
				}
				else
				{
					new BillDetails();
					dispose();
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
		
		if(e.getSource()==cont)
		{					
				
					switch(bankchoice.getSelectedIndex())
					{
					case 0:
						new Payment(id,"Credit Payment");
						dispose();
						break;
					case 1:
						new Payment(id,"Debit Payment");
						dispose();
						break;
					}
					dispose();
			
		}
	}
	
}
