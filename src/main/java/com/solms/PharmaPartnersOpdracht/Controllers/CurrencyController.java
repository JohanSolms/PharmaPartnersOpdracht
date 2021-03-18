package com.solms.PharmaPartnersOpdracht.Controllers;
/*

        Author: Johan Solms - johan.solms@gmail.com

 */
import com.solms.PharmaPartnersOpdracht.Models.Currency;
import com.solms.PharmaPartnersOpdracht.PharmaPartnersOpdrachtApplication;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    List<Currency> currencies;

    @RequestMapping(value = "/test")
    public @ResponseBody String test(){
        return "Hallo!";
    }

    @ApiOperation(value = "getCurrencies", notes="Retrieve all stored blockchain currencies",nickname = "getCurrencies")
    @GetMapping(value = "/currencies")
    public List<Currency> getCurrencies(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        PharmaPartnersOpdrachtApplication.logger.info("getCurrencies");
        return currencyService.getAllCurrencies(page, size, sort);
    }

    @PostMapping(value = "/currencies")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        PharmaPartnersOpdrachtApplication.logger.info("addCurrency " + currency.getTicker());

        currencyService.addUser(currency);
        return ResponseEntity.ok(currency);
    }

    @GetMapping(value = "/currencies", params = "id")
    public ResponseEntity<Currency> getCurrency(@RequestParam long id) {
        PharmaPartnersOpdrachtApplication.logger.info("getCurrency " + id);
        return currencyService.getCurrency(id).isPresent() ? ResponseEntity.ok(currencyService.getCurrency(id).get()) : ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/currencies", params = "id")
    public ResponseEntity<Currency> putCurrency(@RequestParam long id, @RequestBody Currency currency) {
        PharmaPartnersOpdrachtApplication.logger.info("putCurrency id " + id + " " + currency.getTicker());
        Optional<Currency> optionalCurrency = currencyService.updateCurrency(id, currency);
        return optionalCurrency.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/currencies", params = "id")
    public ResponseEntity<Currency> deleteCurrency(@RequestParam long id) {
        PharmaPartnersOpdrachtApplication.logger.info("deleteCurrency id " + id);
        return currencyService.deleteCurrency(id).isPresent() ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}