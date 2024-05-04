package com.bijanghanei.bex.service;

import com.bijanghanei.bex.entity.Price;
import com.bijanghanei.bex.entity.WatchList;

public interface BexService {
    WatchList getWatchList();
    Price getPrice(String symbol);

}
