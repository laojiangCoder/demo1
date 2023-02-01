package org.soft.base.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.soft.base.dao.BankDao;
import org.soft.base.dao.BankUserDao;
import org.soft.base.dao.impl.BankDaoImpl;
import org.soft.base.dao.impl.BankUserDaoImpl;
import org.soft.base.model.Bank;
import org.soft.base.model.BankUser;

 

//修改银行用户界面
public class BankUserUpdateFrame extends JFrame {
	
	private BankUser bankUser ;
	
	private JPanel jp1,jp2,jp3,jp4 ;
	private JLabel jl1,jl2,jl3 ;
	private JTextField jtf1,jtf2 ;
	private JComboBox<Vector<Bank>> jcb1 ;   //下拉列表
	private JButton jb1,jb2 ;
	
	private BankDao bankDao = new BankDaoImpl() ;
	
	private Bank selectedBank = null ;
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;

	
	public BankUserUpdateFrame(BankUser bankUser) {
		this.bankUser = bankUser  ;
		
		jp1 = new JPanel() ;
		jl1 = new JLabel("用户账号：") ;
		jtf1 = new JTextField(10) ;
		jtf1.setText(this.bankUser.getBankUserNo());
		jp1.add(jl1) ;
		jp1.add(jtf1) ;
		
		jp2 = new JPanel() ;
		jl2 = new JLabel("用户姓名：");
		jtf2 = new JTextField(10) ;
		jtf2.setText(this.bankUser.getBankUserName());
		jp2.add(jl2) ;
		jp2.add(jtf2) ;
		
		jp3= new JPanel() ;
		jl3 = new JLabel("银行：") ;
		List<Bank> bankList = bankDao.getAllBanks() ;
		Vector<Bank> bankVect = new Vector<Bank>() ;
		bankVect.add(new Bank(0,"请选择银行")); //增加一个初始选项，没有实际含义
		for(Bank bank:bankList) {
			bankVect.add(bank ) ;
		}
		jcb1 = new JComboBox(bankVect) ; //数据为Bank类型，显示是执行bank.toString()
		for(int i=0;i<bankVect.size();i++) {
		   Bank bank = bankVect.get(i) ;
			if ( bank.getBankId() == this.bankUser.getBankId() ) {
				selectedBank = bankVect.get(i) ;//默认选中的银行（原来的银行对象）
				 jcb1.setSelectedIndex(i);
				 break ;
			}
		}
        jcb1.addItemListener(new BankUserItemListener());
 		jp3 = new JPanel() ;
		jp3.add(jl3) ;
		jp3.add(jcb1) ;
		
		jp4 = new JPanel() ;
		jb1 = new JButton("修改");
		jb1.addActionListener(new BankUserUpdateActionListener()) ;
		jb2 = new JButton("重置");
		jb1.addActionListener(new BankUserUpdateActionListener()) ;
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
	
	public void close() {
		this.dispose(); 
	}

	class BankUserUpdateActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 Object obj = e.getSource() ;
			 if ( obj == jb1 ) {
				 //修改
				 String  bankUserNo = jtf1.getText() ; //银行账号
				 String  bankUserName = jtf2.getText() ; //用户姓名
				 int bankId = selectedBank.getBankId() ; //所属银行Id
				 BankUser bu = new BankUser() ;
				 bu.setBankUserNo(bankUserNo);
				 bu.setBankUserName(bankUserName);
				 bu.setBankId(bankId);
				 bu.setBankUserId(bankUser.getBankUserId());
				 
				 boolean result = bankUserDao.updateBankUser(bu) ;
				 if ( result ) {
					 //修改用户信息成功
					 close() ;
				 } else {
					 //修改用户信息失败
				 }
				  
			 } else if ( obj == jb2 ) {
				 //重置
			 }
			
		}
		
	}
	
	class BankUserItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			selectedBank = (Bank)e.getItem() ;
		}
		
	}
	
}
