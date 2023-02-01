package org.soft.base.dao;

import org.soft.base.model.BankUser;

public interface BankUserDao {
	
	/**
	 * 银行用户注册
	 * @param bankUser 包括账号、密码、银行Id
	 * @return 注册成功返回true，否则返回false
	 */
	public boolean register(BankUser bankUser) ;

	/**
	 * 银行用户登录
	 * @param bankUser 包括账号、密码
	 * @return 登录成功返回银行用户详细信息，否则返回null
	 */
	public BankUser login(BankUser bankUser) ;
	
	/**
	 * 存款
	 * @param bankUser 包括用户Id,存款金额
	 * @return 存款成功返回true，否则返回false
	 */
	public  boolean deposit(BankUser bankUser) ;
	
	/**
	 * 根据银行用户Id获取银行用户对象
	 * @param bankUserId 银行用户Id
	 * @return 如果银行用户Id存在，返回对应的对象，否则返回false
	 */
	public  BankUser getBankUserById(int bankUserId) ; 
	
	/**
	 * 取款
	 * @param bankUser 包括用户Id,取款金额
	 * @return 取款成功返回true，否则返回false
	 */
	public boolean withdrawl(BankUser bankUser) ;
	
	 /**
	  * 转账
	  * @param outerNo  转出账号
	  * @param innerNo  转入账号
	  * @param transferMoney 转账金额
	  * @return
	  */
    public boolean transfer(String outerNo,String innerNo,double transferMoney) ;
    
    /**
     * 根据银行账号获取银行用户对象
     * @param bankUserNo  银行账号
     * @return 如果银行账号存在，返回银行用户对象，否则返回null
     */
    public BankUser getBankUserByNo(String bankUserNo) ;
    
    /**
     * 减少余额
     * @param bankUser 用户对象
     * @param money 减少金额
     * @return 减少成功返回true，否则返回false
     */
    public boolean decrease(BankUser bankUser,double money) ;
	
    /**
     * 增加余额
     * @param bankUser 用户对象
     * @param money 减少金额
     * @return 增加成功返回true，否则返回false
     */
    public boolean increase(BankUser bankUser,double money) ;
    
    /**
     * 修改银行用户信息
     * @param bankUser 银行用户对象
     * @return 修改成功返回true，否则返回false
     */
    public  boolean updateBankUser(BankUser bankUser) ;
    
    /**
     * 修改密码
     * @param bankUser 包括新的银行用户Id、新密码
     * @return 修改成功返回true，否则返回false
     */
    public boolean changePassword(BankUser bankUser) ;
}
