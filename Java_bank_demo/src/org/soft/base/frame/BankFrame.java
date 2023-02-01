package org.soft.base.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.soft.base.dao.BankDao;
import org.soft.base.dao.impl.BankDaoImpl;
import org.soft.base.model.Admin;
import org.soft.base.model.Bank;

public class BankFrame extends JFrame {
	
	private Admin admin ; //登录成功的管理员信息
	
	private BankFrame current ; //当前Frame的对象，用于保存
	
	private BankDao bankDao = new BankDaoImpl() ;
	
	private JTable jt ;
	private JScrollPane jsp ; //滚动Pane
	private DefaultTableModel dtm ; //默认表格模型
	private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8 ;
	private JPanel jp1,jp2,jp3 ;
	private JLabel jl1 ;
	private JTextField  jtf1 ;
	
	private List<Bank> bankList = null ; //银行列表数据
	private  Vector<Object> titlesVect  = new Vector<Object>()  ; //表格标题容器
	private  Vector<Vector<Object>> dataVect = new Vector<Vector<Object>>() ; //表格数据容器
	
	private int page = 1 ; //当前页码
	private  int begin = 0 ;  //开始记录号
	private  int size = 5 ;  //每页记录数
	private int totalPages = 0 ; //总页数
	private int recordCount = 0 ; //总记录数
	
