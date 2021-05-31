package ro.go.bogdanenache.demoreactive.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "demo")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    String id;
    String customerId;
    List<String> products;
}
