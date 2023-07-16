package com.lvhao.service.impl;

import com.lvhao.dao.BalanceMapper;
import com.lvhao.entity.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequiresNewPropagationBalanceService {

  private final BalanceMapper balanceMapper;
  private final RequiresNewPropagationBalanceService2 requiresNewPropagationBalanceService2;

  @Transactional
  public void insertOne() {
    balanceMapper.insert(new Balance(100, "Bob", 100));
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

  private final BalanceMapper balanceMapper;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void insertAnotherOne() {
    balanceMapper.insert(new Balance(101, "Mike", 100));
    throw new RuntimeException();
  }
}
