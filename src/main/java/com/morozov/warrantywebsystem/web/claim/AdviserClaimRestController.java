package com.morozov.warrantywebsystem.web.claim;

import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.model.ClaimStatus;
import com.morozov.warrantywebsystem.repository.ClaimRepository;
import com.morozov.warrantywebsystem.web.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = AdviserClaimRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdviserClaimRestController {
    static final String REST_URL = "/rest/adviser/claims";

    private ClaimRepository claimRepository;

    @PatchMapping(value = "/submitted/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatusToSubmitted(@PathVariable int id) {
        Claim claim = claimRepository.getExisted(id);
        claim.setAdviser(SecurityUtil.authUser());
        claim.setClaimSubmitDate(LocalDateTime.now());
        claim.setHistory(claim.getHistory() + "\nClaim submitted by" + SecurityUtil.authUser().getEmail() + " at " + LocalDate.now());
        claim.setStatus(ClaimStatus.SUBMITTED);
        claimRepository.save(claim);
    }

    @PatchMapping(value = "/approved/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatusToApproved(@PathVariable int id, @RequestParam String approveAmount) {
        Claim claim = claimRepository.getExisted(id);
        claim.setAdviser(SecurityUtil.authUser());
        claim.setApprovedAmount(Double.parseDouble(approveAmount));
        claim.setClaimApproveDate(LocalDateTime.now());
        claim.setHistory(claim.getHistory() + "\nClaim approved by" + SecurityUtil.authUser().getEmail() + " at " + LocalDate.now());
        claim.setStatus(ClaimStatus.APPROVED);
        claimRepository.save(claim);
    }

    @PatchMapping(value = "/denied/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatusToDenied(@PathVariable int id, @RequestParam String reason) {
        Claim claim = claimRepository.getExisted(id);
        claim.setAdviser(SecurityUtil.authUser());
        claim.setClaimApproveDate(LocalDateTime.now());
        claim.setHistory(claim.getHistory() + "\nClaim denied by" + SecurityUtil.authUser().getEmail() + " at " + LocalDate.now() + " by reason: " + reason);
        claim.setStatus(ClaimStatus.APPROVED);
        claimRepository.save(claim);
    }

    @PatchMapping(value = "/drafted/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatusToDraft(@PathVariable int id, @RequestParam String reason) {
        Claim claim = claimRepository.getExisted(id);
        claim.setAdviser(SecurityUtil.authUser());
        claim.setClaimQuestionDate(LocalDateTime.now());
        claim.setHistory(claim.getHistory() + "\nClaim turned back to draft by" + SecurityUtil.authUser().getEmail() + " at " + LocalDate.now() + " by reason: " + reason);
        claim.setStatus(ClaimStatus.APPROVED);
        claimRepository.save(claim);
    }
}