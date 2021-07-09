package com.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, объекты которого содержат данные об одной строке данных: список столбцов и соответствующих им значений.
 *
 * @param <T> - тип данных значений в столбцах
 */
public class Entry<T> {

    private final Map<String, T> columnValues;

    public Entry(String[] columns, T[] values) {
        columnValues = new HashMap<>();
        int columnNum = Math.min(columns.length, values.length);
        for(int i = 0; i < columnNum; i++) {
            columnValues.putIfAbsent(columns[i], values[i]);
        }
    }

    /**
     * Метод получает значение одного из столбцов.
     *
     * @param columnName - название столбца.
     * @return значение столбца в этой строке (или null, если столбец не найден).
     */
    public T getValue(String columnName) {
        return columnValues.get(columnName);
    }

}
