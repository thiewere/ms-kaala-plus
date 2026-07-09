package com.thifuge.kaala_plus.shared.services;

import com.thifuge.kaala_plus.shared.entities.Currency;
import com.thifuge.kaala_plus.shared.repositories.CurrencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public Currency createCurrency(Currency currency) {
        log.info("Creating a new currency {}", currency);
        return this.currencyRepository.save(currency);
    }

    public List<Currency> getAllCurrencies() {
        log.info("Getting all currencies");
        return this.currencyRepository.findAll();
    }

    public Currency getCurrency(Integer id) {
        log.info("Getting currency with id {}", id);
        Optional<Currency> currency = this.currencyRepository.findById(id);
        return currency.orElse(null);
    }

    public Currency getCurrencyByName(String name) {
        log.info("Getting currency by name {}", name);
        Optional<Currency> currency = this.currencyRepository.findByName(name);
        return currency.orElse(null);
    }

    public Currency updateCurrency(Integer id, Currency currency) {
        Currency currencyInDB = this.getCurrency(id);

        log.info("Updating currency with id {}", id);
        currencyInDB.setName(currency.getName());
        currencyInDB = this.currencyRepository.save(currencyInDB);
        return currencyInDB;
    }

    public void deleteCurrency(Integer id) {
        Currency currencyInDB = this.getCurrency(id);
        log.info("Deleting currency with id {}", id);
        this.currencyRepository.delete(currencyInDB);
    }
}
