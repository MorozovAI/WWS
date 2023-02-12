package com.morozov.warrantywebsystem.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.morozov.warrantywebsystem.HasId;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "claims")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Claim extends BaseEntity implements HasId {

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonAlias("dealer.")
    private Dealer dealer;

    @Column(name = "oem", nullable = false)
   // @NotBlank
    @Size(max = 25)
    private String oem;

    @Column(name = "dealer_RO", nullable = false)
    @NotBlank
    private String dealerRO;

    @Column(name = "esn", nullable = false)
    @NotBlank
    private String esn;

    @Column(name = "mileage", nullable = false)
    //@NotNull
    private Integer mileage;

    @Column(name = "mileage_type", nullable = false)
   // @NotNull
    private MileageType mileageType;

    @Column(name = "application_type")
    //@NotNull
    private ApplicationType applicationType;

    @Column(name = "engine_model")
    //@NotBlank
    private String engineModel;

    @Column(name = "failure_date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate failureDate;

    @Column(name = "receive_date")
    private LocalDateTime claimReceivedDate;  //date of first claim receiving or after question from dealer user

    @Column(name = "question_date")
    private LocalDateTime claimQuestionDate; //date of question from adviser to dealer user

    @Column(name = "submit_date")
    private LocalDateTime claimSubmitDate;    //date of submit to main warranty system

    @Column(name = "approve_date")
    private LocalDateTime claimApproveDate;  //date of approving from main warranty system

    @ElementCollection
    @CollectionTable(name = "claim_parts",
            joinColumns = {@JoinColumn(name = "claim_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "part_id")
    @Column(name = "qty")
    @ToString.Exclude
    @Nullable
    @JsonIgnore
    private Map<Part, Integer> parts;

    @Column(name = "claim_amount")
    private Double claimAmount;

    @Column(name = "approve_amount")
    private Double approvedAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    @ToString.Exclude
    @Nullable
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adviser")
    @ToString.Exclude
    @Nullable
    private User adviser;

    @Column(name = "narrative")
    @Size(max = 2000)
    private String narrative;

    @Column(name = "history")
    @Size(max = 2000)
    private String history="";

    @Column(name = "status")
    private ClaimStatus status;

    public Claim(Integer id, String dealerRO, String esn, LocalDate failureDate) {
        super(id);
        this.dealerRO = dealerRO;
        this.esn = esn;
        this.failureDate = failureDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Claim claim = (Claim) o;
        return dealerRO.equals(claim.dealerRO) && esn.equals(claim.esn) && failureDate.equals(claim.failureDate);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}