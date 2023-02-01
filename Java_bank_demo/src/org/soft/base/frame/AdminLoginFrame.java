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

import org.soft.base.dao.AdminDao;
import org.soft.base.dao.impl.AdminDaoImpl;
import org.soft.base.model.Admin;

//管理员登录页面
public class AdminLoginFrame extends JFrame {
	
	private JLabel jl1,jl2;
	private JTextField jtf1 ;
	private JPasswordField jpf1 ;
	private JPanel jp1,jp2,jp3 ;
	private JButton jb1,jb2 ;
	
	 
	public AdminLoginFrame() {
		
		jl1 = new JLabel("管理员号:");
		jtf1 = new JTextField(20) ;
		jp1 = new JPanel() ;
		jp1.add(jl1) ;
		jp1.add(jtf1) ;
		
		jl2 = new JLabel("管理员密码：");
		jpf1 = new JPasswordField(20) ;
		jp2 = new JPanel() ;
		jp2.add(jl2) ;
		jp2.add(jpf1) ;
		
		jb1 = new JButton("登录");
		jb1.addActionListener(new MyActionListener());
		jb2 = new JButton("重置");
		jb2.addActionListener(new MyActionListener());
		jp3 = new JPanel() ;
		jp3.add(jb1) ;
		jp3.add(jb2) ;
		
		this.setLayout(new GridLayout(3,1));
		this.add(jp1) ;
		this.add(jp2) ;
		this.add(jp3) ;
		
		
		this.setTitle("管理员登录界面");
		this.setVisible(true);
		this.setSize(500,200);
		this.setLocation(300,150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
		new AdminLoginFrame() ;
	}
	
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 if ( e.getSource() == jb1 ) {
				 //登录
				 String adminNo = jtf1.getText() ;
				 String adminPass = new String(jpf1.getPassword()) ;
				 
				 AdminDao adminDao = new AdminDaoImpl() ;
				 Admin admin = new Admin() ;
				 admin.setAdminNo(adminNo);
				 admin.setAdminPass(adminPass);
				 
				 Admin adm =  adminDao.loginAdmin(admin) ;
				 if ( adm != null ) {
					 //登录成功
				 new AdminMainFrame(adm) ; //跳转到主页面
					 
				 } else {
					 //登录失败
					 jtf1.setText(""); 
					 jpf1.setText("");
				 }
				 
			 } else if ( e.getSource() == jb2 ) {
				 //重置
				 jtf1.setText(""); 
				 jpf1.setText("");
			 }  
		}
		
	}

}
