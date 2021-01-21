package consumerpage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.Cruding;
import project.HomePage;

public class SignupPage extends JFrame implements ActionListener,ItemListener
{
	private static final long serialVersionUID = 1L;
	
	private JLabel coid,entdetails,entfname,entlname,entage,entemail,entpass,entrepass,entaddr;
	private JTextField nonid,fname,lname,email;
	private JTextArea addr;
	private JPasswordField pass,repass;
	@SuppressWarnings("rawtypes")
	private JComboBox day,month,year;
	private JButton create,cancel;
	private JLabel panel;
	
	private String monthname[]={null,"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private String dd[]=new String[32];
	private String mm[]=new String[13];
	private String yyyy[]=new String[101];
	private String dy=null,mn=null,yr=null;
	private String id;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SignupPage(String id)
	{
		this.id=id;
		
		dd[0]="--";
		mm[0]="--";
		yyyy[0]="--";
		
		int j=2015;
		for(int i=1;i<101;i++)
		{
			yyyy[i]=String.valueOf(j);
			j--;
		}
		
		for(int i=1;i<13;i++)
		{
			mm[i]=monthname[i];
		}
		
		for(int i=1;i<32;i++)
		{
			dd[i]=String.valueOf(i);
		}
		
		coid=new JLabel("Consumer ID: ");
		
		nonid=new JTextField(11);
		nonid.setText(id);
		nonid.setEditable(false);
		
		entdetails=new JLabel("Enter your details:");
		entdetails.setForeground(Color.BLACK);
		entdetails.setFont(entdetails.getFont().deriveFont(16.0f));
		entfname=new JLabel("First Name");
		fname=new JTextField(20);
		entlname=new JLabel("Last Name");
		lname=new JTextField(20);
		entage=new JLabel("Birth Date");
		year=new JComboBox(yyyy);
		month=new JComboBox(mm);
		day=new JComboBox(dd);
		entaddr=new JLabel("Address");
		
		addr=new JTextArea();
		addr.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		entemail=new JLabel("E-mail");
		email=new JTextField(30);
		entpass=new JLabel("New Password");
		pass=new JPasswordField(20);
		entrepass=new JLabel("Re-Type Password");
		repass=new JPasswordField(20);
		create=new JButton("Submit");
		cancel=new JButton("Cancel");
		
		panel=new JLabel(new ImageIcon("Image/signuppage.jpg"));
		panel.setLayout(null);
		panel.add(coid);
		panel.add(nonid);
		panel.add(entdetails);
		panel.add(entfname);
		panel.add(fname);
		panel.add(entlname);
		panel.add(lname);
		panel.add(entage);
		panel.add(year);
		panel.add(month);
		panel.add(day);
		panel.add(entaddr);
		panel.add(addr);
		panel.add(entemail);
		panel.add(email);
		panel.add(entpass);
		panel.add(pass);
		panel.add(entrepass);
		panel.add(repass);
		panel.add(create);
		panel.add(cancel);
		
		entdetails.setBounds(30,10,160,20);
		coid.setBounds		(50,40,160,20);
		nonid.setBounds		(50,60,160,20);
		entfname.setBounds	(50,90,160,20);
		fname.setBounds		(50,110,160,20);
		entlname.setBounds	(50,140,160,20);
		lname.setBounds		(50,160,160,20);
		entage.setBounds	(50,190,160,20);
		day.setBounds		(50,210,50,20);
		month.setBounds		(100,210,50,20);
		year.setBounds		(150,210,60,20);
		entaddr.setBounds	(50,240,160,20);
		addr.setBounds		(50,260,160,40);
		entemail.setBounds	(50,310,160,20);
		email.setBounds		(50,330,160,20);
		entpass.setBounds	(50,360,160,20);
		pass.setBounds		(50,380,160,20);
		entrepass.setBounds	(50,410,160,20);
		repass.setBounds	(50,430,160,20);
		create.setBounds	(30,470,75,30);
		cancel.setBounds	(155,470,75,30);
		
		add(panel);
		setSize(260,550);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Sign Up");
		setResizable(false);
		setVisible(true);
		
		create.addActionListener(this);
		cancel.addActionListener(this);
		
		year.addItemListener(this);
		month.addItemListener(this);
		day.addItemListener(this);
		
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource()==year)
		{
			yr=(String) year.getSelectedItem();
		}
		
		if(e.getSource()==month)
		{
			mn=(String) month.getSelectedItem();
		}
		
		if(e.getSource()==day)
		{
			dy=(String) day.getSelectedItem();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
			if(e.getSource()==create)
			{
				try
				{
					garbage(fname.getText());
					garbage(lname.getText());
					garbage(yr);
					garbage(mn);
					garbage(dy);
					garbage(addr.getText());
					
					int flag=0;
					for(int i=0;i<email.getText().length()-1;i++)
					{
						if(email.getText().substring(i, i+1).equals("@"))
						{
							flag=1;
							break;
						}
					}
					if(flag==0)
					{
						garbage("@");
					}
					
					if(pass.getText().length()<6)
					{
						garbage("pass");
					}
					
					confirmpass(pass.getText(),repass.getText());
					
					String bdate = dy+"-"+mn+"-"+yr;
					int n=JOptionPane.showConfirmDialog(null, coid.getText()+nonid.getText()+"\n"+
							entfname.getText()+": "+fname.getText()+"\n"+
							entlname.getText()+": "+lname.getText()+"\n"+
							entage.getText()+": "+bdate+"\n"+
							entaddr.getText()+": "+addr.getText()+"\n"+
							entemail.getText()+": "+email.getText()+"\n\n"+
							"Are you sure to create this Account?",
							"Confirm Submit", JOptionPane.YES_NO_OPTION);

					if(n==0)
					{
						Cruding c=new Cruding();
						boolean b=c.createconsumer(id, pass.getText(), fname.getText(), lname.getText(), bdate, addr.getText(), email.getText());
						if(b)
						{
							JOptionPane.showMessageDialog(null, "New Account Created", "Done", JOptionPane.INFORMATION_MESSAGE);
							new HomePage();
							dispose();
						}
						
					}
				}
				catch(NullDataException | MismatchException | SQLException er)
				{
					JOptionPane.showMessageDialog(null, er.getMessage(), "Error", DO_NOTHING_ON_CLOSE);
					pass.setText(null);
					repass.setText(null);
				}
			}
			
			if(e.getSource()==cancel)
			{
				new HomePage();
				dispose();
			}
	}
	
	void garbage(String x) throws NullDataException
	{
		if(x==null||x.equals("")||x.equals("--"))
		{
			throw new NullDataException("Missing or Incorrect Entries!!!"
					+"\n\nPlease Enter Again");
		}
		
		if(x.equals("pass"))
		{
			throw new NullDataException("Password should be atleast six digits!!!"
					+"\n\nPlease Enter Again");
		}
		
		if(x.equals("@"))
		{
			throw new NullDataException("Valid E-mail address not entered!!!"
					+"\n\nPlease Enter Again");
		}

		
	}
	
	void confirmpass(String x,String y) throws MismatchException
	{
		if (!(x.equals(y)))
		{
			throw new MismatchException("Password do not match"
					+"\n\nPlease Enter Password Again");
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
	
	class MismatchException extends Exception
	{
		private static final long serialVersionUID = 1L;

		public MismatchException(String s) 
		{
			super(s);
		}
	}
	

}
