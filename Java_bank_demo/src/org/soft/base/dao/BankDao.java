package org.soft.base.dao;

import java.util.List;

import org.soft.base.model.Bank;

public interface BankDao {
	
	public List<Bank> getAllBanks() ;
	
	public int getAllBanksCount() ;
	
	public List<Bank> getAllBanksPage(int begin,int size) ;
	
	public boolean addBank(Bank bank) ;
	
	public Bank getBankById(int bankId ) ;
	
	public boolean updateBank(Bank bank) ;
	
	public boolean deleteBank(int bankId) ;
	
	public  List<Bank> queryBanks(String bankName) ;
	
	public  List<Bank> queryBanksPage(String bankName,int begin,int size) ;

}
