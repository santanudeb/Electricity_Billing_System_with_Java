package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import adminchoice.AdminSelection;
import consumerpage.ConsumerSelection;

public class ShowBill extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JLabel coid,readdate,duedate,rebate,cotype,unitcons,grossamt,netamt;
	private JLabel tname,taddress,tcoid,tbillno,treaddate,tduedate,trebate,tcotype,tunitcons,tgrossamt,tnetamt;
	private JLabel main;
	private JButton OK;
	private JLabel pidctrd,pinfo;
	@SuppressWarnings("unused")
	private String monthname[]={null,"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private String rday,rmonth,ryear,dday,dmonth,dyear,go,id;

	public ShowBill(String id,String go)
	{
		this.go=go;
		this.id=id;
		
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) 
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
			UIManager.put(key, new FontUIResource("Sherif", Font.BOLD, 24));
		}
		
		Cruding c=new Cruding();
		try 
		{
			c.showinfo(id);
			
			if(c.readdate==null||c.duedate==null)
			{
				ryear="yyyy";
				rmonth="mm";
				rday="dd";
				dyear="yyyy";
				dmonth="mm";
				dday="dd";
			}
			else
			{
				ryear=c.readdate.substring(0,4);
				//rmonth=monthname[Integer.parseInt(c.readdate.substring(5,7))];
				rday=c.readdate.substring(8,10);
				dyear=c.readdate.substring(0,4);
				//dmonth=monthname[Integer.parseInt(c.readdate.substring(5,7))];
				dday=c.readdate.substring(8,10);
			}
			
			coid=new JLabel("Consumer's ID",SwingConstants.CENTER);
			readdate=new JLabel("Reading Date",SwingConstants.CENTER);
			duedate=new JLabel("Due Date",SwingConstants.CENTER);
			rebate=new JLabel("Rebate",SwingConstants.CENTER);
			cotype=new JLabel("Consumer Type",SwingConstants.CENTER);
			unitcons=new JLabel("Unit Consumed",SwingConstants.CENTER);
			grossamt=new JLabel("Gross Amt.",SwingConstants.CENTER);
			netamt=new JLabel("Net Amt.",SwingConstants.CENTER);
			
			coid.setForeground(Color.WHITE);
			readdate.setForeground(Color.WHITE);
			duedate.setForeground(Color.WHITE);
			rebate.setForeground(Color.WHITE);
			cotype.setForeground(Color.WHITE);
			unitcons.setForeground(Color.WHITE);
			grossamt.setForeground(Color.WHITE);
			netamt.setForeground(Color.WHITE);
			
			tname=new JLabel(c.fname+" "+c.lname);
			taddress=new JLabel(c.address);
			tcoid=new JLabel(id,SwingConstants.CENTER);
			tbillno=new JLabel("Bill No. "+String.valueOf(c.billno)+"  ",SwingConstants.RIGHT);
			treaddate=new JLabel(rday+"-"+rmonth+"-"+ryear,SwingConstants.CENTER);
			tduedate=new JLabel(dday+"-"+dmonth+"-"+dyear,SwingConstants.CENTER);
			trebate=new JLabel("Rs."+ c.rebate,SwingConstants.CENTER);
			tcotype=new JLabel(c.cotype,SwingConstants.CENTER);
			tunitcons=new JLabel(String.valueOf(c.unit),SwingConstants.CENTER);
			tgrossamt=new JLabel("Rs. "+ String.valueOf(c.unit*6),SwingConstants.CENTER);
			tnetamt=new JLabel("Rs. "+String.valueOf((c.unit*6)-c.rebate),SwingConstants.CENTER);
			
			tbillno.setForeground(Color.WHITE);
			tname.setForeground(Color.BLACK);
			tname.setForeground(Color.WHITE);
			taddress.setForeground(Color.BLACK);
			tcoid.setForeground(Color.BLACK);
			treaddate.setForeground(Color.BLACK);
			tduedate.setForeground(Color.BLACK);
			trebate.setForeground(Color.BLACK);
			tcotype.setForeground(Color.BLACK);
			tunitcons.setForeground(Color.BLACK);
			tgrossamt.setForeground(Color.BLACK);
			tnetamt.setForeground(Color.BLACK);
			
			OK=new JButton("OK");

			pidctrd=new JLabel();
			pinfo=new JLabel();
			main=new JLabel(new ImageIcon("image/showbill.jpg"));
			
			pidctrd.setLayout(new GridLayout(2,5));
			pidctrd.setBorder(BorderFactory.createLineBorder(Color.white));
			pinfo.setLayout(new GridLayout(2,5));
			pinfo.setBorder(BorderFactory.createLineBorder(Color.white));
			main.setLayout(null);
			
			pidctrd.add(coid);
			pidctrd.add(cotype);
			pidctrd.add(readdate);
			pidctrd.add(tcoid);
			pidctrd.add(tcotype);
			pidctrd.add(treaddate);
			
			pinfo.add(unitcons);
			pinfo.add(grossamt);
			pinfo.add(rebate);
			pinfo.add(netamt);
			pinfo.add(duedate);

			pinfo.add(tunitcons);
			pinfo.add(tgrossamt);
			pinfo.add(trebate);
			pinfo.add(tnetamt);
			pinfo.add(tduedate);		
			
			main.add(tname);
			main.add(taddress);
			main.add(tbillno);
			main.add(pidctrd);
			main.add(pinfo);
			main.add(OK);
			add(main);
			
			tbillno.setBounds(840,10,150,30);
			tname.setBounds(20,30,150,30);
			taddress.setBounds(20,60,150,30);
			pidctrd.setBounds(50,120,900,80);
			pinfo.setBounds(50,240,900,80);
			
			OK.setBounds(875,340,75,45);
			
			setSize(1000,440);
			setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			setTitle("Bill Info");
			setVisible(true);
			
			OK.addActionListener(this);
		}
		catch (SQLException e) 
		{
			Enumeration<Object> keys2 = UIManager.getDefaults().keys();
			while (keys2.hasMoreElements()) 
			{
				Object key = keys2.nextElement();
				Object value = UIManager.get(key);
				if (value instanceof FontUIResource)
				UIManager.put(key, new FontUIResource("Sherif", Font.BOLD, 12));
			}
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.OK_OPTION);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{

		if(e.getSource()==OK)
		{
			if(go=="A")
			{
				Enumeration<Object> keys = UIManager.getDefaults().keys();
				while (keys.hasMoreElements()) 
				{
					Object key = keys.nextElement();
					Object value = UIManager.get(key);
					if (value instanceof FontUIResource)
					UIManager.put(key, new FontUIResource("Sherif", Font.BOLD, 12));
				}
				AdminSelection as=new AdminSelection();
				as.selection();
			}
			
			if(go=="C")
			{
				Enumeration<Object> keys = UIManager.getDefaults().keys();
				while (keys.hasMoreElements()) 
				{
					Object key = keys.nextElement();
					Object value = UIManager.get(key);
					if (value instanceof FontUIResource)
					UIManager.put(key, new FontUIResource("Sherif", Font.BOLD, 12));
				}
				ConsumerSelection as=new ConsumerSelection();
				as.selection(id);
			}
			dispose();
		}
	}
	
	
}
