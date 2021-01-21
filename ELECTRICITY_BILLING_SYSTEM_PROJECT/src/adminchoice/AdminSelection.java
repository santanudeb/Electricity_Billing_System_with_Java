package adminchoice;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import adminchoice.CreateID;
import adminchoice.DeleteID;
import adminchoice.ShowID;
import adminchoice.UpdateID;
import project.BillDetails;
import project.HomePage;

@SuppressWarnings("unused")
public class AdminSelection extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JButton option[]=new JButton[4];
	private JLabel panel;
	private JButton logout;

	private String id;
	
	public boolean check(String user, String password)
	{
		id=user;
		if(user.equals("TIRTHANKAR")&&password.equals("12345"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void selection()
	{		
		option[0]=new JButton("Create a new Consumer ID");
		option[1]=new JButton("Update Details of a Consumer Bill");
		option[2]=new JButton("Delete a Consumer ID");
		option[3]=new JButton("Show details of a Consumer Bill");
		
		logout=new JButton("Logout");
		
		panel=new JLabel(new ImageIcon("Image/selection.png"));
		panel.setLayout(null);
		
		option[0].setBounds	(20,10,250,20);
		option[1].setBounds	(20,50,250,20);
		option[2].setBounds	(20,90,250,20);
		option[3].setBounds	(20,130,250,20);
		logout.setBounds	(180, 170,80,30);
		
		panel.add(option[0]);
		panel.add(option[1]);
		panel.add(option[2]);
		panel.add(option[3]);
		
		option[0].setContentAreaFilled(false);
		option[1].setContentAreaFilled(false);
		option[2].setContentAreaFilled(false);
		option[3].setContentAreaFilled(false);
		
		panel.add(logout);
		
		add(panel);
		
		setSize(280,240);
		setVisible(true);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Admin Page");
		
		logout.requestFocusInWindow();

		
		option[0].addActionListener(this);
		option[1].addActionListener(this);
		option[2].addActionListener(this);
		option[3].addActionListener(this);
		logout.addActionListener(this);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==option[0])
		{
			new CreateID();
			dispose();
		}
		
		if(e.getSource()==option[1])
		{
			new UpdateID();
			dispose();
		}
		
		if(e.getSource()==option[2])
		{
			new DeleteID();
			dispose();
		}
		
		if(e.getSource()==option[3])
		{
			new ShowID();
			dispose();
		}
		
		if(e.getSource()==logout)
		{
			new HomePage();
			dispose();
		}
	}

}
