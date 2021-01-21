package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import adminchoice.AdminSelection;
import consumerpage.ConsumerSelection;
import consumerpage.NewConsumer;

public class HomePage extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel main;
	private JTextField adtxt,cotxt;
	private JPasswordField adp,cop;
	private JButton adb,cob,signup;
	private JLabel aduser,adpass,coid,copass,or,p1,p2;
	
	
	private String user,password;
	public HomePage()
	{		
		aduser=new JLabel("Username");
		adtxt=new JTextField(20);
		adpass=new JLabel("Password");
		adp=new JPasswordField();
		adb=new JButton("Login");
		
		coid=new JLabel("Consumer ID");
		cotxt=new JTextField(20);
		copass=new JLabel("Password");
		cop=new JPasswordField();
		cob=new JButton("Login");
		or=new JLabel("OR");
		signup=new JButton("Signup");
		
		
		p1=new JLabel(new ImageIcon("image/admin.png"));
		p1.add(aduser);
		p1.add(adtxt);
		p1.add(adpass);
		p1.add(adp);
		p1.add(adb);
		
		aduser.setBounds(70,50,150,20);
		adtxt.setBounds(70,70,150,20);
		adpass.setBounds(70,100,150,20);
		adp.setBounds(70,120,150,20);
		adb.setBounds(100,160,75,30);
		
		p2=new JLabel(new ImageIcon("Image/customer.png"));
		p2.add(coid);
		p2.add(cotxt);
		p2.add(copass);
		p2.add(cop);
		p2.add(cob);
		p2.add(or);
		p2.add(signup);
		
		coid.setBounds(70,50,150,20);
		cotxt.setBounds(70,70,150,20);
		copass.setBounds(70,100,150,20);
		cop.setBounds(70,120,150,20);
		cob.setBounds(70,160,65,30);
		or.setBounds(140,165,20,20);
		signup.setBounds(165,160,75,30);
		
		main=new JPanel();
		main.setLayout(new GridLayout(1,2,50,50));
		main.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		main.add(p1);
		p1.setBorder(BorderFactory.createTitledBorder("For Admin Only"));
		main.add(p2);
		p2.setBorder(BorderFactory.createTitledBorder("For Consumer Only"));
		add(main);
		
		setSize(700,300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("HomePage");
		setResizable(false);
		setVisible(true);
		
		adb.addActionListener(this);
		cob.addActionListener(this);
		signup.addActionListener(this);
		adp.addActionListener(this);
		cop.addActionListener(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==adb||e.getSource()==adp)
		{
			user=adtxt.getText();
			password=adp.getText();
			if(!(user.equals("")))
			{
				AdminSelection as=new AdminSelection();
				if(as.check(user,password))
				{
					aduser.setForeground(Color.BLACK);
					adpass.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(null, "Welcome Admin", "Login Successful", ICONIFIED);
					as.selection();
					dispose();
				}
				else
				{
					adpass.setForeground(Color.RED);
					aduser.setForeground(Color.BLACK);
				}
			}
			else
			{
				adpass.setForeground(Color.RED);
				aduser.setForeground(Color.RED);
			}
		}
		
		if(e.getSource()==cob||e.getSource()==cop)
		{
			user=cotxt.getText();
			password=cop.getText();
			if(!(user.equals("")))
			{
				try
				{
					ConsumerSelection c=new ConsumerSelection();
					if(c.check(user, password))
					{
						coid.setForeground(Color.BLACK);
						copass.setForeground(Color.BLACK);
						JOptionPane.showMessageDialog(null, "Welcome Consumer", "Login Successful", ICONIFIED);
						c.selection(user);
						dispose();
					}
					else
					{
						coid.setForeground(Color.BLACK);
						copass.setForeground(Color.RED);
					}
				}
				catch (HeadlessException | SQLException e1)
				{
					coid.setForeground(Color.BLACK);
					copass.setForeground(Color.RED);
				}
			}
			else
			{
				coid.setForeground(Color.RED);
				copass.setForeground(Color.RED);
			}
			
		}
		
		if(e.getSource()==signup)
		{
			new NewConsumer();
			dispose();
		}
	}
	
	public static void main(String[] args) 
	{
		new HomePage();
	}
}
