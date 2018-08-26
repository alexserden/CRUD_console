package com.alexander.repository;

import com.alexander.model.Account;

import java.io.IOException;
import java.util.List;

public interface AccountRepository extends GenericRepository<Account,Long>{
    Account create(Account account);

    void delete(Long id);

    Account getById(Long id) throws IOException;

    List<Account> getAll() throws IOException;

    void clearAll();
}
