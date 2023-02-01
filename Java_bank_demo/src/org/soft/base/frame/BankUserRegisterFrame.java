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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.soft.base.dao.BankDao;
import org.soft.base.dao.BankUserDao;
import org.soft.base.dao.impl.BankDaoImpl;
import org.soft.base.dao.impl.BankUserDaoImpl;
import org.soft.base.model.Bank;
import org.soft.base.model.BankUser;

//银行用户注册界面
public class BankUserRegisterFrame extends JFrame {
	
	private JPanel jp1,jp2,jp3,jp4 ;
	private JLabel jl1,jl2,jl3 ;
	private JTextField jtf1 ;
	private JPasswordField jpf1 ;
	private JComboBox<Vector<Bank>> jcb1 ;   //下拉列表
	private JButton jb1,jb2 ;
	
	private BankDao bankDao = new BankDaoImpl() ;
	
	private BankUserDao bankUserDao = new BankUserDaoImpl() ;
	
	private Bank  selectedBank = null ; //选中的银行
	
	public BankUserRegisterFrame() {
		
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
		
		jl3 = new JLabel("银行");
		List<Bank> bankList = bankDao.getAllBanks() ;
		Vector<Bank> bankVect = new Vector<Bank>() ;
		bankVect.add(new Bank(0,"请选择银行")); //增加一个初始选项，没有实际含义
		for(Bank bank:bankList) {
			bankVect.add(bank ) ;
		}
		jcb1 = new JComboBox(bankVect) ; //数据为Bank类型，显示是执行bank.toString()
		jcb1.addItemListener(new BankItemListener());
 		jp3 = new JPanel() ;
		jp3.add(jl3) ;
		jp3.add(jcb1) ;
		
		jb1 = new JButton("注册");
		jb1.addActionListener(new RegisterActionListener());
		jb2 = new JButton("重置");
		jb2.addActionListener(new RegisterActionListener());
		jp4 = new JPanel() ;
		jp4.add(jb1) ;
		jp4.add(jb2) ;
		
		this.setLayout(new GridLayout(4,1));
		
		this.add(jp1) ;
		this.add(jp2) ;
		this.add(jp3) ;
		this.add(jp4) ;
		
		this.setTitle("银行用户注册界面");
		this.setSize(300,200);
		this.setLocation(300,200) ;
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void close() {
		this.dispose(); 
	}
	
	class BankItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			selectedBank =  (Bank) e.getItem() ;
		}
		
	}
	
	class RegisterActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		     if (  e.getSource() == jb1 ) {
		    	 //注册
		    	 String bankUserNo = jtf1.getText() ;
		    	 String bankUserPass = new String(jpf1.getPassword()) ;
		    	 BankUser bankUser = new BankUser() ;
		    	 bankUser.setBankUserNo(bankUserNo);
		    	 bankUser.setBankUserPass(bankUserPass);
		    	 bankUser.setBankId(selectedBank.getBankId());
		    	 boolean result = bankUserDao.register(bankUser) ;
		    	 if ( result ) {
		    		 //注册成功
		    		 close() ; //关闭注册界面
		    		 new BankUserLoginFrame() ; //打开登录界面
		    	 } else {
		    		 //注册失败
		    		 System.out.println("注册失败");
		    	 }
		     } else {
		    	 //重置
		    	 jtf1.setText(""); 
		    	 jpf1.setText("");
		    	 jcb1.setSelectedIndex(0); //选中第一条记录（初始化）
		     }
			
		}
		
	}
	
	public static void main(String[] args) {
		new BankUserRegisterFrame() ;
	}

}
