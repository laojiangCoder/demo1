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
import org.soft.base.model.Bank;

//修改银行界面
public class BankUpdateFrame extends JFrame {
	
	private BankFrame bankFrame ; //银行界面
	
	private Bank oldBank ; //需要修改的原来银行信息
	
	private JLabel jl1 ;
	private JTextField jtf1 ;
	private JButton jb1 ;
	private JPanel jp1,jp2 ;
	
	private BankDao bankDao  = new BankDaoImpl() ;
	
	public BankUpdateFrame(BankFrame bankFrame,Bank oldBank) {
		//保存上个界面传来的信息
		this.bankFrame = bankFrame ;
		this.oldBank = oldBank  ;
		
		
		jl1 = new JLabel("银行名称");
		//显示原来的银行名称
		jtf1 = new JTextField(20) ;
		jtf1.setText(oldBank.getBankName());
		jp1 = new JPanel() ;
		jp1.add(jl1) ;
		jp1.add(jtf1) ;
		
		jb1 = new JButton("修改");
		jb1.addActionListener(new BankUpdateActionListener());
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
	
    class  BankUpdateActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 if ( e.getSource() == jb1 ) {
				 String bankName = jtf1.getText() ;
				 Bank newBank = new Bank() ;
				 newBank.setBankId(oldBank.getBankId());
				 newBank.setBankName(bankName);
				 boolean result = bankDao.updateBank(newBank) ;
				 if ( result ) {
					 //修改成功
					 close() ; //关闭当前frame
					 bankFrame.close();  //关闭银行Frame
					 new BankFrame(bankFrame.getAdmin(),null,1) ;//刷新银行界面的表格数据 
				 } else {
					 //修改失败
				 }
			 }
			
		}
    	
    }

}
