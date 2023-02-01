package org.soft.base.frame;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.soft.base.dao.BankFeeDao;
import org.soft.base.dao.impl.BankFeeDaoImpl;
import org.soft.base.model.BankSumFee;

//银行手续费
public class BankFeeFrame  extends JFrame {
	
	private  List<BankSumFee> bankSumFeeList = null ;
	private  Vector<Object> titlesVect  = new Vector<Object>()  ; //表格标题容器
	private  Vector<Vector<Object>> dataVect = new Vector<Vector<Object>>() ; //表格数据容器
	
	private JTable jt ;
	private DefaultTableModel dtm ;
	private JScrollPane jsp ;
	
	private BankFeeDao bankFeeDao = new BankFeeDaoImpl() ;
	
	public BankFeeFrame() {
		
		//表格数据刷新
		//标题初始化
		Object[] titlesArr = {"银行名称","银行手续费"} ;   // 表格标题数组
		for(int i=0;i<titlesArr.length;i++) {
			titlesVect.add(titlesArr[i]) ;
		}
 
	     bankSumFeeList = bankFeeDao.getBankSumFees() ;
		
		for(int i=0;i<bankSumFeeList.size();i++) {
			Vector<Object> vect = new Vector<Object>() ;
			BankSumFee bankSumFee = bankSumFeeList.get(i) ;
			vect.add(bankSumFee.getBankName()) ;
			vect.add(bankSumFee.getSumFee()) ;
			dataVect.add(vect) ;
		}
		
		//重新加载表格的所有数据
		dtm = new DefaultTableModel(dataVect,titlesVect) ;
		jt = new JTable(dtm) ;
		jsp = new JScrollPane(jt) ;
	  
	    this.add(jsp) ;
		
		this.setTitle("银行手续费界面");
		this.setSize(800,600);
		this.setLocation(100,0);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //关闭当前的Frame
		
	}
    
}
