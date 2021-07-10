package com.Model.Rules;

import com.Model.Entry;

/**
 * Правило, которое всегда не выполняется.
 */
public class RuleFalse implements Rule {

    public RuleFalse() {}

    @Override
    public boolean checkEntry(Entry entry) {
        return false;
    }

}
