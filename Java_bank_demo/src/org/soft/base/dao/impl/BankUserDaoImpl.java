package org.soft.base.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.soft.base.dao.BankFeeDao;
import org.soft.base.dao.BankUserDao;
import org.soft.base.model.BankFee;
import org.soft.base.model.BankUser;
import org.soft.base.util.ExecuteDB;

public class BankUserDaoImpl extends ExecuteDB implements BankUserDao {

	@Override
	public boolean register(BankUser bankUser) {
		String sql = "insert into bankUser(bankUserNo,bankUserPass,bankBalance,bankId) value(?,?,0,?)" ;
		Object[] objects = {bankUser.getBankUserNo(),bankUser.getBankUserPass(),bankUser.getBankId()} ;
		boolean result = executeInsertUpdateDelete(sql, objects) ;
		return result ;
	}

	@Override
	public BankUser login(BankUser bankUser) {
		BankUser bu = null ;
		String sql = "select * from bankUser where bankUserNo=? and bankUserPass=?" ;
		Object[] objects = {bankUser.getBankUserNo(),bankUser.getBankUserPass()} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			if ( rs.next() ) {
				   int bankUserId = rs.getInt("bankUserId")  ;
				   String  bankUserNo = rs.getString("bankUserNo")  ;
				   String  bankUserPass = rs.getString("bankUserPass") ;
				   String  bankUserName = rs.getString("bankUserName")  ;
				   double  bankBalance = rs.getDouble("bankBalance")  ;
				   int  bankId = rs.getInt("bankId")  ;
				   bu = new BankUser(bankUserId, bankUserNo, bankUserPass, bankUserName, bankBalance, bankId) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bu;
	}

	@Override
	public boolean deposit(BankUser bankUser) {
       String sql = "update bankUser set bankBalance=bankBalance+? where bankUserId=?" ;
       Object[] objects = {bankUser.getBankBalance(),bankUser.getBankUserId()} ;
       boolean result = executeInsertUpdateDelete(sql, objects) ;
		return result ;
	}

	@Override
	public BankUser getBankUserById(int bankUserId) {
		BankUser bu = null ;
		String sql = "select * from bankUser where bankUserId=?" ;
		Object[] objects = {bankUserId} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			if ( rs.next() ) {
				   int buId = rs.getInt("bankUserId")  ;
				   String  bankUserNo = rs.getString("bankUserNo")  ;
				   String  bankUserPass = rs.getString("bankUserPass") ;
				   String  bankUserName = rs.getString("bankUserName")  ;
				   double  bankBalance = rs.getDouble("bankBalance")  ;
				   int  bankId = rs.getInt("bankId")  ;
				   bu = new BankUser(buId, bankUserNo, bankUserPass, bankUserName, bankBalance, bankId) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bu;
	}

	@Override
	public boolean withdrawl(BankUser bankUser) {
		double money = bankUser.getBankBalance() ; //取款金额
		BankUser bu  =  getBankUserById(bankUser.getBankUserId()) ;
		double balance = bu.getBankBalance() ; //当前余额
		if ( balance < money ) {
			//余额小于取款金额，不能取款，返回false
			return false;
		} else {
			String sql = "update bankUser set bankBalance=bankBalance-? where bankUserId=?" ;
			Object[] objects = {money,bankUser.getBankUserId()} ;
			boolean result = executeInsertUpdateDelete(sql, objects) ;
			return result ;
		}
		
	}

	@Override
	public boolean transfer(String outerNo, String innerNo, double transferMoney) {
		Connection con = getConnection() ;
		try {
			con.setAutoCommit(false);//启动事务
			//1  转出用户金额减少
			BankUser outerUser = getBankUserByNo(outerNo) ; //转出用户
		    BankUser innerUser = getBankUserByNo(innerNo) ; //转入用户
		    boolean result1 = decrease(outerUser, transferMoney) ;
		    if ( result1 ) {
		    	// 转出用户金额减少成功
		    	System.out.println("转出用户金额成功");
		    } else {
		    	// 转出用户金额减少失败
		    	System.out.println("转出用户金额失败");
		    	con.setAutoCommit(true);
		    	return false;
		    }
		    
			//2  计算手续费
			//2-1 获取转出用户和转入用户的银行Id，比较是否相等
		    double fee = 0.0 ; //手续费
		    int outerBankId = outerUser.getBankId() ;
		    int innerBankId = innerUser.getBankId() ;
		    if ( outerBankId != innerBankId ) {
		    	//2-2 计算手续费  
		    	fee = transferMoney * 0.5 / 100 ;
		    	if ( fee < 2 ) {
		    		fee = 2 ;
		    	} else if ( fee > 50 ) {
		    		fee = 50 ;
		    	}
			
		    	if ( fee > 0 ) {
		    		//2-3 如果存在手续费，转出用户减少手续费
		    		boolean result2 = decrease(outerUser,fee) ;
		    		if ( result2 ) {
		    			//转出用户减少手续费成功
		    			System.out.println("转出用户减少手续费成功") ;
		    		} else {
		    			//转出用户减少手续费失败
		    			System.out.println("转出用户减少手续费失败") ;
		    			con.rollback();
		    			con.setAutoCommit(true);
		    		    return false ;
		    		}
		    		//3 增加银行手续费
		    		BankFee bankFee = new BankFee() ;
		    		bankFee.setBankId(outerBankId);
		    		bankFee.setFee(fee); 
		    		BankFeeDao  bankFeeDao = new BankFeeDaoImpl() ;
		    		boolean result3 = bankFeeDao.addBankFee(bankFee) ;
		    		if ( result3  ) {
		    			// 增加银行手续费成功
		    			System.out.println("增加银行手续费成功") ;
		    		} else {
		    			//增加银行手续费失败
		    			System.out.println("增加银行手续费失败") ;
		    			con.rollback();
		    			con.setAutoCommit(true);
		    			return false ;
		    		}
		    		
		    	}
				
		    }
			
			//4 查询转出用户的余额
		    BankUser newOuterUser = getBankUserById(outerUser.getBankUserId()) ;
		    double outerBankBalance = newOuterUser.getBankBalance() ;
		 // 判断余额大于等于0，继续。否则回滚事务，结束
		    if ( outerBankBalance >= 0 ) {
		    	//转出用户的余额足够
		    	System.out.println("转出用户的余额足够，可以转账");
		    } else {
		    	//转出用户的余额不足
		    	System.out.println("转出用户的余额不足，不能转账");
		    	con.rollback();
		    	con.setAutoCommit(true);
		    	return false ;
		    }
			
			//5 转入用户余额增加
		    boolean result4 = increase(innerUser,transferMoney) ;
		    if ( result4 ) {
		    	//转入用户余额增加成功
		        System.out.println("转入用户余额增加成功") ;
		        con.commit();
		        con.setAutoCommit(true);
		        return true ;
		    } else {
		    	//转入用户余额增加失败
		    	 System.out.println("转入用户余额增加失败") ;
		    	 con.rollback();
		    	 con.setAutoCommit(true);
		    	 return false ;
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
		return false;
	}

	@Override
	public BankUser getBankUserByNo(String bankUserNo) {
		BankUser bu = null ;
		String sql = "select * from bankUser where bankUserNo=?" ;
		Object[] objects = {bankUserNo} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			if ( rs.next() ) {
				   int bankUserId = rs.getInt("bankUserId")  ;
				   String  buNo = rs.getString("bankUserNo")  ;
				   String  bankUserPass = rs.getString("bankUserPass") ;
				   String  bankUserName = rs.getString("bankUserName")  ;
				   double  bankBalance = rs.getDouble("bankBalance")  ;
				   int  bankId = rs.getInt("bankId")  ;
				   bu = new BankUser(bankUserId, buNo, bankUserPass, bankUserName, bankBalance, bankId) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bu;
	}

	@Override
	public boolean decrease(BankUser bankUser, double money) {
		String sql = "update bankUser set bankBalance=bankBalance-? where bankUserId=?" ;
		Object[] objects = {money,bankUser.getBankUserId()} ;
		boolean result = executeInsertUpdateDelete(sql, objects) ;
		return result ;
	}

	@Override
	public boolean increase(BankUser bankUser, double money) {
		String sql = "update bankUser set bankBalance=bankBalance+? where bankUserId=?" ;
		Object[] objects = {money,bankUser.getBankUserId()} ;
		boolean result = executeInsertUpdateDelete(sql, objects) ; 
		return result ;
	}

	@Override
	public boolean updateBankUser(BankUser bankUser) {
		String sql = "update bankUser set bankUserNo=?,bankUserName=?,bankId=? where bankUserId=?" ;
		Object[] objects = {bankUser.getBankUserNo(),bankUser.getBankUserName(),bankUser.getBankId(),
				            bankUser.getBankUserId()} ;
		boolean result = executeInsertUpdateDelete(sql, objects) ;
		return result ;
	}

	@Override
	public boolean changePassword(BankUser bankUser) {
        String sql = "update bankUser set bankUserPass=? where bankUserId=?" ;
        Object[] objects = {bankUser.getBankUserPass(),bankUser.getBankUserId()} ;
        boolean result = executeInsertUpdateDelete(sql,objects) ;
		return result ;
	}

}
