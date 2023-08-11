package org.test.aptosblogdemo.aptos.contract.repository;

import org.test.aptosblogdemo.aptos.contract.AptosAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AptosAccountRepository extends JpaRepository<AptosAccount, String> {
    
}
