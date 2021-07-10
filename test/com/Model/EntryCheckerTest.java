package com.Model;

import com.Model.Rules.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntryCheckerTest {

    private EntryChecker entryChecker;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        entryChecker = new EntryChecker();
        entryChecker.addRule(new RuleEquals<>("ТИП", "ТРАВОЯДНОЕ"));
        entryChecker.addRule(new RuleAnd(
                new RuleOr(new RuleEquals<>("ТИП", "ТРАВОЯДНОЕ"), new RuleEquals<>("ТИП", "ВСЕЯДНОЕ")),
                new RuleEquals<>("РОСТ", "МАЛЕНЬКОЕ")
        ));
        entryChecker.addRule(new RuleAnd(
                new RuleEquals<>("ТИП", "ВСЕЯДНОЕ"),
                new RuleNotEquals<>("РОСТ", "ВЫСОКОЕ")
        ));
        String[] columns = {"ВЕС", "РОСТ", "ТИП"};
        String[][] valuesMatrix = {{"ЛЕГКОЕ", "МАЛЕНЬКОЕ", "ВСЕЯДНОЕ"},
                {"ТЯЖЕЛОЕ", "МАЛЕНЬКОЕ", "ТРАВОЯДНОЕ"},
                {"ТЯЖЕЛОЕ", "НЕВЫСОКОЕ", "ТРАВОЯДНОЕ"},
                {"СРЕДНЕЕ", "МАЛЕНЬКОЕ", "ПЛОТОЯДНОЕ"},
                {"ЛЕГКОЕ", "ВЫСОКОЕ", "ВСЕЯДНОЕ"},
                {"ЛЕГКОЕ", "НЕВЫСОКОЕ", "ПЛОТОЯДНОЕ"},
                {"СРЕДНЕЕ", "ВЫСОКОЕ", "ТРАВОЯДНОЕ"},
                {"ТЯЖЕЛОЕ", "НЕВЫСОКОЕ", "ВСЕЯДНОЕ"},
                {"СРЕДНЕЕ", "НЕВЫСОКОЕ", "ВСЕЯДНОЕ"},
                {"ЛЕГКОЕ", "МАЛЕНЬКОЕ", "ТРАВОЯДНОЕ"}};
        for(String[] values: valuesMatrix) {
            entryChecker.addEntry(new Entry<>(columns, values));
        }
    }

    @Test
    void addRule() {
        entryChecker.addRule(new RuleEquals<>("ТИП", "ТРАВОЯДНОЕ"));
        assertEquals(entryChecker.getRuleCount(), 4);
    }

    @Test
    void addEntry() {
        String[] columns = {"ВЕС", "РОСТ", "ТИП"};
        String[] values = {"СРЕДНЕЕ", "НЕВЫСОКОЕ", "ВСЕЯДНОЕ"};
        entryChecker.addEntry(new Entry<>(columns, values));
        assertEquals(entryChecker.getEntryCount(), 11);
    }

    @Test
    void countMatches() {
        List<Integer> counts = entryChecker.countMatches();
        assertEquals(counts.get(0), 4);
        assertEquals(counts.get(1), 3);
        assertEquals(counts.get(2), 3);
    }

}