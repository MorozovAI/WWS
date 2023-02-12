package com.morozov.warrantywebsystem.service;

import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.model.ClaimStatus;
import com.morozov.warrantywebsystem.model.Role;
import com.morozov.warrantywebsystem.repository.ClaimRepository;
import com.morozov.warrantywebsystem.to.ClaimTo;
import com.morozov.warrantywebsystem.util.ClaimsUtil;
import com.morozov.warrantywebsystem.web.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.morozov.warrantywebsystem.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class ClaimService {

    @Value("${settings.period-for-update-new-claim}")
    private int daysForUpdates;
    private final ClaimRepository claimRepository;

    public Page<ClaimTo> getAll(int status, Pageable pageable) {
        if (SecurityUtil.hasRole(Role.ADVISER))
            return claimRepository.findClaimsByStatus(ClaimStatus.values()[status], pageable)
                    .map(ClaimsUtil::createClaimTo);
        else
            return claimRepository.findClaimsByStatusAndDealer(ClaimStatus.values()[status],
                            SecurityUtil.authUser().getDealer(), pageable)
                    .map(ClaimsUtil::createClaimTo);
    }

    public Optional<Claim> get(int id) {
        if (SecurityUtil.hasRole(Role.ADVISER)) {
            return claimRepository.findById(id);
        }
        return claimRepository.findClaimByIdAndDealer(id, SecurityUtil.authUser().getDealer());
    }

    public void delete(int id) {
        //checkNotFoundWithId(
        claimRepository.deleteExisted(id);//, id);
    }

    public void changeStatusToSubmit(Claim claim, String status) {
        if (SecurityUtil.hasRole(Role.ADVISER)) {
            claim.setStatus(ClaimStatus.valueOf(status));
            claimRepository.save(claim);
        }
    }

    public void update(Claim claim) {
        if (isUpdatable(claim)) claimRepository.save(claim);
        else throw new UnsupportedOperationException();
    }

    private boolean isUpdatable(Claim claim) {

        return claim.getStatus().equals(ClaimStatus.DRAFT) ||
                (claim.getClaimReceivedDate().plusDays(daysForUpdates).isAfter(LocalDateTime.now()) && claim.getStatus().equals(ClaimStatus.IN_REVIEW));
    }
}
