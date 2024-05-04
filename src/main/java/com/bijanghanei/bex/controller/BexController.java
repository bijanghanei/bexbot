package com.bijanghanei.bex.controller;


import com.bijanghanei.bex.entity.Price;
import com.bijanghanei.bex.entity.WatchList;
import com.bijanghanei.bex.service.BexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bex")
public class BexController {
    private final BexService bexService;

    public BexController(BexService bexService) {
        this.bexService = bexService;
    }
    @GetMapping("/symbols")
    WatchList getAllSymbols(){
        return bexService.getWatchList();
    }
    @GetMapping("/latest/irr/{symbol}")
    Price getPrice(@PathVariable("symbol") String symbol){
        return bexService.getPrice(symbol);
    }
}
