package com.Model;

import com.Model.Rules.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, получающий списки правил и применяющий их к данным.
 */
public class EntryChecker {

    private final List<Rule> rules;
    private final List<Entry> entries;

    public EntryChecker() {
        rules = new ArrayList<>();
        entries = new ArrayList<>();
    }

    /**
     * Добавляет правило к списку.
     *
     * @param rule - новое правило.
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    /**
     * Добавляет строку данных к списку.
     *
     * @param entry - новая строка данных.
     */
    public void addEntry(Entry entry) {
        if(entry != null) {
            entries.add(entry);
        }
    }

    /**
     * Для каждого правила подсчитывает количество строк данных, которые ему соответствуют.
     *
     * @return список целых чисел, каждое - количество подходящих строк для каждого правила.
     */
    public List<Integer> countMatches() {
        List<Integer> res = new ArrayList<>();
        for(Rule rule: rules) {
            if(rule != null) {
                int matches = 0;
                for(Entry entry: entries) {
                    if(rule.checkEntry(entry)) {
                        matches++;
                    }
                }
                res.add(matches);
            } else {
                res.add(-1);
            }
        }
        return res;
    }

}
