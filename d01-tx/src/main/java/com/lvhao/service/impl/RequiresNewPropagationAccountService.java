package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequiresNewPropagationAccountService {

  private final AccountMapper accountMapper;
  private final RequiresNewPropagationBalanceService2 requiresNewPropagationBalanceService2;

  @Transactional
  public void insertOne() {
    accountMapper.insert(new Account(100, 100));
    try {
      requiresNewPropagationBalanceService2.insertAnotherOne();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

@Service
@RequiredArgsConstructor
class RequiresNewPropagationBalanceService2 {

  private final AccountMapper accountMapper;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void insertAnotherOne() {
    accountMapper.insert(new Account(101, 100));
    throw new RuntimeException();
  }
}
