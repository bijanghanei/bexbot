package com.bijanghanei.bex.client;


import com.bijanghanei.bex.entity.Price;
import com.bijanghanei.bex.entity.WatchList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "prices-api",url = "https://api.priceto.day/v1")
public interface PricesClient {
    @GetMapping("/symbols")
    WatchList getSymbols();
    @GetMapping("/latest/irr/{symbol}")
    Price getSymbolPrice(@PathVariable("symbol") String symbol);
}
