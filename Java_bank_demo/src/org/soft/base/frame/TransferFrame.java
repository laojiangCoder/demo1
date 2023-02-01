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

//转账界面
public class TransferFrame extends JFrame {
	
	private BankUser bankUser ;
	
	private JPanel jp1,jp2,jp3 ;
	private JLabel jl1,jl2  ;
	private JTextField jtf1,jtf2 ;
	private JButton jb1,jb2 ;
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;
	
	public TransferFrame(BankUser bankUser) {
		
		this.bankUser = bankUser ;
		
		jp1 = new JPanel() ;
		jl1 = new JLabel("收款账号");
		jtf1 = new JTextField(15) ;
		jp1.add(jl1) ;
		jp1.add(jtf1) ;
		
		jp2 = new JPanel() ;
		jl2 = new JLabel("转账金额");
		jtf2 = new JTextField(15) ;
		jp2.add(jl2) ;
		jp2.add(jtf2) ;
		
		jp3 = new JPanel() ;
		jb1 = new JButton("转账") ;
		jb1.addActionListener(new TransferActionListener());
		jb2 = new JButton("取消") ;
		jb2.addActionListener(new TransferActionListener());
		jp3.add(jb1) ;
		jp3.add(jb2) ;
		
		this.setLayout(new GridLayout(3,1));
		this.add(jp1) ;
		this.add(jp2) ;
		this.add(jp3) ;
		
		this.setTitle("转账界面");
		this.setSize(500,200);
		this.setLocation(300,100) ;
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void close() {
		this.dispose();
	}
	
	class TransferActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
              Object obj = e.getSource() ;
              if ( obj == jb1 ) {
            	  //转账
            	  String  innerNo = jtf1.getText() ; //收款账号
            	  String  strTransferMoney = jtf2.getText() ; //转账金额
            	  double transferMoney = Double.parseDouble(strTransferMoney) ;
            	  if ( innerNo != null && !innerNo.equals("") 
            			  && transferMoney > 0 ) {
            		  String outerNo = bankUser.getBankUserNo() ; //转出账号
            		  boolean result = bankUserDao.transfer(outerNo, innerNo, transferMoney) ;
            		  if ( result ) {
            			  //转账成功
            			  close() ;
            		  } else {
            			  //转账失败
            		  }
            	  }
              } else if(obj == jb2) {
            	  //取消、
            	  jtf1.setText("");
            	  jtf2.setText("");
              }
			
		}
		
	}
	

}
