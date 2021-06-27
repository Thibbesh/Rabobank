package nl.rabobank.com.stockexchange.controllers;

import nl.rabobank.com.stockexchange.exception.ResourceNotFoundException;
import nl.rabobank.com.stockexchange.models.Account;
import nl.rabobank.com.stockexchange.models.Customer;
import nl.rabobank.com.stockexchange.repository.AccountRepository;
import nl.rabobank.com.stockexchange.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customers/{customerId}")
public class AccountController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     *
     * @param customerId
     * @param account
     * @return
     */
    @PostMapping(value = "/accounts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Account save(@PathVariable Integer customerId, @RequestBody Account account) {

        return customerRepository.findById(customerId).map(customer -> {
                                                                        account.setCustomer(customer);
            return accountRepository.save(account);

        }).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

    }

    /**
     *
     * @param customerId
     * @param pageable
     * @return
     */
    @GetMapping(value = "/accounts")
    public Page<Account> all (@PathVariable Integer customerId, Pageable pageable){
        return accountRepository.findByCustomerCustomerId(customerId, pageable);
    }

    /**
     *
     * @param customerId
     * @param accountNumber
     * @return
     */
    @DeleteMapping(value = "/accounts/{accountNumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer customerId,@PathVariable String accountNumber){

        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found");
        }

        return accountRepository.findById(accountNumber).map(account -> {
                                                                    accountRepository.delete(account);
                                                                    return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountNumber+"] can't be found"));

    }

    /**
     *
     * @param customerId
     * @param accountNumber
     * @param newAccount
     * @return
     */
    @PutMapping(value = "/accounts/{accountNumber}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer customerId,@PathVariable String accountNumber,@RequestBody Account newAccount){

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

        return accountRepository.findById(accountNumber).map(account ->{
                                                                        newAccount.setCustomer(customer);
                                                                        accountRepository.save(newAccount);
                                                                        return ResponseEntity.ok(newAccount);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountNumber+"] can't be found"));


    }
}
