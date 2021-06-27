package nl.rabobank.com.stockexchange.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name="ACCOUNT")
@Entity
@Getter
@Setter
public class Account implements Serializable {

    @Id
    @Column(name="ACCOUNTNUMBER",updatable = false)
    private String accountNumber;

    @Column(name="ACCOUNTNAME")
    private String accountName;

    @Column(name="BALANCE")
    private Double balance;

    @Column(name="OPENINGDATE")
    private LocalDate openingDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMERID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    private static final long serialVersionUID = -6380749575516426900L;
}
