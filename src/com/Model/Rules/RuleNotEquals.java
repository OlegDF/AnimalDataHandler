package com.Model.Rules;

import com.Model.Entry;

/**
 * Правило, которое выполняется для строк, в которых значение определенного столбца отличается от заранее заданного.
 */
public class RuleNotEquals<T> implements Rule<T> {

    private final String column;
    private final T value;

    public RuleNotEquals(String column, T value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public boolean checkEntry(Entry<T> entry) {
        return !value.equals(entry.getValue(column));
    }

}
