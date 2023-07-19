package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import com.lvhao.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeclarativeTxAccountService implements AccountService {

  private final AccountMapper accountMapper;

  @Override
  @Transactional
  public void transfer(int fromId, int toId, int amount) {
    Account fromAccount = accountMapper.selectByPrimaryKey(fromId);
    Account toAccount = accountMapper.selectByPrimaryKey(toId);

    fromAccount.setBalance(fromAccount.getBalance() - amount);
    toAccount.setBalance(toAccount.getBalance() + amount);

    doTransfer(fromAccount, toAccount);
  }

  public void doTransfer(Account from, Account to) {
    accountMapper.updateByPrimaryKey(from);
    accountMapper.updateByPrimaryKey(to);
    throw new RuntimeException();
  }
}