	public BankFrame(Admin admin,List<Bank> bankList,int page) {
		
		this.admin = admin ;
		this.bankList = bankList ;
        this.page = page ;
	
		
		jl1= new JLabel("银行名称");
		jtf1 = new JTextField(20) ;
		jb4 = new JButton("查询");
		jb4.addActionListener(new BankActionListener());
		jp2 = new JPanel() ;
		jp2.add(jl1) ;
		jp2.add(jtf1) ;
		jp2.add(jb4) ;
		
		this.add(jp2,BorderLayout.NORTH) ;
		
		jb1 = new JButton("新增");
		jb1.addActionListener(new BankActionListener());
		jb2 = new JButton("修改");
		jb2.addActionListener(new BankActionListener());
		jb3 = new JButton("删除");
		jb3.addActionListener(new BankActionListener());
		jp1 = new JPanel() ;
		jp1.add(jb1) ;
		jp1.add(jb2) ;
		jp1.add(jb3) ;
		
		//jp3 = new JPanel() ;
		jb5 = new JButton("上一页") ;
		jb5.addActionListener(new BankActionListener()) ;
		jp1.add(jb5) ;
		jb6 = new JButton("下一页") ;
		jb6.addActionListener(new BankActionListener()) ;
		jp1.add(jb6) ;
		jb7 = new JButton("首页") ;
		jb7.addActionListener(new BankActionListener()) ;
		jp1.add(jb7) ;
		jb8 = new JButton("末页") ;
		jb8.addActionListener(new BankActionListener()) ;
		jp1.add(jb8) ;
		//this.add(jp3,BorderLayout.SOUTH) ;
		
		this.add(jp1,BorderLayout.SOUTH) ;	
		
		fresh() ;
		
		this.setTitle("银行管理界面");
		this.setSize(800,600);
		this.setLocation(100,0);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //关闭当前的Frame
		
		current = this; //保存当前Frame对象
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	
	//刷新表格的数据
	public  void fresh() {
		   //表格数据刷新
				//标题初始化
				Object[] titlesArr = {"银行Id","银行名称"} ;   // 表格标题数组
				for(int i=0;i<titlesArr.length;i++) {
					titlesVect.add(titlesArr[i]) ;
				}
				
				begin = (page-1) * size ;
				
				// 如果银行列表为空，表示初始化所有的银行，否则显示查询的结果
			   if ( bankList == null ) {
				   //初始化
				 //  bankList = bankDao.getAllBanks() ;
				   bankList = bankDao.getAllBanksPage(begin,size) ;
				   recordCount = bankDao.getAllBanksCount() ;
				   if (  recordCount % size == 0 ) {
					   totalPages = recordCount / size ;
				   } else {
					   totalPages = recordCount / size + 1 ;
				   }
			   }
				
				for(int i=0;i<bankList.size();i++) {
					Vector<Object> vect = new Vector<Object>() ;
					Bank bank = bankList.get(i) ;
					vect.add(bank.getBankId()) ;
					vect.add(bank.getBankName()) ;
					dataVect.add(vect) ;
				}
				
				//重新加载表格的所有数据
				dtm = new DefaultTableModel(dataVect,titlesVect) ;
				jt = new JTable(dtm) ;
				jsp = new JScrollPane(jt) ;
			  
			   this.add(jsp) ;
	}
	
	public void close() {
		this.dispose();
	}
	
	class BankActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == jb1 ) {
				//新增
				new BankAddFrame(current) ;
			} else if ( e.getSource() == jb2 ) {
				//修改
			     int selectRow = jt.getSelectedRow() ;
			     Vector<Vector> dtVec = dtm.getDataVector() ; //获取表格的所有数据
			     Vector rowVec = dtVec.get(selectRow) ; //获取选中行的数据
			     int bankId = (int)rowVec.get(0) ; //获取选中行的第一个字段（银行id）的值
                 Bank bank = bankDao.getBankById(bankId) ;
			     new BankUpdateFrame(current,bank) ;
			} else if ( e.getSource() == jb3 ) {
				//删除
				 int selectRow = jt.getSelectedRow() ;
				 int bankId = (int)dtm.getDataVector().get(selectRow).get(0) ;
				 boolean result = bankDao.deleteBank(bankId) ;
				 if ( result ) {
					 //刷新表格数据
					 close();
					 new BankFrame(admin,null,page) ;
				 }
			} else if (e.getSource()==jb4) {
				//查询
				String bankName = jtf1.getText() ;
				//bankList = bankDao.queryBanks(bankName) ;
				bankList = bankDao.queryBanksPage(bankName,begin,size) ;
				close() ;
				new BankFrame(admin,bankList,page) ;
			}else if ( e.getSource() == jb5 ) {
				//上一页
				page = page - 1 ;
				if ( page <= 0 ) {
					page = 1 ;
				}
				begin = (page-1)*size ;
				String bankName = jtf1.getText() ;
				if ( bankName != null && !bankName.equals("")) {
					bankList = bankDao.queryBanksPage(bankName,begin,size) ;
				} else {
					bankList = bankDao.getAllBanksPage(begin,size) ;
				}
				close() ;
				new BankFrame(admin,bankList,page) ;
				
			}else if ( e.getSource() == jb6 ) {
				//下一页
				page = page + 1 ;
				if ( page > totalPages && totalPages > 0 ) {
					page = totalPages ;
				}  
				begin = (page-1)*size ;
				String bankName = jtf1.getText() ;
				if ( bankName != null && !bankName.equals("")) {
					bankList = bankDao.queryBanksPage(bankName,begin,size) ;
				} else {
					bankList = bankDao.getAllBanksPage(begin,size) ;
					recordCount  = bankDao.getAllBanksCount() ;
				}
				if (  recordCount % size == 0 ) {
					   totalPages = recordCount / size ;
				   } else {
					   totalPages = recordCount / size + 1 ;
				   }
				close() ;
				new BankFrame(admin,bankList,page) ;
				
			}else if ( e.getSource() == jb7 ) {
				//首页
				page =   1 ;
				begin = (page-1)*size ;
				String bankName = jtf1.getText() ;
				if ( bankName != null && !bankName.equals("")) {
					bankList = bankDao.queryBanksPage(bankName,begin,size) ;
				} else {
					bankList = bankDao.getAllBanksPage(begin,size) ;
				}
				close() ;
				new BankFrame(admin,bankList,page) ;
				
			}else if ( e.getSource() == jb8 ) {
				//末页
				
				String bankName = jtf1.getText() ;
				if ( bankName != null && !bankName.equals("")) {
					bankList = bankDao.queryBanksPage(bankName,begin,size) ;
				} else {
					bankList = bankDao.getAllBanksPage(begin,size) ;
					recordCount  = bankDao.getAllBanksCount() ;
					
				}
				if (  recordCount % size == 0 ) {
					   totalPages = recordCount / size ;
				   } else {
					   totalPages = recordCount / size + 1 ;
				   }
				page =   totalPages ;
				begin = (page-1)*size ;
				close() ;
				new BankFrame(admin,bankList,page) ;
				
			}
			
		}
		
	}

}
