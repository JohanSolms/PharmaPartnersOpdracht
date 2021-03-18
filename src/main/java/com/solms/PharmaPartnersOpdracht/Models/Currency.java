package com.solms.PharmaPartnersOpdracht.Models;
/*

        Author: Johan Solms - johan.solms@gmail.com

 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    @GeneratedValue
    private long id;

    private String ticker;
    private String name;
    private long coinNumber;
    private long marketCap;

    public Currency(String ticker, String name, long coinNumber, long marketCap){
        this.ticker = ticker;
        this.name = name;
        this.coinNumber = coinNumber;
        this.marketCap = marketCap;
    }

    public Currency(){

    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCoinNumber() {
        return coinNumber;
    }

    public void setCoinNumber(long coinNumber) {
        this.coinNumber = coinNumber;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
