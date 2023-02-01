package org.soft.base.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.soft.base.dao.BankUserDao;
import org.soft.base.dao.impl.BankUserDaoImpl;
import org.soft.base.model.BankUser;

//银行用户主界面
public class BankUserMainFrame extends JFrame {
	
	private BankUser bankUser  ;  //登录成功的银行用户对象
	
	private BankUserMainFrame current ; //保存当前Frame对象
	
	private JButton jb1,jb2,jb3,jb4,jb5,jb6 ;
	private  JPanel   jp1,jp2 ;
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;
	
	public BankUserMainFrame(BankUser bankUser) {
		this.bankUser = bankUser ;
		
		jb1 = new JButton("修改用户信息") ;
		jb1.addActionListener(new BankUserMainActionListener());
		jp1 = new JPanel() ;
		jp1.setSize(600,200);
		jp1.add(jb1) ;
		
		
		jb2 = new JButton("修改密码");
		jb2.addActionListener(new BankUserMainActionListener());
		jp1.add(jb2) ;
		
		jb3 = new JButton("存款");
		jb3.addActionListener(new BankUserMainActionListener());
		jp2 = new JPanel() ;
		jp2.setSize(600,200);
		jp2.add(jb3) ;
		
		jb4 = new JButton("取款");
		jb4.addActionListener(new BankUserMainActionListener());
		jp2.add(jb4) ;
		
		jb5 = new JButton("查询");
		jb5.addActionListener(new BankUserMainActionListener());
		jp2.add(jb5) ;
		
		jb6 = new JButton("转账");
		jb6.addActionListener(new BankUserMainActionListener());
		jp2.add(jb6) ;
		
		this.setLayout(new GridLayout(2,1)) ;
		this.add(jp1) ;
		this.add(jp2) ;
		
		this.setTitle("银行用户主界面!欢迎您，"+this.bankUser.getBankUserName() );
		this.setVisible(true);
		this.setSize(800,600);
		this.setLocation(200,0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		current = this ; // 保存当前Frame对象
		
	}
	
	public void close() {
		this.dispose(); 
	}
	
	class BankUserMainActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 Object obj = e.getSource() ; //动作监听事件的对象
			 if ( obj == jb1 ) {
				 //修改用户信息
				 //获取最新的用户信息，因为这些信息可能会改变
				 BankUser newBankUser = bankUserDao.getBankUserById(bankUser.getBankUserId()) ;
				 new BankUserUpdateFrame(newBankUser) ;
			 } else if ( obj == jb2 ) {
				 //修改密码
				 BankUser newBankUser = bankUserDao.getBankUserById(bankUser.getBankUserId()) ;
				 new BankUserChangePasswordFrame(newBankUser) ;
			 } else if (obj == jb3) {
				 //存款
				 new  DepositFrame(bankUser) ;
			 } else if ( obj == jb4 ) {
				 //取款
				 new WithdrawlFrame(bankUser) ;
			 } else if ( obj == jb5) {
				 //查询
				 new QueryBalanceFrame(bankUser) ;
			 } else if ( obj == jb6) {
				 //转账
				 new TransferFrame(bankUser) ;
			 }
			
		}
		
	}

}
