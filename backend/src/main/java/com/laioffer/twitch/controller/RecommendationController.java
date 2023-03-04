package com.laioffer.twitch.controller;

import com.laioffer.twitch.entity.db.Item;
import com.laioffer.twitch.service.RecommendationException;
import com.laioffer.twitch.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class RecommendationController {
    private RecommendationService recommendationService;
    @Autowired

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }
    @RequestMapping(value = "/recommendation", method = RequestMethod.GET)
    @ResponseBody

    public Map<String, List<Item>> recommendation(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(false);
        Map<String, List<Item>> itemMap;

        try {
            if (session == null) {
                itemMap = recommendationService.recommendationByDefault();

            } else {
                String userId = (String) session.getAttribute("user_id");
                itemMap = recommendationService.recommendItemsByUser(userId);
            }
        } catch (RecommendationException ex) {
            throw new ServletException(ex);
        }
        return itemMap;
    }
}
