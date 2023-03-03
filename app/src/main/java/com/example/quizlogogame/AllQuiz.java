package com.example.quizlogogame;

import com.example.quizlogogame.Models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class AllQuiz {
//    public static final Quiz[][] ALL_QUIZ = {{new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"), new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"), new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"), new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"), new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net")},
//            {new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"), new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"), new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"), new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"), new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net")},
//            {},
//            {},
//            {}};

    public static final List<List<Quiz>> ALL_QUIZ = new ArrayList<>();
    List<Quiz> level1 = new ArrayList<>();
    List<Quiz> level2 = new ArrayList<>();
    List<Quiz> level3 = new ArrayList<>();
    List<Quiz> level4 = new ArrayList<>();
    List<Quiz> level5 = new ArrayList<>();

    public AllQuiz(){
        level1.add(new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"));
        level1.add(new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"));
        level1.add(new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"));
        level1.add(new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"));
        level1.add(new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net"));

        level2.add(new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"));
        level2.add(new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"));
        level2.add(new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"));
        level2.add(new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"));
        level2.add(new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net"));

        level3.add(new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"));
        level3.add(new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"));
        level3.add(new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"));
        level3.add(new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"));
        level3.add(new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net"));

        level4.add(new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"));
        level4.add(new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"));
        level4.add(new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"));
        level4.add(new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"));
        level4.add(new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net"));

        level5.add(new Quiz(R.drawable.ask_a, R.drawable.ask_b, "Ask"));
        level5.add(new Quiz(R.drawable.badoo_a, R.drawable.badoo_b, "badoo"));
        level5.add(new Quiz(R.drawable.bitcoin_a, R.drawable.bitcoin_b, "bitcoin"));
        level5.add(new Quiz(R.drawable.buzzfeed_a, R.drawable.buzzfeed_b, "BuzzFeed"));
        level5.add(new Quiz(R.drawable.cnet_a, R.drawable.cnet_b, "c|net"));

        ALL_QUIZ.add(level1);
        ALL_QUIZ.add(level2);
        ALL_QUIZ.add(level3);
        ALL_QUIZ.add(level4);
        ALL_QUIZ.add(level5);
    }
}
