package com.Model.Rules;

import com.Model.Entry;

/**
 * Интерфейс объектов правил, которые могут проверять определенный набор условий на строках данных.
 *
 * @param <T> - тип данных значений в строках
 */
public interface Rule<T> {

    /**
     * Проверяет строку данных на соответствие правилу.
     *
     * @param entry - входная строка данных.
     * @return true, если строка соответствует правилу.
     */
    boolean checkEntry(Entry<T> entry);

}
