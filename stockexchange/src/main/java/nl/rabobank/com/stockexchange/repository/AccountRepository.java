package nl.rabobank.com.stockexchange.repository;

import nl.rabobank.com.stockexchange.models.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Page<Account> findByCustomerCustomerId(Integer customerId, Pageable pageable);
}
