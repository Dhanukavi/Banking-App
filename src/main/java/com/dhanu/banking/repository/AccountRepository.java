package com.dhanu.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhanu.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
