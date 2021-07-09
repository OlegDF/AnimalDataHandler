package com.Model.Rules;

import com.Model.Entry;

/**
 * Правило, которое содержит два других правила и выполняется для строк, для которых выполняются они оба.
 */
public class RuleAnd implements Rule {

    private final Rule rule1, rule2;

    public RuleAnd(Rule rule1, Rule rule2) {
        this.rule1 = rule1;
        this.rule2 = rule2;
    }

    @Override
    public boolean checkEntry(Entry entry) {
        return rule1.checkEntry(entry) && rule2.checkEntry(entry);
    }

}
