package com.example.quizlogogame;

import com.example.quizlogogame.Models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class AllQuiz {

    public static final List<List<Quiz>> ALL_QUIZ = new ArrayList<>();
    List<Quiz> level1 = new ArrayList<>();
    List<Quiz> level2 = new ArrayList<>();
    List<Quiz> level3 = new ArrayList<>();
    List<Quiz> level4 = new ArrayList<>();
    List<Quiz> level5 = new ArrayList<>();
    List<Quiz> level6 = new ArrayList<>();
    List<Quiz> level7 = new ArrayList<>();
    List<Quiz> level8 = new ArrayList<>();

    public AllQuiz(){
        level1.add(new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"));
        level1.add(new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"));
        level1.add(new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"));
        level1.add(new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"));
        level1.add(new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net"));
        level1.add(new Quiz(R.drawable.four_chan_a, R.drawable.four_chan_b, "4chan"));
        level1.add(new Quiz(R.drawable.google_a, R.drawable.google_b, "Google"));
        level1.add(new Quiz(R.drawable.office_a, R.drawable.office_b, "Office"));
        level1.add(new Quiz(R.drawable.tiktok_a, R.drawable.tiktok_b, "TikTok"));
        level1.add(new Quiz(R.drawable.tinder_a, R.drawable.tinder_b, "Tinder"));

        level2.add(new Quiz(R.drawable.uber_a, R.drawable.uber_b, "Uber"));
        level2.add(new Quiz(R.drawable.wikia_a, R.drawable.wikia_b, "Wikia"));
        level2.add(new Quiz(R.drawable.gizmodo_a, R.drawable.gizmodo_b, "Gizmodo"));
        level2.add(new Quiz(R.drawable.hotels_a, R.drawable.hotels_b, "Hotels"));
        level2.add(new Quiz(R.drawable.kik_a, R.drawable.kik_b, "Kik"));
        level2.add(new Quiz(R.drawable.match_a, R.drawable.match_b, "Match"));

        level3.add(new Quiz(R.drawable.rt_a, R.drawable.rt_b, "RT"));
        level3.add(new Quiz(R.drawable.techradar_a, R.drawable.techradar_b, "TechRadar"));
        level3.add(new Quiz(R.drawable.uber_eats_a, R.drawable.uber_eats_b, "UberEats"));
        level3.add(new Quiz(R.drawable.vevo_a, R.drawable.vevo_b, "vevo"));
        level3.add(new Quiz(R.drawable.waze_a, R.drawable.waze_b, "waze"));
        level3.add(new Quiz(R.drawable.wish_a, R.drawable.wish_b, "wish"));
        level3.add(new Quiz(R.drawable.zalando_a, R.drawable.zalando_b, "zalando"));
        level3.add(new Quiz(R.drawable.zedge_a, R.drawable.zedge_b, "zedge"));

        level4.add(new Quiz(R.drawable.adidas_a, R.drawable.adidas_b, "adidas"));
        level4.add(new Quiz(R.drawable.allianz_a, R.drawable.allianz_b, "allianz"));
        level4.add(new Quiz(R.drawable.amazon_a, R.drawable.amazon_b, "amazon"));
        level4.add(new Quiz(R.drawable.audi_a, R.drawable.audi_b, "audi"));
        level4.add(new Quiz(R.drawable.barbie_a, R.drawable.barbie_b, "barbie"));

        level5.add(new Quiz(R.drawable.citroen_a, R.drawable.citroen_b, "citroen"));
        level5.add(new Quiz(R.drawable.dell_a, R.drawable.dell_b, "dell"));
        level5.add(new Quiz(R.drawable.dropbox_a, R.drawable.dropbox_b, "dropbox"));
        level5.add(new Quiz(R.drawable.ebay_a, R.drawable.ebay_b, "ebay"));
        level5.add(new Quiz(R.drawable.fila_a, R.drawable.fila_b, "fila"));
        level5.add(new Quiz(R.drawable.honda_a, R.drawable.honda_b, "honda"));
        level5.add(new Quiz(R.drawable.intel_a, R.drawable.intel_b, "intel"));
        level5.add(new Quiz(R.drawable.lacoste_a, R.drawable.lacoste_b, "lacoste"));
        level5.add(new Quiz(R.drawable.lufthansa_a, R.drawable.lufthansa_b, "lufthansa"));

        level6.add(new Quiz(R.drawable.mercedes_a, R.drawable.mercedes_b, "mercedes"));
        level6.add(new Quiz(R.drawable.pizzahut_a, R.drawable.pizzahut_b, "pizzahut"));
        level6.add(new Quiz(R.drawable.ray_ban_a, R.drawable.ray_ban_b, "rayban"));
        level6.add(new Quiz(R.drawable.redbull_a, R.drawable.redbull_b, "redbull"));
        level6.add(new Quiz(R.drawable.rolex_a, R.drawable.rolex_b, "rolex"));
        level6.add(new Quiz(R.drawable.uncle_ben_a, R.drawable.uncle_ben_b, "unclebens"));

        level7.add(new Quiz(R.drawable.vans_a, R.drawable.vans_b, "vans"));
        level7.add(new Quiz(R.drawable.visa_a, R.drawable.visa_b, "visa"));
        level7.add(new Quiz(R.drawable.wikipedia_a, R.drawable.wikipedia_b, "wikipedia"));
        level7.add(new Quiz(R.drawable.yamaha_a, R.drawable.yamaha_b, "yamaha"));
        level7.add(new Quiz(R.drawable.adobe_a, R.drawable.adobe_b, "adobe"));
        level7.add(new Quiz(R.drawable.amd_a, R.drawable.amd_b, "amd"));
        level7.add(new Quiz(R.drawable.best_buy_a, R.drawable.best_buy_b, "bestbuy"));

        level8.add(new Quiz(R.drawable.bmw_a, R.drawable.bmw_b, "bmw"));
        level8.add(new Quiz(R.drawable.bosh_a, R.drawable.bosh_b, "bosh"));
        level8.add(new Quiz(R.drawable.bp_a, R.drawable.bp_b, "bp"));
        level8.add(new Quiz(R.drawable.converse_a, R.drawable.converse_b, "converse"));
        level8.add(new Quiz(R.drawable.corona_a, R.drawable.corona_b, "corona"));
        level8.add(new Quiz(R.drawable.dunlop_a, R.drawable.dunlop_b, "dunlop"));
        level8.add(new Quiz(R.drawable.heineken_a, R.drawable.heineken_b, "heineken"));
        level8.add(new Quiz(R.drawable.ibm_a, R.drawable.ibm_b, "ibm"));
        level8.add(new Quiz(R.drawable.icq_a, R.drawable.icq_b, "icq"));
        level8.add(new Quiz(R.drawable.kfc_a, R.drawable.kfc_b, "kfc"));
        level8.add(new Quiz(R.drawable.kodak_a, R.drawable.kodak_b, "kodak"));
        level8.add(new Quiz(R.drawable.michelin_a, R.drawable.michelin_b, "michelin"));

        ALL_QUIZ.add(level1);
        ALL_QUIZ.add(level2);
        ALL_QUIZ.add(level3);
        ALL_QUIZ.add(level4);
        ALL_QUIZ.add(level5);
        ALL_QUIZ.add(level6);
        ALL_QUIZ.add(level7);
        ALL_QUIZ.add(level8);
    }
}