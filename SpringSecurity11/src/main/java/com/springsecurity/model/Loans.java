package com.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Long loanNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "start_dt")
    private Date startDt;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private Integer totalLoan;

    @Column(name = "amount_paid")
    private Integer amountPaid;

    @Column(name = "outstanding_amount")
    private Integer outstandingAmount;

    @Column(name = "create_dt")
    private Date createDt;

}
