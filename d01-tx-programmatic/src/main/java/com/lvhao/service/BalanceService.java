package com.lvhao.service;

public interface BalanceService {
  void transfer(int fromUserId, int toUserId, int amount);
}
