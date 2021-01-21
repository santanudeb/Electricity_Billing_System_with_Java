package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import adminchoice.AdminSelection;
import consumerpage.ConsumerSelection;

@SuppressWarnings({ "serial" })
public class BillDetails extends JFrame implements ActionListener
{
	//private String id,name,address,unit_consumed,rebate,readdate,duedate,bill_no;
	private JLabel coid,rdate,ddate,rebet,cotype,unitcons,grossamt,netamt;
	private JLabel tname,taddress,tcoid,tbillno,treaddate,tduedate,trebate,tcotype,tunitcons,tgrossamt,tnetamt;
	private JLabel main;
	private JButton OK;
	private JLabel pidctrd,pinfo;
	private String id;
	
	
	public void billdetails(String id,String name,String address,int unit_consumed,int rebate, String readdate,String duedate,int bill_no)
	{
		this.id=id;
		coid=new JLabel("Consumer's ID",SwingConstants.CENTER);
		rdate=new JLabel("Reading Date",SwingConstants.CENTER);
		ddate=new JLabel("Due Date",SwingConstants.CENTER);
		rebet=new JLabel("Rebate",SwingConstants.CENTER);
		cotype=new JLabel("Consumer Type",SwingConstants.CENTER);
		unitcons=new JLabel("Unit Consumed",SwingConstants.CENTER);
		grossamt=new JLabel("Gross Amt.",SwingConstants.CENTER);
		netamt=new JLabel("Net Amt.",SwingConstants.CENTER);
		
		coid.setForeground(Color.WHITE);
		rdate.setForeground(Color.WHITE);
		ddate.setForeground(Color.WHITE);
		rebet.setForeground(Color.WHITE);
		cotype.setForeground(Color.WHITE);
		unitcons.setForeground(Color.WHITE);
		grossamt.setForeground(Color.WHITE);
		netamt.setForeground(Color.WHITE);
		
		tname=new JLabel(name);
		taddress=new JLabel(address);
		tcoid=new JLabel(id,SwingConstants.CENTER);
		tbillno=new JLabel("Bill No. "+String.valueOf(bill_no)+"  ",SwingConstants.RIGHT);
		treaddate=new JLabel(readdate,SwingConstants.CENTER);
		tduedate=new JLabel(duedate,SwingConstants.CENTER);
		trebate=new JLabel("Rs."+ rebate,SwingConstants.CENTER);
		tcotype=new JLabel("Domestic",SwingConstants.CENTER);
		tunitcons=new JLabel(String.valueOf(unit_consumed),SwingConstants.CENTER);
		tgrossamt=new JLabel("Rs. "+ String.valueOf(unit_consumed*6),SwingConstants.CENTER);
		tnetamt=new JLabel("Rs. "+String.valueOf((unit_consumed*6)-rebate),SwingConstants.CENTER);
		
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
		pidctrd.add(rdate);
		pidctrd.add(tcoid);
		pidctrd.add(tcotype);
		pidctrd.add(treaddate);
		
		pinfo.add(unitcons);
		pinfo.add(grossamt);
		pinfo.add(rebet);
		pinfo.add(netamt);
		pinfo.add(ddate);

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


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==OK)
		{
			
			AdminSelection as=new AdminSelection();
			as.selection();
			ConsumerSelection sa=new ConsumerSelection();
			sa.selection(id);
			dispose();
			
		}
		
	}
	
}
