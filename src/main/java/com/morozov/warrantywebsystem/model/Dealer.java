package com.morozov.warrantywebsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "dealers")
@Getter
@Setter
@NoArgsConstructor
public class Dealer {

    @Id
    @GeneratedValue(strategy=SEQUENCE)
    @Nullable
    private Integer id;

    @Column(name = "dealer_name", unique = true)
    @NotBlank
    @Size(min = 5, max = 50)
    private String dealerName;

    @Column(name = "dealer_code", nullable = false, unique = true)
    @PrimaryKeyJoinColumn
    private Integer dealerCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;

    public Dealer(String dealerName, Integer dealerCode) {
        this.dealerName = dealerName;
        this.dealerCode = dealerCode;
    }

    @Override
    public String toString() {
        return "Dealer " +
                dealerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dealer dealer = (Dealer) o;
        return dealerCode != null && Objects.equals(dealerCode, dealer.dealerCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
