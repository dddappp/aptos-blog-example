package org.test.aptosblogdemo.aptos.contract.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.test.aptosblogdemo.aptos.contract.AptosAccount;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional(readOnly = true)
public interface AptosAccountRepository extends JpaRepository<AptosAccount, String> {

}
