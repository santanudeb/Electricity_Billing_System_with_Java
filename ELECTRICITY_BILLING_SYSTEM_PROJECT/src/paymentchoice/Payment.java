package paymentchoice;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import consumerpage.ConsumerSelection;
import project.Cruding;

public class Payment extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JLabel cardno,expdate,cvv,main;
	private JTextField txtcard,txtcvv;
	private JButton pay,cancel;
	@SuppressWarnings("rawtypes")
	private JComboBox month,year;
	
	private String mm[]=new String[13];
	private String yyyy[]=new String[10];
	
	private String monthname[]={null,"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private String id;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Payment(String id, String string)
	{
		this.id=id;
		mm[0]="--";
		yyyy[0]="--";
		
		mm[0]="--";
		yyyy[0]="--";
		
		int j=2015;
		for(int i=1;i<10;i++)
		{
			yyyy[i]=String.valueOf(j);
			j++;
		}
		
		for(int i=1;i<13;i++)
		{
			mm[i]=monthname[i];
		}
		
		cardno=new JLabel("Enter your Card No. :");
		expdate=new JLabel("Expiry Date : ");
		cvv=new JLabel("CVV :");
		
		txtcard=new JTextField(19);
		txtcvv=new JTextField(4);
		
		month=new JComboBox(mm);
		year=new JComboBox(yyyy);
		
		pay=new JButton("Pay Now");
		cancel=new JButton("Cancel");

		main=new JLabel(new ImageIcon("Image/paycard.png"));
		main.setLayout(null);
		
		main.add(cardno);
		main.add(txtcard);
		main.add(expdate);
		main.add(month);
		main.add(year);
		main.add(cvv);
		main.add(txtcvv);
		main.add(pay);
		main.add(cancel);

		add(main);
		
		cardno.setBounds(20,10,150,20);
		txtcard.setBounds(20,30,160,20);
		expdate.setBounds(20,60,150,20);
		month.setBounds(20,80,50,20);
		year.setBounds(70,80,60,20);
		cvv.setBounds(140,60,150,20);
		txtcvv.setBounds(140,80,60,20);
		pay.setBounds(20,120,90,30);
		cancel.setBounds(120,120,80,30);
		
		setSize(220,200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Sign Up");
		setResizable(false);
		setVisible(true);
		setTitle(string);
		
		pay.addActionListener(this);
		cancel.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==pay)
		{
			Cruding c=new Cruding();
			try
			{
				long num1=Long.parseLong(txtcard.getText());
				int num2=Integer.parseInt(txtcvv.getText());
				if(String.valueOf(num2).length()!=4||String.valueOf(num1).length()!=19)
				{
					garbage("");
				}
				garbage((String)month.getSelectedItem());
				garbage((String)year.getSelectedItem());
				
				c.showid(id);
				int amt=(c.unit*6)-c.rebate;
				int n=JOptionPane.showConfirmDialog(null,"Are you sure you want to pay Rs. "+amt,
						"Confirm Payment", JOptionPane.YES_NO_OPTION);

				if(n==0)
				{
					if(c.paydone(id))
					{
						JOptionPane.showMessageDialog(null, "Transaction Complete", "Successfull", ICONIFIED);
						ConsumerSelection cs=new ConsumerSelection();
						cs.selection(id);
						dispose();
					}
				}
			}
			catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", DO_NOTHING_ON_CLOSE);
			}
		}
		
		if(e.getSource()==cancel)
		{
			ConsumerSelection cs=new ConsumerSelection();
			cs.selection(id);
			dispose();
		}
	}
	
	class NullDataException extends Exception
	{
		private static final long serialVersionUID = 1L;

		public NullDataException(String s) 
		{
			super(s);
		}
	}
	
	void garbage(String x) throws NullDataException
	{
		if(x.equals("--"))
		{
			throw new NullDataException("Missing or Incorrect Entries!!!"
					+"\n\nPlease Enter Again");
		}
		if(x.equals(""))
		{
			throw new NullDataException("Card No. has to of 19 digits\nAnd CVV No. has to of 4 digits"
					+"\n\nPlease Enter Again");
		}
	}
}