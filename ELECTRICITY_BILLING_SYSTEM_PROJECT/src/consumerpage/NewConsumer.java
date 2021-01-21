package consumerpage;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import project.Cruding;
import project.HomePage;

public class NewConsumer extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JLabel enter;
	private JTextField txt;
	private JLabel panel;
	private JButton submit,cancel;
	
	public NewConsumer()
	{
		enter=new JLabel("Enter Your Consumer ID");
		txt=new JTextField(11);
		
		submit=new JButton("Submit");
		cancel=new JButton("Cancel");
		
		panel=new JLabel(new ImageIcon("Image/signup.jpg"));
		panel.setLayout(null);
		
		enter.setBounds(60,20,150,20);
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
		if(e.getSource()==txt||e.getSource()==submit)
		{
			if(!(txt.getText().equals("")))
			{
				String id=txt.getText();
				Cruding c=new Cruding();
				try 
				{
					if(c.check(id))
					{
						if(c.validcheck(id))
						{
							new SignupPage(id);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Account already created", "Signup Invalid", DO_NOTHING_ON_CLOSE);
							
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Enter a valid Consumer ID", "Invalid Consumer ID", DO_NOTHING_ON_CLOSE);
					}
				} 
				catch (HeadlessException | SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Consumer ID", DO_NOTHING_ON_CLOSE);
				}
			}
			
		}
		
		if(e.getSource()==cancel)
		{
			new HomePage();
			dispose();
		}
	}
}
