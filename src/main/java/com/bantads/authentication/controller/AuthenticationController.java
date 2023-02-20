package com.bantads.authentication.controller;

import com.bantads.authentication.model.AuthenticationModel;
import com.bantads.authentication.repository.AuthenticationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private AuthenticationRepository authenticationRepository;

    @GetMapping
    public ResponseEntity<List<AuthenticationModel>> getAllAuthentications() {
        List<AuthenticationModel> authModelList = this.authenticationRepository.findAll();
        return ResponseEntity.ok(authModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthenticationModel> getAuthenticationById(@PathVariable String id) {
        AuthenticationModel authModel = this.authenticationRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(authModel);
    }

    @GetMapping("/customer/{customer}")
    public ResponseEntity<AuthenticationModel> getAuthenticationByAccount(@PathVariable String customer) {
        AuthenticationModel authModel = this.authenticationRepository.findByCustomer(customer);
        return ResponseEntity.ok(authModel);
    }

    @PostMapping
    public ResponseEntity<AuthenticationModel> createAuthentication(@RequestBody AuthenticationModel authModel) {
        AuthenticationModel addedAuthModel = this.authenticationRepository.save(authModel);
        return ResponseEntity.ok(addedAuthModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthenticationModel> updateAuthentication(@PathVariable String id, @RequestBody AuthenticationModel authModel) {
        AuthenticationModel auth = this.authenticationRepository.findById(id).orElseThrow();
        auth.setCustomer(authModel.getCustomer());
        auth.setLogin(authModel.getLogin());
        auth.setPassword(authModel.getPassword());
        auth.setType(authModel.getType());
        auth.setIsApproved(authModel.getIsApproved());
        auth.setIsPending(authModel.getIsPending());
        return ResponseEntity.ok(this.authenticationRepository.save(auth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthentication(@PathVariable String id) {
        AuthenticationModel authModel = this.authenticationRepository.findById(id).orElseThrow();
        this.authenticationRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
