package com.example.tde2;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class PriceResponse {
    @SerializedName("bitcoin")
    private Map<String, Float> bitcoin;

    public Map<String, Float> getBitcoin() {
        return bitcoin;
    }
}
