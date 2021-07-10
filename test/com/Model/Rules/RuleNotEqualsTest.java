package com.Model.Rules;

import com.Model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleNotEqualsTest {

    private Rule<String> ruleNotEquals1, ruleNotEquals2;
    private Entry<String> entry1, entry2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ruleNotEquals1 = new RuleNotEquals<>("HEIGHT", "HEIGHT1");
        ruleNotEquals2 = new RuleNotEquals<>("WEIGHT", "WEIGHT1");
        String[] columns = {"HEIGHT", "WEIGHT"};
        String[] values1 = {"HEIGHT1", "WEIGHT2"};
        String[] values2 = {"HEIGHT2", "WEIGHT1"};
        entry1 = new Entry<>(columns, values1);
        entry2 = new Entry<>(columns, values2);
    }

    @Test
    void checkEntry() {
        assertFalse(ruleNotEquals1.checkEntry(entry1));
        assertTrue(ruleNotEquals1.checkEntry(entry2));
        assertTrue(ruleNotEquals2.checkEntry(entry1));
        assertFalse(ruleNotEquals2.checkEntry(entry2));
    }
}