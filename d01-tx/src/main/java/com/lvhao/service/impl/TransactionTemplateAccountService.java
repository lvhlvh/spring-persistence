package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import com.lvhao.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class TransactionTemplateAccountService implements AccountService {

  private final AccountMapper accountMapper;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void transfer(int fromId, int toId, int amount) {
    Account fromAccount = accountMapper.selectByPrimaryKey(fromId);
    Account toAccount = accountMapper.selectByPrimaryKey(toId);

    fromAccount.setBalance(fromAccount.getBalance() - amount);
    toAccount.setBalance(toAccount.getBalance() + amount);

    transactionTemplate.executeWithoutResult(
        transactionStatus -> {
          accountMapper.updateByPrimaryKey(fromAccount);
          accountMapper.updateByPrimaryKey(toAccount);
          throw new RuntimeException();
        });
  }
}
