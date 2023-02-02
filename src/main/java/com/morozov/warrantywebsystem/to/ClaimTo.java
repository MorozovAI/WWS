package com.morozov.warrantywebsystem.to;

import com.morozov.warrantywebsystem.model.ClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor
public class ClaimTo {
    Integer id;
    Integer dealer;
    String dealerRO;
    String esn;
    LocalDate failureDate;
    Double claimAmount;
    ClaimStatus status;
}
