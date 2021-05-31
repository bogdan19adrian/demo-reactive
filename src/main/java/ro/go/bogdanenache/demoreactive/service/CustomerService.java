package ro.go.bogdanenache.demoreactive.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ro.go.bogdanenache.demoreactive.dao.CustomerEntity;
import ro.go.bogdanenache.demoreactive.dao.CustomerRepository;
import ro.go.bogdanenache.demoreactive.dto.CustomerDTO;
import ro.go.bogdanenache.demoreactive.exception.NotFoundRuntimeException;
import ro.go.bogdanenache.demoreactive.exception.ServiceException;

import java.time.Duration;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Used to create element
     * convert to dto
     * return converted element
     */
    public Publisher<CustomerDTO> createCustomer(CustomerDTO customerDTO) {

        return Mono.just(customerDTO)
                .map(cstDto -> new CustomerEntity(null, cstDto.getCustomerName()))
                .flatMap(customerRepository::save)
                .map(ce -> new CustomerDTO(ce.getCustomerId(), ce.getCustomerName()));

    }

    /**
     * Used to find an element by id
     * convert to dto
     * if the id does not match to anything in db return error instead
     */
    public Publisher<CustomerDTO> findCustomerById(String id) {

        return customerRepository.findById(id)
                .map(ce -> new CustomerDTO(ce.getCustomerId(), ce.getCustomerName()))
                .switchIfEmpty(
                        Mono.error(new NotFoundRuntimeException("Customer not found")));
    }

    /**
     * Used to exemplify subscription to a flux that emits periodically
     * apply error handling and retry
     * <p>
     * .retryWhen(Retry.backoff(3, Duration.ofSeconds(5))
     * .filter(throwable -> throwable instanceof ServiceException));
     * <p>
     * .onErrorResume(error -> {
     * if (error instanceof ServiceException) {
     * log.debug("we do not like olga");
     * }
     * <p>
     * return Flux.error(error);
     * });
     */
    public Publisher<CustomerDTO> findAllCustomersWithBackpressure() {

        return customerRepository
                .findAll()
                .delayElements(Duration.ofSeconds(5))
                .log("DEBUG")
                .map(this::getBuild);

    }

    /**
     * Simulate an error in processing
     */
    private CustomerDTO getBuild(CustomerEntity entity) {
        if (entity.getCustomerName().equals("Olga")) {
            throw new ServiceException("Exception occurred in processing the flux of customers");
        }
        return CustomerDTO.builder().customerId(entity.getCustomerId()).customerName(entity.getCustomerName()).build();
    }
}
