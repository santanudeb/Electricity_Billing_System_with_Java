package adminchoice;

import java.awt.Dimension;
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

public class DeleteID extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JLabel enter;
	private JTextField txt;
	private JButton submit,cancel;
	private JLabel panel;
	
	public DeleteID()
	{
		enter=new JLabel("Enter Consumer ID");
		txt=new JTextField(11);
		
		submit=new JButton("Submit");
		cancel=new JButton("Cancel");
		
		panel=new JLabel(new ImageIcon("Image/delete.jpg"));
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
		String id=txt.getText();
		if(e.getSource()==txt||e.getSource()==submit)
		{
			if(txt.getText()!=null)
			{
				int n=JOptionPane.showConfirmDialog(null, "Are you sure to Delete Consumer ID : "+id+"?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(n==0)
				{
					Cruding c=new Cruding();
					try 
					{
						boolean b=c.deleteid(id);
						if(b)
						{
							JOptionPane.showMessageDialog(null, "Deleted", "Done", JOptionPane.INFORMATION_MESSAGE);
							AdminSelection as=new AdminSelection();
							as.selection();
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Enter a valid Consumer ID", "Invalid Consumer ID", JOptionPane.OK_OPTION);
						}
					}
					catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insert an Consumer's Id", "Field Blank", JOptionPane.OK_OPTION);
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
