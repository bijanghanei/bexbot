package com.bijanghanei.bex.service;

import com.bijanghanei.bex.client.PricesClient;
import com.bijanghanei.bex.entity.Price;
import com.bijanghanei.bex.entity.WatchList;
import org.springframework.stereotype.Service;

@Service
public class BexServiceImpl implements BexService{
    private final PricesClient pricesClient;

    public BexServiceImpl(PricesClient pricesClient) {
        this.pricesClient = pricesClient;
    }

    @Override
    public WatchList getWatchList() {
        return pricesClient.getSymbols();
    }

    @Override
    public Price getPrice(String symbol) {
        return pricesClient.getSymbolPrice(symbol);
    }
}
