package ro.go.bogdanenache.demoreactive.controller;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.go.bogdanenache.demoreactive.dto.CustomerDTO;
import ro.go.bogdanenache.demoreactive.service.CustomerService;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/create")
    public Publisher<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping(value = "/get/{id}")
    public Publisher<CustomerDTO> getCustomer(@PathVariable String id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/getAll")
    public Publisher<CustomerDTO> getAllCustomers() {
        return customerService.findAllCustomersWithBackpressure();
    }
}
