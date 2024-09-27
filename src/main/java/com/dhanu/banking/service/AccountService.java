package com.dhanu.banking.service;

import java.util.List;

import com.dhanu.banking.dto.AccountDto;


public interface AccountService {
AccountDto createAccount(AccountDto accountDto);
AccountDto getAccountById(long id);
AccountDto deposit(Long id,double amount);
AccountDto withdraw(Long id,double amount);
List<AccountDto> getAllAccounts();
void deleteAccount(Long id);
}
