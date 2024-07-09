package com.trianee.JPA.embebded;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @EmbeddedId
    private OrderId id;

    @Embedded
    private  Address address;

    @Column(name = "orderinfo", nullable = false)
    private String orderInfo;

    @Column(name = "anotherfield", nullable = false)
    private String anotherField;
}
