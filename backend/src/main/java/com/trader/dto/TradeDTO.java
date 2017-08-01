package com.trader.dto;

import java.util.Date;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;

public class TradeDTO {

    @NotNull
    private String userId;
    @NotNull
    private String currencyFrom;
    @NotNull
    private String currencyTo;
    @NotNull
    private BigDecimal amountSell;
    @NotNull
    private BigDecimal amountBuy;
    @NotNull
    private Double rate;
    @NotNull
    // @DateTimeFormat(pattern="dd-MMM-yy HH:mm:ss")
    private String timePlaced;
    @NotNull
    private String originatingCountry;

    private TradeDTO(){}

    public TradeDTO(String userId, String currencyFrom, String currencyTo, BigDecimal amountSell,
                    BigDecimal amountBuy, Double rate, String timePlaced, String originatingCountry) {
        this.userId = userId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountSell = amountSell;
        this.amountBuy = amountBuy;
        this.rate = rate;
        this.timePlaced = timePlaced;
        this.originatingCountry = originatingCountry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(BigDecimal amountSell) {
        this.amountSell = amountSell;
    }

    public BigDecimal getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(BigDecimal amountBuy) {
        this.amountBuy = amountBuy;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(String timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }
}
