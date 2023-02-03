package com.morozov.warrantywebsystem.web.claim;

import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.model.ClaimStatus;
import com.morozov.warrantywebsystem.repository.ClaimRepository;
import com.morozov.warrantywebsystem.to.ClaimTo;
import com.morozov.warrantywebsystem.util.ClaimsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = ClaimRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ClaimRestController {
    static final String REST_URL = "/rest/claims";

    @Autowired
    ClaimRepository repository;

    @GetMapping("/{id}")
    public Claim get(@PathVariable int id) {
        //int userId = SecurityUtil.authUserId();
        log.info("get claim {}", id);
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete claim {}", id);
        repository.deleteById(id);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllWithPagination(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                                                    @RequestParam(value = "length", defaultValue = "20") Integer length,
                                                                    @RequestParam(value = "draw", defaultValue = "1") Integer draw,
                                                                    @RequestParam(value = "order[0][column]", defaultValue = "0") int sortColIndex,
                                                                    @RequestParam(value = "order[0][dir]", defaultValue = "desc") String order,
                                                                    @RequestParam(value = "status", defaultValue = "0") int status,
                                                                    @RequestParam Map<String, String> allRequestParams
    ) {
        //int userId = SecurityUtil.authUser().id();
        log.info("getAll claims with pagination");
        int page = start / length; //Calculate page number

        Pageable pageable = PageRequest.of(
                page,
                length,
                Sort.by(
                        Sort.Direction.fromString(order),
                        allRequestParams.size() > 0 ? allRequestParams.get("columns[" + sortColIndex + "][data]"):"failureDate"
                )
        );
        Page<ClaimTo> claims = repository.findClaimsByStatus(ClaimStatus.values()[status], pageable)
                .map(ClaimsUtil::createClaimTo);
        Map<String, Object> response = new HashMap<>();
        response.put("draw", draw);
        response.put("recordsTotal", claims.getTotalElements());
        response.put("recordsFiltered", claims.getTotalElements());
        response.put("data", claims.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
/*
    @GetMapping
    public List<ClaimTo> getAll() {
        //int userId = SecurityUtil.authUser().id();
        log.info("getAll claims");
        Iterable<Claim> claims = repository.findAll(Sort.by("failureDate").descending());
        List<ClaimTo> claimsTo = ClaimsUtil.getClaimTos((List<Claim>) claims);

        return claimsTo;
    }*/


/*    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }*/

/*    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Validated(View.Web.class) @RequestBody Meal meal) {
        Meal created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }*/

 /*   @Override
    @GetMapping("/filter")
    public List<MealTo> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalTime startTime,
            @RequestParam @Nullable LocalDate endDate,
            @RequestParam @Nullable LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }*/
}