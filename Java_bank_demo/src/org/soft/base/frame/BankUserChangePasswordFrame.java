package org.soft.base.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.soft.base.dao.BankUserDao;
import org.soft.base.dao.impl.BankUserDaoImpl;
import org.soft.base.model.BankUser;

//银行用户修改密码界面
public class BankUserChangePasswordFrame  extends JFrame {
	
	private BankUser bankUser = null ;
	
	private JPanel jp1,jp2,jp3,jp4 ;
	private JLabel jl1,jl2,jl3 ;
	private JPasswordField jpf1,jpf2,jpf3 ;
	private JButton jb1,jb2 ;
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;
	
	public BankUserChangePasswordFrame(BankUser bankUser) {
		
		this.bankUser = bankUser  ;
		
		jp1 = new JPanel() ;
		jl1 = new JLabel("旧密码");
		jpf1= new JPasswordField(15) ;
        jp1.add(jl1) ;
        jp1.add(jpf1) ;
        
        jp2 = new JPanel() ;
        jl2 = new  JLabel("新密码") ;
        jpf2 = new JPasswordField(15) ;
        jp2.add(jl2) ;
        jp2.add(jpf2) ;
        
        jp3 = new JPanel() ;
        jl3 = new JLabel("确认密码");
        jpf3 = new JPasswordField(15) ;
        jp3.add(jl3) ;
        jp3.add(jpf3) ;
        
        jp4 = new JPanel() ;
        jb1 = new JButton("修改密码");
        jb1.addActionListener(new BankUserChangePasswordActionListener());
        jb2 = new JButton("重置");
        jb2.addActionListener(new BankUserChangePasswordActionListener());
        jp4.add(jb1) ;
        jp4.add(jb2) ;
        
        this.setLayout(new GridLayout(4,1));
        this.add(jp1) ;
        this.add(jp2) ;
        this.add(jp3) ;
        this.add(jp4) ;
		
		this.setTitle("修改银行用户界面");
		this.setSize(500,300);
		this.setLocation(300,100) ;
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public  void close() {
		this.dispose();
	}
	
	class BankUserChangePasswordActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
             Object obj = e.getSource() ;
             if ( obj == jb1  ) {
            	 //修改密码
            	 String oldPassword = new String(jpf1.getPassword()) ; //旧密码
            	 String newPassword = new String(jpf2.getPassword()) ; //新密码
            	 String confirmPassword = new String(jpf3.getPassword()) ; //确认密码
            	 if ( !oldPassword.equals(bankUser.getBankUserPass()) ) {
            		 //输入的旧密码和原密码不一致
            		 System.out.println("输入的旧密码和原密码不一致") ;
            		 return ;
            	 } 
            	 if ( !newPassword.equals(confirmPassword) ) {
            		 //新密码和确认密码不一致
            		 System.out.println("新密码和确认密码不一致") ;
            		 return ;
            	 }
            	 
            	 BankUser bu  = new BankUser() ;
            	 bu.setBankUserId(bankUser.getBankUserId());
            	 bu.setBankUserPass(newPassword);
            	 
            	 boolean result = bankUserDao.changePassword(bu) ;
            	 if ( result ) {
            		 //修改密码成功
            		 close() ;
            	 }
            	 
             } else if ( obj == jb2 ) {
            	 //重置
             }
			
		}
		
	}

}
