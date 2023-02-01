package org.soft.base.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import org.soft.base.dao.BankUserDao;
import org.soft.base.dao.impl.BankUserDaoImpl;
import org.soft.base.model.BankUser;

//银行用户登录界面
public class BankUserLoginFrame extends JFrame {
	
	private JPanel jp1,jp2,jp3 ;
	private JLabel jl1,jl2 ;
	private JTextField jtf1 ;
	private JPasswordField jpf1 ;
	private JButton jb1,jb2 ;
	
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;
	

	public BankUserLoginFrame() {
		
		jl1 = new JLabel("银行账号") ;
		jtf1 = new JTextField(20) ;
		jp1 = new JPanel() ;
		jp1.add(jl1) ;
		jp1.add(jtf1) ;
		
		jl2 = new JLabel("密码");
		jpf1 = new JPasswordField(20) ;
		jp2 = new JPanel() ;
		jp2.add(jl2) ;
		jp2.add(jpf1) ;
		
				
		jb1 = new JButton("登录");
		jb1.addActionListener(new BankUserLoginActionListener());
		jb2 = new JButton("重置");
		jb2.addActionListener(new BankUserLoginActionListener());
		jp3 = new JPanel() ;
		jp3.add(jb1) ;
		jp3.add(jb2) ;
		
		this.setLayout(new GridLayout(3,1));
		
		this.add(jp1) ;
		this.add(jp2) ;
		this.add(jp3) ;
		
		this.setTitle("银行用户登录界面");
		this.setSize(400,300);
		this.setLocation(300,200) ;
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	 
	
	class BankUserLoginActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		     if (  e.getSource() == jb1 ) {
		    	 //登录
		    	 String bankUserNo = jtf1.getText() ;
		    	 String bankUserPass = new String(jpf1.getPassword()) ;
		    	 BankUser bankUser = new BankUser() ;
		    	 bankUser.setBankUserNo(bankUserNo);
		    	 bankUser.setBankUserPass(bankUserPass);

                BankUser bu  = bankUserDao.login(bankUser) ;
                if ( bu != null ) {
                	//登录成功
                	new BankUserMainFrame(bu) ;
                } else {
                	//登录失败
                }
		    	 
		     } else {
		    	 //重置
		    	 jtf1.setText(""); 
		    	 jpf1.setText("");
		      }
			
		}
		
	}
	
	public static void main(String[] args) {
		new BankUserLoginFrame() ;
	}

}
