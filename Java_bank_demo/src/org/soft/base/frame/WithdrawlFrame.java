package org.soft.base.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.soft.base.dao.BankUserDao;
import org.soft.base.dao.impl.BankUserDaoImpl;
import org.soft.base.model.BankUser;

//取款界面
public class WithdrawlFrame extends  JFrame {
	
private BankUser bankUser ;
	
	
	private JPanel jp1,jp2 ;
	private JLabel jl1 ;
	private JTextField jtf1 ;
	private JButton jb1,jb2 ;
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;
	
	public WithdrawlFrame(BankUser bankUser) {
		
        this.bankUser = bankUser ;
		
		jp1 = new JPanel() ;
		jl1 = new JLabel("存款金额");
		jtf1 = new JTextField(20) ;
		jp1.add(jl1) ;
		jp1.add(jtf1) ;
		
		jp2 = new JPanel() ;
		jb1= new JButton("取款");
		jb1.addActionListener(new WithdrawlActionListener());
		jb2 = new JButton("取消");
		jb2.addActionListener(new WithdrawlActionListener());
		jp2.add(jb1) ;
		jp2.add(jb2) ;
		
		this.setLayout(new GridLayout(2,1));
		this.add(jp1) ;
		this.add(jp2) ;
		
		this.setTitle("取款界面");
		this.setSize(500,200);
		this.setLocation(300,100) ;
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public  void close() {
		this.dispose(); 
	}
		
    class WithdrawlActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		     Object obj = e.getSource() ;
		     if ( obj == jb1 ) {
		    	 //取款
		    	 String strMoney = jtf1.getText();
				 double money = 0.00 ;
				 if ( strMoney != null && !strMoney.equals("") ) {
					 money = Double.parseDouble(strMoney) ;
					 if ( money > 0 ) {
						 BankUser bu = new BankUser() ;
						 bu.setBankUserId(bankUser.getBankUserId());
						 bu.setBankBalance(money);
						 boolean result = bankUserDao.withdrawl(bu) ;
						 if( result ) {
							 //取款成功
							 close() ;
						 } else {
							 //取款失败
						 }
					 }
				 }	 
		     } else if ( obj == jb2 ) {
		    	 //取消
		    	 jtf1.setText(""); 
		     }
			
		}
    	
    }
	

}
