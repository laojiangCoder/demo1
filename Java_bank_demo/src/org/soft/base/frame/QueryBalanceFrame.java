package org.soft.base.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.soft.base.dao.BankUserDao;
import org.soft.base.dao.impl.BankUserDaoImpl;
import org.soft.base.model.BankUser;

public class QueryBalanceFrame extends JFrame {
	
	private BankUser bankUser ;
	
	private  JPanel jp1 ;
	private  JLabel jl1,jl2 ;
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;
	
	public QueryBalanceFrame(BankUser bankUser) {
		this.bankUser = bankUser ;
		
		jp1 = new JPanel() ;
		jl1 = new JLabel("余额为") ;
		this.bankUser = bankUserDao.getBankUserById(bankUser.getBankUserId()) ;
		jl2 = new JLabel(""+this.bankUser.getBankBalance()) ; //显示余额 
		jp1.add(jl1) ;
		jp1.add(jl2) ;
		this.add(jp1) ;
		
		this.setTitle("查询界面");
		this.setSize(500,200);
		this.setLocation(300,100) ;
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

}
