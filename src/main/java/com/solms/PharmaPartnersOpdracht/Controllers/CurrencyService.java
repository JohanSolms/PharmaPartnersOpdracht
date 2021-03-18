package com.solms.PharmaPartnersOpdracht.Controllers;
/*

        Author: Johan Solms - johan.solms@gmail.com

 */
import com.solms.PharmaPartnersOpdracht.Models.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository repository;

    public CurrencyService(){
    }

    public List<Currency> getAllCurrencies(Integer page, Integer size, String sort)
    {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Currency> pagedResult = repository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Currency>();
        }
    }

    public void addUser(Currency userRecord) {
        repository.save(userRecord);
    }

    public Optional<Currency> getCurrency(long id){
       return repository.findById(id);
    }

    public Optional<Currency> updateCurrency(long id, Currency currency){

       Optional<Currency> optionalCurrency = repository.findById(id);

       if(optionalCurrency.isPresent()){
           optionalCurrency.get().setId(id);
           optionalCurrency.get().setTicker(currency.getTicker());
           optionalCurrency.get().setName(currency.getName());
           optionalCurrency.get().setCoinNumber(currency.getCoinNumber());
           optionalCurrency.get().setMarketCap(currency.getMarketCap());
           repository.save(optionalCurrency.get());
       }
       return  optionalCurrency;
    }

    public Optional<Currency> deleteCurrency(long id){
        Optional<Currency> currency = repository.findById(id);
        currency.ifPresent(value -> repository.delete(value));
        return currency;
    }

}