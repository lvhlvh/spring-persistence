package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequiredPropagationAccountService {

  private final AccountMapper accountMapper;
  private final RequiredPropagationAccountService2 requiredPropagationAccountService2;

  @Transactional
  public void insertOne() {
    accountMapper.insert(new Account(100, 100));
    //    insertAnotherOne();
    try {
      // 如果把insertAnotherOne写在该类里面, 然后调用, 则是直接调用insertAnotherOne, 不会经过proxy, 没有事务的传播特性
      requiredPropagationAccountService2.insertAnotherOne();
    } catch (Exception e) {
      // even enclosing method handles exception, the tx still will be rollback
      e.printStackTrace();
    }
  }
}

@Service
@RequiredArgsConstructor
class RequiredPropagationAccountService2 {

  private final AccountMapper accountMapper;

  @Transactional
  public void insertAnotherOne() {
    accountMapper.insert(new Account(101, 100));
    // RuntimeException及其子类 & Error及其子类 会导致回滚
    //    throw new AWTError("");
    throw new RuntimeException();
  }
}
