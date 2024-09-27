package com.dhanu.banking.service.impl;
import java.util.stream.Collectors;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhanu.banking.dto.AccountDto;
import com.dhanu.banking.entity.Account;
import com.dhanu.banking.mapper.AccountMapper;
import com.dhanu.banking.repository.AccountRepository;
import com.dhanu.banking.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{

	private AccountRepository accountRepository;

   public AccountServiceImpl(AccountRepository accountRepository)
     {
	this.accountRepository=accountRepository;
     }

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}

	@Override
	public AccountDto getAccountById(long id) {
		
		Account account=accountRepository
				        .findById(id)
				        .orElseThrow(()->new RuntimeException("Account does not exists"));
			return AccountMapper.mapToAccountDto(account);	        
				        
		
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		
		Account account=accountRepository
		        .findById(id)
		        .orElseThrow(()->new RuntimeException("Account does not exists"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository
		        .findById(id)
		        .orElseThrow(()->new RuntimeException("Account does not exists"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient amount");
		}
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);

	}

	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
	
		         accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exists"));
	    
		accountRepository.deleteById(id);
	}
}


