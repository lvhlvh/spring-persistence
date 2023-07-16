package com.lvhao.service.impl;

import com.lvhao.dao.BalanceMapper;
import com.lvhao.entity.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NestedPropagationBalanceService {
  private final BalanceMapper balanceMapper;
  private final NestedPropagationBalanceService2 nestedPropagationBalanceService2;
  private final NestedPropagationBalanceService3 nestedPropagationBalanceService3;

  @Transactional
  public void insertOne() {
    balanceMapper.insert(new Balance(100, "Bob", 100));
    //    insertAnotherOne();
    try {
      // 如果把insertAnotherOne写在该类里面, 然后调用, 则是直接调用insertAnotherOne, 不会经过proxy, 没有事务的传播特性
      nestedPropagationBalanceService2.insertAnotherOne();
    } catch (Exception e) {
      // even enclosing method handles exception, the tx still will be rollback
      e.printStackTrace();
    }

    try {
      nestedPropagationBalanceService3.insertAnotherMoreOne();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

@Service
@RequiredArgsConstructor
class NestedPropagationBalanceService2 {
  private final BalanceMapper balanceMapper;

  @Transactional(propagation = Propagation.NESTED)
  public void insertAnotherOne() {
    balanceMapper.insert(new Balance(101, "Mike", 100));
    throw new RuntimeException();
  }
}

@Service
@RequiredArgsConstructor
class NestedPropagationBalanceService3 {
  private final BalanceMapper balanceMapper;

  @Transactional(propagation = Propagation.NESTED)
  public void insertAnotherMoreOne() {
    balanceMapper.insert(new Balance(102, "Mark", 100));
  }
}
