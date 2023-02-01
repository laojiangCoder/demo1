package org.soft.base.dao;

import java.util.List;

import org.soft.base.model.BankFee;
import org.soft.base.model.BankSumFee;

public interface BankFeeDao {
	
	/**
	 * 新增银行的手续费
	 * @param bankFee 银行手续费
	 * @return 新增成功返回true，否则返回false
	 */
	public boolean addBankFee(BankFee bankFee) ;
	
	/**
	 * 获取所有银行的手续费合计
	 * @return  所有银行的手续费合计列表
	 */
	public List<BankSumFee> getBankSumFees() ;

}
