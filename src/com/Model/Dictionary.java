package com.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, объект которого содержит список столбцов с ожидаемыми значениями для каждого и может проверять исходные данные
 * и правила на соответствие, обнаруживая неправильные значения.
 *
 * @param <T> - тип данных значений в столбцах
 */
public class Dictionary<T> {

    private final Map<String, List<T>> columns;

    public Dictionary() {
        columns = new HashMap<>();
    }

    /**
     * Добавляет столбец со списком ожидаемых значений.
     *
     * @param columnName - столбец.
     * @param columnValues - ожидаемые значения.
     */
    public void addColumn(String columnName, List<T> columnValues) {
        columns.putIfAbsent(columnName, columnValues);
    }

    /**
     * Проверяет, соответствует ли столбец одному из тех, что записан в словаре.
     *
     * @param columnName - название проверяемого столбца.
     * @return true, если столбец с таким же названием содержится в словаре.
     */
    public boolean checkColumnName(String columnName) {
        return columns.containsKey(columnName);
    }

    /**
     * Проверяет, что заданный список столбцов совпадает с списком в словаре.
     *
     * @param columnNames - список названий столбцов.
     * @return true, если списки столбцов совпадают.
     */
    public boolean checkColumnList(String[] columnNames) {
        if(columnNames.length != columns.size()) {
            return false;
        }
        for(String columnName: columnNames) {
            if(!columns.containsKey(columnName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяет, что в словаре для данного столбца содержится данное значение.
     *
     * @param columnName - название столбца.
     * @param value - проверяемое значение столбца.
     * @return true, если данное значение есть в словаре.
     */
    public boolean checkValue(String columnName, T value) {
        List<T> columnValues = columns.get(columnName);
        return columnValues.contains(value);
    }

}
