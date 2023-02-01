package org.soft.base.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.soft.base.dao.BankDao;
import org.soft.base.dao.impl.BankDaoImpl;
import org.soft.base.model.Admin;
import org.soft.base.model.Bank;

//新增银行界面
public class BankAddFrame  extends JFrame {
	
	private BankFrame bankFrame ;
	
	private JLabel jl1 ;
	private JTextField jtf1 ;
	private JButton jb1 ;
	private JPanel jp1,jp2 ;
	
	private BankDao bankDao  = new BankDaoImpl() ;
	
	//将银行主界面作为参数传给当前界面
	public BankAddFrame(BankFrame bankFrame) {
		
		this.bankFrame = bankFrame ;
		
		jl1 = new JLabel("银行名称");
		jtf1 = new JTextField(20) ;
		jp1 = new JPanel() ;
		jp1.add(jl1) ;
		jp1.add(jtf1) ;
		
		jb1 = new JButton("新增");
		jb1.addActionListener(new BankAddActionListener());
		jp2 = new JPanel() ;
		jp2.add(jb1) ;
		
		this.setLayout(new GridLayout(2,1));
		this.add(jp1) ;
		this.add(jp2) ;
		
		this.setTitle("新增银行界面");
		this.setSize(500,200);
		this.setLocation(300,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	//关闭
	public void close() {
		this.dispose(); 
	}
	
	class BankAddActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 if ( e.getSource() ==  jb1 ) {
				 //新增银行
				 String bankName = jtf1.getText() ;
				 Bank bank = new Bank() ;
				 bank.setBankName(bankName);
				 
				boolean result = bankDao.addBank(bank) ;
				if ( result ) {
					//新增银行成功
					 close() ; //关闭当前frame
					 bankFrame.close();  //关闭银行Frame
					 new BankFrame(bankFrame.getAdmin(),null,1) ;//刷新银行界面的表格数据
					jtf1.setText("");
				} else {
					//新增银行失败
				}
			 }
			
		}
		
	}

}
