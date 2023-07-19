package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import com.lvhao.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@RequiredArgsConstructor
public class TxManagerAccountService implements AccountService {

  private final AccountMapper accountMapper;
  private final PlatformTransactionManager txManager;

  @Override
  public void transfer(int fromId, int toId, int amount) {
    Account fromAccount = accountMapper.selectByPrimaryKey(fromId);
    Account toAccount = accountMapper.selectByPrimaryKey(toId);

    fromAccount.setBalance(fromAccount.getBalance() - amount);
    toAccount.setBalance(toAccount.getBalance() + amount);

    // 1. transactionDefinition代表了事务相关的配置, 等价于@Transactional注解里面那些配置,
    //    DefaultTransactionDefinition和@Transactional注解默认的配置相同
    DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
    // 2. 根据事务配置开启事务, 事务以TransactionStatus对象的形式存在
    TransactionStatus transaction = txManager.getTransaction(transactionDefinition);
    // 3. 执行事务逻辑
    try {
      accountMapper.updateByPrimaryKey(fromAccount);
      accountMapper.updateByPrimaryKey(toAccount);
//      int i = 1 / 0;
      txManager.commit(transaction);
    } catch (Exception e) {
      txManager.rollback(transaction);
      throw new RuntimeException(e);
    }
  }
}
