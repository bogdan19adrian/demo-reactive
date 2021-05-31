package ro.go.bogdanenache.demoreactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class OrderDTO {

    String id;
    String customerId;
    List<String> products;
}
