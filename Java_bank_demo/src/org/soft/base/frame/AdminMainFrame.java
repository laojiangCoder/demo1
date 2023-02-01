package org.soft.base.frame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.soft.base.model.Admin;

//管理员主页面
public class AdminMainFrame extends JFrame {
	
	private Admin admin ; //登录成功的管理员对象
	
	private JButton jb1,jb2 ; 
	private JPanel jp1 ; 
	
	public AdminMainFrame(Admin admin){
		
		this.admin = admin ; 
		
		jp1 = new JPanel() ;
		jb1 = new JButton("银行管理");
		jb1.addActionListener(new BankActionListener());
		jb2 = new JButton("银行手续费");
		jb2.addActionListener(new BankActionListener());
		jp1.add(jb1) ;
		jp1.add(jb2) ;
		
		this.setLayout(new FlowLayout());
		this.add(jp1) ;
		
		this.setTitle("管理员主界面!欢迎您，"+this.admin.getAdminName());
		this.setVisible(true);
		this.setSize(800,600);
		this.setLocation(200,0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class BankActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource() ;
			if ( obj == jb1  ) {
				//银行管理
				 new BankFrame(admin,null,1) ;
			} else if ( obj == jb2 ) {
				//银行手续费
				new BankFeeFrame() ;
			}
		   
			
		}
		
	}
	
 

}
