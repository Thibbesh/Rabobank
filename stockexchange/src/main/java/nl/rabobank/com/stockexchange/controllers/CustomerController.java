package nl.rabobank.com.stockexchange.controllers;

import nl.rabobank.com.stockexchange.exception.ResourceNotFoundException;
import nl.rabobank.com.stockexchange.models.Customer;
import nl.rabobank.com.stockexchange.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping()
    public Page<Customer> allCustomers (Pageable pageable){
        return customerRepository.findAll(pageable);

    }

    @GetMapping(value = "/{customerId}")
    public Customer findByCustomerId (@PathVariable Integer customerId){
        return customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId){

        return customerRepository.findById(customerId).map(customer -> {
                                                                        customerRepository.delete(customer);
                                                                        return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId,@RequestBody Customer newCustomer){

        return customerRepository.findById(customerId).map(customer -> {
                                                                        customer.setCustomerName(newCustomer.getCustomerName());
                                                                        customer.setDateofBirth(newCustomer.getDateofBirth());
                                                                        customer.setPhoneNumber(newCustomer.getPhoneNumber());
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

    }

}
