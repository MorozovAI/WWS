package com.morozov.warrantywebsystem.web.dealer;

import com.morozov.warrantywebsystem.model.Dealer;
import com.morozov.warrantywebsystem.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

import static com.morozov.warrantywebsystem.util.validation.ValidationUtil.assureIdConsistent;
import static com.morozov.warrantywebsystem.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDealerController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "dealers")
public class AdminDealerController extends AbstractDealerController {

    static final String REST_URL = "/rest/admin/dealers/";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Dealer> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    @Cacheable
    public List<Dealer> getAll() {
        log.info("get all users");
        return (List<Dealer>) dealerRepository.findAll(Sort.by(Sort.Direction.ASC, "dealerName"));
    }

    @GetMapping(value = "{dealerId}/users")
    @Cacheable
    public Set<User> getAllByDealer(@PathVariable int dealerId) {
        log.info("get all users for dealer {}", dealerId);
        return dealerRepository.getExisted(dealerId).getUsers();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Dealer> createWithLocation(@Valid @RequestBody Dealer dealer) {
        log.info("create dealer {}", dealer);
        checkNew(dealer);
        Dealer created = dealerRepository.save(dealer);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@RequestBody @Valid Dealer updatedDealer, @PathVariable int id) {
        log.info("update dealer {} with id={}", updatedDealer, id);
        assureIdConsistent(updatedDealer, id);
        Dealer dealer = dealerRepository.getExisted(id);
        dealer.setDealerCode(updatedDealer.getDealerCode());
        dealer.setDealerName(updatedDealer.getDealerName());
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(allEntries = true)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        Dealer dealer = dealerRepository.getExisted(id);
        dealer.setEnabled(enabled);
        dealer.getUsers().forEach(u -> u.setEnabled(enabled));
    }

    @PostMapping(value = "{dealerId}/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody User user, @PathVariable int dealerId) {
        log.info("create user {}", user);
        checkNew(user);
        user.setDealer(dealerRepository.getExisted(dealerId));
        User created = userRepository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}