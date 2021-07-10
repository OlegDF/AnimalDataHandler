package com.Model.Rules;

import com.Model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleOrTest {

    private Rule ruleOr1, ruleOr2, ruleOr3, ruleOr4;
    private Entry entry;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Rule ruleTrue1, ruleTrue2, ruleFalse1, ruleFalse2;
        ruleTrue1 = new RuleTrue();
        ruleTrue2 = new RuleTrue();
        ruleFalse1 = new RuleFalse();
        ruleFalse2 = new RuleFalse();
        ruleOr1 = new RuleOr(ruleTrue1, ruleTrue2);
        ruleOr2 = new RuleOr(ruleTrue1, ruleFalse2);
        ruleOr3 = new RuleOr(ruleFalse1, ruleTrue2);
        ruleOr4 = new RuleOr(ruleFalse1, ruleFalse2);
        String[] columns = {"HEIGHT"};
        String[] values = {"HEIGHT1"};
        entry = new Entry<>(columns, values);
    }

    @Test
    void checkEntry() {
        assertTrue(ruleOr1.checkEntry(entry));
        assertTrue(ruleOr2.checkEntry(entry));
        assertTrue(ruleOr3.checkEntry(entry));
        assertFalse(ruleOr4.checkEntry(entry));
    }

}