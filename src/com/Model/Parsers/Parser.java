package com.Model.Parsers;

import com.Model.Dictionary;
import com.Model.Entry;
import com.Model.Rules.Rule;

/**
 * Интерфейс класса, получающего объекты данных программы из текста.
 *
 * @param <T> - тип данных значений в строках
 */
public interface Parser<T> {

    /**
     * Получает из заданного текста объект правила. Передает сообщение об ошибке, если столбцы и значения, упомянутые в
     * правиле, не содержатся в словаре.
     *
     * @param ruleLine - входная строка.
     * @param dictionary - словарь, по которому проверяются входные данные.
     * @return полученное правило или null, если входные данные неправильны.
     */
    Rule<T> parseRule(String ruleLine, Dictionary<T> dictionary);

    /**
     * Получает из заданного текста объект строки данных с заданными столбцами. Передает сообщение об ошибке, если
     * количество столбцов и количество значений в строке не совпадает.
     *
     * @param columns - список столбцов в строке.
     * @param entryLine - входная строка.
     * @return полученную строку данных или null, если входные данные неправильны.
     */
    Entry<T> parseEntry(String[] columns, String entryLine);

    /**
     * Получает из заданного текста название столбца данных и ряда значений и добавляет его в словарь.
     *
     * @param columnLine - входная строка.
     * @param dictionary - дополняемый словарь.
     */
    void parseDictionaryColumn(String columnLine, Dictionary<T> dictionary);

}
