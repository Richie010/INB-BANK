package com.mind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.mind.entity.FixedDeposit;
import com.mind.service.FixedDepositService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/FD")
public class FixedDepositController {

    @Autowired
    private FixedDepositService fixedDepositService;

int duration;
@PostMapping("/create")
public FixedDeposit createFixedDeposit(@RequestBody FixedDeposit fixeddepo) {
    try {
        FixedDeposit fixedDeposit = fixedDepositService.createFixedDeposit(fixeddepo);
        return fixedDeposit;
    } catch (IllegalArgumentException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
    }
}
}
