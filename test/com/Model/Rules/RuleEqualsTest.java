package com.Model.Rules;

import com.Model.Entry;

import static org.junit.jupiter.api.Assertions.*;

class RuleEqualsTest {

    private Rule<String> ruleEquals1, ruleEquals2;
    private Entry<String> entry1, entry2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ruleEquals1 = new RuleEquals<>("HEIGHT", "HEIGHT1");
        ruleEquals2 = new RuleEquals<>("WEIGHT", "WEIGHT1");
        String[] columns = {"HEIGHT", "WEIGHT"};
        String[] values1 = {"HEIGHT1", "WEIGHT2"};
        String[] values2 = {"HEIGHT2", "WEIGHT1"};
        entry1 = new Entry<>(columns, values1);
        entry2 = new Entry<>(columns, values2);
    }

    @org.junit.jupiter.api.Test
    void checkEntry() {
        assertTrue(ruleEquals1.checkEntry(entry1));
        assertFalse(ruleEquals1.checkEntry(entry2));
        assertFalse(ruleEquals2.checkEntry(entry1));
        assertTrue(ruleEquals2.checkEntry(entry2));
    }
}