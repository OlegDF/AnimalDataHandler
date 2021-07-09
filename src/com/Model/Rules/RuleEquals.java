package com.Model.Rules;

import com.Model.Entry;

/**
 * Правило, которое выполняется для строк, в которых значение определенного столбца совпадает с заранее заданным.
 */
public class RuleEquals<T> implements Rule<T> {

    private final String column;
    private final T value;

    public RuleEquals(String column, T value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public boolean checkEntry(Entry<T> entry) {
        return value.equals(entry.getValue(column));
    }

}
