package com.laioffer.twitch.service;

import com.laioffer.twitch.dao.FavoriteDao;
import com.laioffer.twitch.entity.db.Item;
import com.laioffer.twitch.entity.db.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoriteService {
    private FavoriteDao favoriteDao;
    @Autowired
    public FavoriteService(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public void setFavoriteItem(String userId, Item item) {
        favoriteDao.setFavoriteItem(userId, item);
    }
    public void unsetFavoriteItem(String userId, String itemId) {
        favoriteDao.unsetFavoriteItem(userId, itemId);
    }

    public Map<String, List<Item>> getFavoriteItem(String userId) {
        Map<String, List<Item>> itemMap = new HashMap<>();
        for (ItemType type : ItemType.values()) {
            itemMap.put(type.toString(), new ArrayList<>());
        }
        Set<Item> favorites = favoriteDao.getFavoriteItem(userId);
        for(Item item : favorites) {
            itemMap.get(item.getType().toString()).add(item);
        }
        return itemMap;
    }
}
