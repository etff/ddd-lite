package com.app.dddlite.order;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery {

    @Id
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;


    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliverStatus status; // READY, COMP
}
