package com.Model.Rules;

import com.Model.Entry;
/**
 * Правило, которое всегда выполняется.
 */
public class RuleTrue implements Rule {

    public RuleTrue() {}

    @Override
    public boolean checkEntry(Entry entry) {
        return true;
    }

}
