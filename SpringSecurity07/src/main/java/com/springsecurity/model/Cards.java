package com.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Cards {

    @Id
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private Integer totalLimit;

    @Column(name = "amount_used")
    private Integer amountUsed;

    @Column(name = "available_amount")
    private Integer availableAmount;

    @Column(name = "create_dt")
    private Date createDt;

}
