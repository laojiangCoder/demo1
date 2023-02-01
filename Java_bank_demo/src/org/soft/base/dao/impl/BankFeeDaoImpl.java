package org.soft.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.soft.base.dao.BankFeeDao;
import org.soft.base.model.BankFee;
import org.soft.base.model.BankSumFee;
import org.soft.base.util.ExecuteDB;

public class BankFeeDaoImpl extends ExecuteDB implements BankFeeDao {

	@Override
	public boolean addBankFee(BankFee bankFee) {
        String sql = "insert into bankFee(bankId,fee,feeTime) value(?,?,now()) " ;
        Object[] objects = {bankFee.getBankId(),bankFee.getFee()} ;
        boolean result = executeInsertUpdateDelete(sql, objects);
		return result;
	}

	@Override
	public List<BankSumFee> getBankSumFees() {
		List<BankSumFee> bankSumFeeList = new LinkedList<BankSumFee>() ;
		String sql = "select bank.bankName,bgp.sumFee from  "
				+ "(select bankId,sum(fee) as sumFee from bankFee group by bankId) bgp "
				+ "left join bank on bgp.bankId=bank.bankId"  ;
		Object[] objects = null ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			while( rs.next()) {
				String bankName = rs.getString("bankName") ;
				double bankSumf= rs.getDouble("sumFee") ;
				BankSumFee bankSumFee = new BankSumFee(bankName,bankSumf) ;
				bankSumFeeList.add(bankSumFee) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bankSumFeeList;
	}

}
