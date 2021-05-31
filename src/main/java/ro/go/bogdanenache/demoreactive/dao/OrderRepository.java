package ro.go.bogdanenache.demoreactive.dao;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<OrderEntity, String> {
}
