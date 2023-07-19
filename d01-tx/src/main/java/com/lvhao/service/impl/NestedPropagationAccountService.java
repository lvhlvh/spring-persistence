package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NestedPropagationAccountService {
  private final AccountMapper accountMapper;
  private final NestedPropagationAccountService2 nestedPropagationAccountService2;
  private final NestedPropagationAccountService3 nestedPropagationAccountService3;

  @Transactional
  public void insertOne() {
    accountMapper.insert(new Account(100, 100));
    //    insertAnotherOne();
    try {
      // 如果把insertAnotherOne写在该类里面, 然后调用, 则是直接调用insertAnotherOne, 不会经过proxy, 没有事务的传播特性
      nestedPropagationAccountService2.insertAnotherOne();
    } catch (Exception e) {
      // even enclosing method handles exception, the tx still will be rollback
      e.printStackTrace();
    }

    try {
      nestedPropagationAccountService3.insertAnotherMoreOne();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

@Service
@RequiredArgsConstructor
class NestedPropagationAccountService2 {
  private final AccountMapper accountMapper;

  @Transactional(propagation = Propagation.NESTED)
  public void insertAnotherOne() {
    accountMapper.insert(new Account(101, 100));
    throw new RuntimeException();
  }
}

@Service
@RequiredArgsConstructor
class NestedPropagationAccountService3 {
  private final AccountMapper accountMapper;

  @Transactional(propagation = Propagation.NESTED)
  public void insertAnotherMoreOne() {
    accountMapper.insert(new Account(102, 100));
  }
}
