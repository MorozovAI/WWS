package com.morozov.warrantywebsystem.web.claim;

import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.repository.ClaimRepository;
import com.morozov.warrantywebsystem.service.ClaimService;
import com.morozov.warrantywebsystem.to.ClaimTo;
import com.morozov.warrantywebsystem.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.morozov.warrantywebsystem.util.validation.ValidationUtil.assureIdConsistent;
import static com.morozov.warrantywebsystem.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ClaimRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ClaimRestController {
    static final String REST_URL = "/rest/claims";

    private ClaimRepository claimRepository;
    private ClaimService claimService;

    @GetMapping("/{id}")
    public ResponseEntity<Claim> get(@PathVariable int id) {
        log.info("get claim {}", id);
        return ResponseEntity.of(claimService.get(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete claim {}", id);
        claimService.delete(id);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllWithPagination(@RequestParam(value = "start", defaultValue = "0") Integer start, //datatables pagination params
                                                                    @RequestParam(value = "length", defaultValue = "20") Integer length,
                                                                    @RequestParam(value = "draw", defaultValue = "1") Integer draw,
                                                                    @RequestParam(value = "order[0][column]", defaultValue = "3") int sortColIndex,
                                                                    @RequestParam(value = "order[0][dir]", defaultValue = "desc") String order,
                                                                    @RequestParam(value = "status", defaultValue = "0") int status,
                                                                    @RequestParam Map<String, String> allRequestParams
    ) {
        log.info("getAll claims with pagination");
        int page = start / length; //Calculate page number

        Pageable pageable = PageRequest.of(
                page,
                length,
                Sort.by(
                        Sort.Direction.fromString(order),
                        allRequestParams.size() > 10 ? allRequestParams.get("columns[" + sortColIndex + "][data]") : "failureDate"
                )
        );
        Page<ClaimTo> claims = claimService.getAll(status, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("draw", draw);   //response for datatables pagination serverside
        response.put("recordsTotal", claims.getTotalElements());
        response.put("recordsFiltered", claims.getTotalElements());
        response.put("data", claims.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Claim> createWithLocation(@Valid @RequestBody Claim claim, @AuthenticationPrincipal AuthUser authUser) {
       // checkNew(claim);
        log.info("create claim {}", claim);
        claim.setDealer(authUser.getUser().getDealer());
        claim.setAuthor(authUser.getUser());
        Claim created = claimRepository.save(claim);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Claim claim, @PathVariable int id) {
        log.info("update claim {}", claim);
        assureIdConsistent(claim, id);
        claimService.update(claim);
    }


}