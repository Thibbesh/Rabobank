package nl.rabobank.com.stockexchange.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name="CUSTOMER")
@Entity
@Getter
@Setter
public class Customer implements Serializable {

    private static final long serialVersionUID = -6759774343110776659L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMERID",updatable = false)
    private Integer customerId;

    @Column(name="NAME")
    private String customerName;

    @Column(name="DATEOFBIRTH" ,nullable=true)
    private LocalDate dateofBirth;

    @Column(name="PHONENUMBER")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMERID")
    private List<Account> accounts = new ArrayList<>();

}
