package com.lvhao.service.impl;

import com.lvhao.dao.BalanceMapper;
import com.lvhao.entity.Balance;
import java.awt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequiredPropagationBalanceService {

  private final BalanceMapper balanceMapper;
  private final RequiredPropagationBalanceService2 requiredPropagationBalanceService2;

  @Transactional
  public void insertOne() {
    balanceMapper.insert(new Balance(100, "Bob", 100));
    //    insertAnotherOne();
    try {
      // 如果把insertAnotherOne写在该类里面, 然后调用, 则是直接调用insertAnotherOne, 不会经过proxy, 没有事务的传播特性
      requiredPropagationBalanceService2.insertAnotherOne();
    } catch (Exception e) {
      // even enclosing method handles exception, the tx still will be rollback
      e.printStackTrace();
    }
  }
}

@Service
@RequiredArgsConstructor
class RequiredPropagationBalanceService2 {

  private final BalanceMapper balanceMapper;

  @Transactional
  public void insertAnotherOne() {
    balanceMapper.insert(new Balance(101, "Mike", 100));
    // RuntimeException及其子类 & Error及其子类 会导致回滚
    //    throw new AWTError("");
    throw new RuntimeException();
  }
}
