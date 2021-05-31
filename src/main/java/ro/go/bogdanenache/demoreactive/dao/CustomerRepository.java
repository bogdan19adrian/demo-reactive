package ro.go.bogdanenache.demoreactive.dao;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerEntity, String> {
}
