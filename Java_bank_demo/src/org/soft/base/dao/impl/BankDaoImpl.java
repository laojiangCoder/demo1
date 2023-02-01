package org.soft.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.soft.base.dao.BankDao;
import org.soft.base.model.Bank;
import org.soft.base.util.ExecuteDB;

public class BankDaoImpl extends ExecuteDB implements BankDao {

	@Override
	public List<Bank> getAllBanks() {
		List<Bank> bankList = new LinkedList<Bank>() ;
		String sql = "select * from bank " ;
		Object[] objects = null ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			while(rs.next()) {
				int bankId = rs.getInt("bankId") ;
				String bankName = rs.getString("bankName") ;
				Bank bank = new Bank(bankId,bankName) ;
				bankList.add(bank) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bankList;
	}

	@Override
	public boolean addBank(Bank bank) {
		String sql = "insert into bank(bankName) value(?)" ;
		Object[] objects = {bank.getBankName()} ;
		boolean result =  executeInsertUpdateDelete(sql, objects) ;
		return result ;
	}

	@Override
	public Bank getBankById(int bankId) {
		Bank bank = null ;
		String sql = "select * from bank where bankId=?" ;
		Object[] objects = {bankId} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			if ( rs.next() ) {
				int bId = rs.getInt("bankId") ;
				String bankName = rs.getString("bankName") ;
				bank = new Bank(bId,bankName) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bank ;
	}

	@Override
	public boolean updateBank(Bank bank) {
		String sql = "update bank set bankName=? where bankId=?" ;
		Object[] objects = {bank.getBankName(),bank.getBankId()} ;
		boolean result= executeInsertUpdateDelete(sql,objects) ;
		return result ;
	}

	@Override
	public boolean deleteBank(int bankId) {
		String sql = "delete from bank where bankId=?" ;
		Object[] objects = {bankId} ;
		boolean result = executeInsertUpdateDelete(sql,objects) ;
		return result ;
	}

	@Override
	public List<Bank> queryBanks(String bankName) {
		List<Bank> bankList = new LinkedList<Bank>() ;
		String sql = "select * from bank where bankName like concat('%',?,'%') " ;
		Object[] objects = {bankName} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			while(rs.next()) {
				int bankId = rs.getInt("bankId") ;
				String bName = rs.getString("bankName") ;
				Bank bank = new Bank(bankId,bName) ;
				bankList.add(bank) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bankList;
	}

	@Override
	public List<Bank> queryBanksPage(String bankName, int begin, int size) {
		List<Bank> bankList = new LinkedList<Bank>() ;
		String sql = "select * from bank where bankName like concat('%',?,'%') limit ?,? " ;
		Object[] objects = {bankName,begin,size} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			while(rs.next()) {
				int bankId = rs.getInt("bankId") ;
				String bName = rs.getString("bankName") ;
				Bank bank = new Bank(bankId,bName) ;
				bankList.add(bank) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bankList;
	}

	@Override
	public List<Bank> getAllBanksPage(int begin, int size) {
		List<Bank> bankList = new LinkedList<Bank>() ;
		String sql = "select * from bank limit ?,? " ;
		Object[] objects = {begin,size} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			while(rs.next()) {
				int bankId = rs.getInt("bankId") ;
				String bankName = rs.getString("bankName") ;
				Bank bank = new Bank(bankId,bankName) ;
				bankList.add(bank) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return bankList;
	}

	@Override
	public int getAllBanksCount() {
		int cnt = 0 ;
		String sql = "select count(bankId) from bank " ;
		Object[] objects = null ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			if ( rs.next() ) {
				cnt = rs.getInt(1) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs, null, null);
		}
		return cnt ;
	}

}
