package com.Model.Rules;

import com.Model.Entry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleAndTest {

    private Rule ruleAnd1, ruleAnd2, ruleAnd3, ruleAnd4;
    private Entry entry;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Rule ruleTrue1, ruleTrue2, ruleFalse1, ruleFalse2;
        ruleTrue1 = new RuleTrue();
        ruleTrue2 = new RuleTrue();
        ruleFalse1 = new RuleFalse();
        ruleFalse2 = new RuleFalse();
        ruleAnd1 = new RuleAnd(ruleTrue1, ruleTrue2);
        ruleAnd2 = new RuleAnd(ruleTrue1, ruleFalse2);
        ruleAnd3 = new RuleAnd(ruleFalse1, ruleTrue2);
        ruleAnd4 = new RuleAnd(ruleFalse1, ruleFalse2);
        String[] columns = {"HEIGHT"};
        String[] values = {"HEIGHT1"};
        entry = new Entry<>(columns, values);
    }

    @Test
    void checkEntry() {
        assertTrue(ruleAnd1.checkEntry(entry));
        assertFalse(ruleAnd2.checkEntry(entry));
        assertFalse(ruleAnd3.checkEntry(entry));
        assertFalse(ruleAnd4.checkEntry(entry));
    }

}