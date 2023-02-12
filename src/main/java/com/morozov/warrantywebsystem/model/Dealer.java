package com.morozov.warrantywebsystem.model;

import com.morozov.warrantywebsystem.HasId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "dealers")
@Getter
@Setter
@NoArgsConstructor
public class Dealer extends BaseEntity implements HasId {


    @Column(name = "dealer_name", unique = true)
    @NotBlank
    @Size(min = 5, max = 50)
    private String dealerName;

    @Column(name = "dealer_code", nullable = false, unique = true)
    private Integer dealerCode;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dealer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;

    public Dealer(Integer id, String dealerName, Integer dealerCode) {
        super(id);
        this.dealerName = dealerName;
        this.dealerCode = dealerCode;
    }

    @Override
    public String toString() {
        return "" + dealerCode;
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
