package adminchoice;

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
import javax.swing.JTextField;

import project.Cruding;

public class UpdatePage extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private int newunit,newrebate;
	private String newduedate,newreaddate,id;
	private String monthname[]={null,"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	private int oldunit,oldrebate;
	private String oldduedate,oldreaddate;
	private JLabel details,entunit,entrebate,entduedate,entreaddate;
	private JTextField txtunit,txtrebate;
	@SuppressWarnings("rawtypes")
	private JComboBox rday,rmonth,ryear,dday,dmonth,dyear;
	private JButton rreset,dreset,update,cancel;
	private JLabel panel;
	
	private String dd[]=new String[32];
	private String mm[]=new String[13];
	private String yyyy[]=new String[10];
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UpdatePage(String id)
	{	
		this.id=id;
		try 
		{
			Cruding c=new Cruding();
			c.showid(id);
			oldunit=c.unit;
			oldrebate=c.rebate;
			oldreaddate=c.readdate;
			oldduedate=c.duedate;
		} 
		catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Retrieve Error", JOptionPane.ERROR_MESSAGE);
		}
		
		dd[0]="--";
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
		
		for(int i=1;i<32;i++)
		{
			dd[i]=String.valueOf(i);
		}
		
		details=new JLabel("Update Bill Details:");
		details.setFont(details.getFont().deriveFont(14.0f));
		
		entunit=new JLabel("Unit Consumed");
		txtunit=new JTextField(11);
		txtunit.setText(String.valueOf(oldunit));
		
		entrebate=new JLabel("Consumer's Rebate");
		txtrebate=new JTextField(5);
		txtrebate.setText(String.valueOf(oldrebate));
		
		entduedate=new JLabel("Due-Date");
		ryear=new JComboBox(yyyy);
		rmonth=new JComboBox(mm);
		rday=new JComboBox(dd);
		rreset=new JButton("Reset");
								
		entreaddate=new JLabel("Reading Date");
		dyear=new JComboBox(yyyy);
		dmonth=new JComboBox(mm);
		dday=new JComboBox(dd);
		dreset=new JButton("Reset");
		
		update=new JButton("Update");
		cancel=new JButton("Cancel");
		
		datefix();
		
		panel=new JLabel(new ImageIcon("Image/UpdatePage.jpg"));
		panel.add(details);
		panel.add(entunit);
		panel.add(txtunit);
		panel.add(entrebate);
		panel.add(txtrebate);
		panel.add(entreaddate);
		panel.add(ryear);
		panel.add(rmonth);
		panel.add(rday);
		panel.add(rreset);
		panel.add(entduedate);
		panel.add(dyear);
		panel.add(dmonth);
		panel.add(dday);
		panel.add(dreset);
		panel.add(update);
		panel.add(cancel);
		
		details.setBounds		(30,10,170,20);
		
		entunit.setBounds		(50,40,150,20);
		txtunit.setBounds		(50,60,150,20);
		
		entrebate.setBounds		(50,90,150,20);
		txtrebate.setBounds		(50,110,150,20);
		
		entreaddate.setBounds	(50,140,150,20);
		rday.setBounds			(50,160,50,20);
		rmonth.setBounds		(100,160,50,20);
		ryear.setBounds			(150,160,60,20);
		rreset.setBounds		(140,185,70,20);
		
		entduedate.setBounds	(50,210,150,20);
		dday.setBounds			(50,230,50,20);
		dmonth.setBounds		(100,230,50,20);
		dyear.setBounds			(150,230,60,20);
		dreset.setBounds		(140,255,70,20);
		
		update.setBounds		(30,310,75,30);
		cancel.setBounds		(160,310,75,30);
		
		add(panel);
		setSize(270,400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Sign Up");
		setResizable(false);
		setVisible(true);
		setTitle("ID: "+id);;
		
		rreset.addActionListener(this);
		dreset.addActionListener(this);
		update.addActionListener(this);
		cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==update)
		{
			newunit=Integer.parseInt(txtunit.getText());
			newrebate=Integer.parseInt(txtrebate.getText());
			if(rday.getSelectedIndex()==0&&rmonth.getSelectedIndex()==0&&ryear.getSelectedIndex()==0)
			{
				newreaddate=null;
			}
			else if(rday.getSelectedIndex()==0||rmonth.getSelectedIndex()==0||ryear.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(null, "Read Date NOT updated due to incomplete fields", "Incomplete Date Fields", JOptionPane.OK_OPTION);
				newreaddate=oldreaddate.substring(8, 10)+"-"+monthname[Integer.parseInt(oldduedate.substring(5, 7))]+"-"+oldreaddate.substring(0, 4);
			}
			else
			{
				newreaddate=String.valueOf(rday.getSelectedItem())+"-"+
						monthname[rmonth.getSelectedIndex()]+"-"+
						String.valueOf(ryear.getSelectedItem());
			}
			
			if(dday.getSelectedIndex()==0&&dmonth.getSelectedIndex()==0&&dyear.getSelectedIndex()==0)
			{
				newduedate=null;
			}
			else if(dday.getSelectedIndex()==0||dmonth.getSelectedIndex()==0||dyear.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(null, "Due Date NOT updated due to incomplete fields", "Incomplete Date Fields", JOptionPane.OK_OPTION);
				newduedate=oldduedate.substring(8, 10)+"-"+monthname[Integer.parseInt(oldduedate.substring(5, 7))]+"-"+oldduedate.substring(0, 4);
			}
			else
			{
				newduedate=String.valueOf(dday.getSelectedItem())+"-"+
						monthname[dmonth.getSelectedIndex()]+"-"+
						String.valueOf(dyear.getSelectedItem());
			}
					
			Cruding c=new Cruding();
			try
			{
				if(c.updateid(newunit,newrebate,newreaddate,newduedate,id))
				{
					JOptionPane.showMessageDialog(null, "Updated", "Done", JOptionPane.INFORMATION_MESSAGE);
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
		
		if(e.getSource()==rreset)
		{
			rday.setSelectedIndex(0);
			rmonth.setSelectedIndex(0);
			ryear.setSelectedIndex(0);
		}
		
		if(e.getSource()==dreset)
		{
			dday.setSelectedIndex(0);
			dmonth.setSelectedIndex(0);
			dyear.setSelectedIndex(0);
		}
		
		if(e.getSource()==cancel)
		{
			AdminSelection as=new AdminSelection();
			as.selection();
			dispose();
		}
	}
	public void datefix()
	{
		if(oldreaddate!=null)
		{
			ryear.setSelectedIndex((Integer.parseInt(oldreaddate.substring(0, 4))-2014));
			rmonth.setSelectedIndex(Integer.parseInt(oldreaddate.substring(5, 7)));
			rday.setSelectedIndex(Integer.parseInt(oldreaddate.substring(8, 10)));
		}
		else
		{
			ryear.setSelectedIndex(0);
			rmonth.setSelectedIndex(0);
			rday.setSelectedIndex(0);
		}

		if(oldduedate!=null)
		{
			dyear.setSelectedIndex((Integer.parseInt(oldduedate.substring(0, 4))-2014));
			dmonth.setSelectedIndex(Integer.parseInt(oldduedate.substring(5, 7)));
			dday.setSelectedIndex(Integer.parseInt(oldduedate.substring(8, 10)));
		}
		else
		{
			dyear.setSelectedIndex(0);
			dmonth.setSelectedIndex(0);
			dday.setSelectedIndex(0);
		}
	}
}