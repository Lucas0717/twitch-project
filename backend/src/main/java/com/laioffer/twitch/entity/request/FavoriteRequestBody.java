package com.laioffer.twitch.entity.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laioffer.twitch.entity.db.Item;

public class FavoriteRequestBody {
    private final Item item;

    @JsonCreator
    public FavoriteRequestBody(@JsonProperty("favorite") Item item) {
        this.item = item;
    }
    public Item getItem() {
        return item;
    }
}
