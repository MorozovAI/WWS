package com.morozov.warrantywebsystem.web.user;

import com.morozov.warrantywebsystem.model.User;
import com.morozov.warrantywebsystem.repository.UserRepository;
import com.morozov.warrantywebsystem.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Slf4j
public abstract class AbstractUserController {

    @Autowired
    protected UserRepository repository;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public ResponseEntity<User> get(int id) {
        log.info("get user {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        log.info("delete user {}", id);
        repository.deleteExisted(id);
    }

    protected User prepareAndSave(User updatedUser, User user) {
        return repository.save(UserUtil.prepareToSave(updatedUser, user));

    }
}