package com.lvhao.service.impl;

import com.lvhao.dao.BalanceMapper;
import com.lvhao.entity.Balance;
import com.lvhao.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class TransactionTemplateBalanceService implements BalanceService {

  private final BalanceMapper balanceMapper;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void transfer(int fromUserId, int toUserId, int amount) {
    Balance fromBalance = balanceMapper.selectByPrimaryKey(fromUserId);
    Balance toBalance = balanceMapper.selectByPrimaryKey(toUserId);

    fromBalance.setBalance(fromBalance.getBalance() - amount);
    toBalance.setBalance(toBalance.getBalance() + amount);

    transactionTemplate.executeWithoutResult(
        transactionStatus -> {
          balanceMapper.updateByPrimaryKey(fromBalance);
          balanceMapper.updateByPrimaryKey(toBalance);
          throw new RuntimeException();
        });
  }
}
