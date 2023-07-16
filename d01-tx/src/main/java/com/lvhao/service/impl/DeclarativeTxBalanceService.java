package com.lvhao.service.impl;

import com.lvhao.dao.BalanceMapper;
import com.lvhao.entity.Balance;
import com.lvhao.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeclarativeTxBalanceService implements BalanceService {

  private final BalanceMapper balanceMapper;

  @Override
  @Transactional
  public void transfer(int fromUserId, int toUserId, int amount) {
    Balance fromBalance = balanceMapper.selectByPrimaryKey(fromUserId);
    Balance toBalance = balanceMapper.selectByPrimaryKey(toUserId);

    fromBalance.setBalance(fromBalance.getBalance() - amount);
    toBalance.setBalance(toBalance.getBalance() + amount);

    doTransfer(fromBalance, toBalance);
  }

  @Transactional
  public void doTransfer(Balance from, Balance to) {
    balanceMapper.updateByPrimaryKey(from);
    balanceMapper.updateByPrimaryKey(to);
    throw new RuntimeException();
  }
}
