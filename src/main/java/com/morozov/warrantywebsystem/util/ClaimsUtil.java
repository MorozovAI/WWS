package com.morozov.warrantywebsystem.util;

import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.to.ClaimTo;

import java.util.List;
import java.util.stream.Collectors;

public class ClaimsUtil {

    public static List<ClaimTo> getClaimTos(List<Claim> claims) {
        return claims.stream().map(ClaimsUtil::createClaimTo).collect(Collectors.toList());
    }

    public static ClaimTo createClaimTo(Claim claim) {
        return new ClaimTo(claim.getId(), claim.getDealer().getDealerCode(), claim.getDealerRO(), claim.getEsn(), claim.getFailureDate(), claim.getClaimAmount(), claim.getApprovedAmount(), claim.getStatus());
    }
}
