package controller;

import model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyController {

    private List<Currency> currencies;

    public CurrencyController() {
        currencies = new ArrayList<>();

        // Hardcoded currencies for this stage of the assignment
        currencies.add(new Currency("USD", "US Dollar", 1.0));
        currencies.add(new Currency("EUR", "Euro", 0.92));
        currencies.add(new Currency("GBP", "British Pound", 0.79));
        currencies.add(new Currency("JPY", "Japanese Yen", 151.50));
        currencies.add(new Currency("BDT", "Bangladeshi Taka", 109.50));
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public double convert(double amount, Currency source, Currency target) {
        // Convert source amount to USD first
        double amountInUsd = amount / source.getRateToUsd();

        // Convert USD to target currency
        return amountInUsd * target.getRateToUsd();
    }
}