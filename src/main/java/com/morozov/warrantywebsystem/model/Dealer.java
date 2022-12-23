package com.morozov.warrantywebsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "dealers")
@Data
@NoArgsConstructor
public class Dealer {

    @Column(name = "dealer_name", unique = true)
    @NotBlank
    @Size(min = 5, max = 50)
    private String dealerName;

    @Id
    @Column(name = "dealer_code", nullable = false, unique = true)
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
                +dealerCode;
    }
}
