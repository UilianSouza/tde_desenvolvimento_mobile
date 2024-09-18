package com.example.tde2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinGeckoService {
    @GET("simple/price")
    Call<PriceResponse> getPrice(
            @Query("ids") String ids,
            @Query("vs_currencies") String vsCurrencies
    );
}
