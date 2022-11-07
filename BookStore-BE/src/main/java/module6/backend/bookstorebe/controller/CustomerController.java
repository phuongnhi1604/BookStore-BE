package module6.backend.bookstorebe.controller;

import module6.backend.bookstorebe.entity.customer.Customer;
import module6.backend.bookstorebe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        Customer customer = customerService.findCustomerById(id);
        if (customer==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
}
