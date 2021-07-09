package com.Model.Parsers;

import com.Model.Dictionary;
import com.Model.Entry;
import com.Model.Rules.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParserStandard implements Parser<String> {

    public ParserStandard() {}

    /**
     * Разбивает введенную строку на сегменты, в каждом из которых ожидается название столбца, знак (= для равенства,
     * != для неравенства) и ряд значений. Выводит ошибку, если название столбца не встречается в словаре или если
     * сегмент не содержит знака.
     *
     * @param ruleLine - входная строка.
     * @param dictionary - словарь, по которому проверяются входные данные.
     * @return полученное правило или null, если входные данные неправильны.
     */
    @Override
    public Rule<String> parseRule(String ruleLine, Dictionary<String> dictionary) {
        Rule<String> res = null;
        String[] rules1stLayer = ruleLine.split(";");
        for(String ruleSubLine: rules1stLayer) {
            String[] ruleEqualSign = ruleSubLine.split("=");
            if(ruleEqualSign.length == 2) {
                String columnName = ruleEqualSign[0];
                boolean negate = false;
                if(columnName.charAt(columnName.length() - 1) =='!') {
                    negate = true;
                    columnName = columnName.substring(0, columnName.length() - 1);
                }
                if(dictionary.checkColumnName(columnName)) {
                    String[] values = ruleEqualSign[1].split(",");
                    res = expandRule(ruleLine, res, columnName, negate, values, dictionary);
                    if(res == null) {
                        return null;
                    }
                } else {
                    System.out.println("Ошибка: в одном из правил проверяется характеристика " + columnName +
                            ", не найденная в словаре. Правило не может быть использовано. Текст правила: " + ruleLine);
                    return null;
                }
            } else {
                System.out.println("Ошибка: в сегменте одного из правил не найдено знака сравнения. Правило не может быть использовано. Текст правила: " + ruleLine);
                return null;
            }
        }
        return res;
    }

    //Разделяет сегмент на отдельные значения столбца. Выводит ошибку, если одно из значений не встречается в словаре.
    //Полученный фрагмент правила соединяет с существующим правилом с помощью нового правила AND.
    private Rule<String> expandRule(String ruleLine, Rule<String> res, String columnName, boolean negate, String[] values, Dictionary<String> dictionary) {
        Rule<String> subRule = null;
        for(String value: values) {
            subRule = expandSubRule(columnName, negate, subRule, value, dictionary);
            if(subRule == null) {
                System.out.println("Ошибка: в одном из правил проверяется значение " + value +
                        " характеристики " + columnName + ", не найденное в словаре. Правило не может быть использовано. Текст правила: " + ruleLine);
                return null;
            }
        }
        if(res == null) {
            res = subRule;
        } else {
            res = new RuleAnd(res, subRule);
        }
        return res;
    }

    //Создает новый фрагмент правила для отдельного значения столбца (правило EQUALS или NOTEQUALS) и присоединяет его к
    // существуюшему правилу (с помощью нового правила OR или AND соответственно)
    private Rule<String> expandSubRule(String columnName, boolean negate, Rule<String> subRule, String value, Dictionary<String> dictionary) {
        if(!dictionary.checkValue(columnName, value)) {
            return null;
        }
        Rule<String> nextRule;
        if(negate) {
            nextRule = new RuleNotEquals<>(columnName, value);
        } else {
            nextRule = new RuleEquals<>(columnName, value);
        }
        if(subRule == null) {
            subRule = nextRule;
        } else {
            if(negate) {
                subRule = new RuleAnd(subRule, nextRule);
            } else {
                subRule = new RuleOr(subRule, nextRule);
            }
        }
        return subRule;
    }

    @Override
    public Entry<String> parseEntry(String[] columns, String entryLine) {
        Entry<String> res;
        String[] values = entryLine.split(",");
        if(columns.length != values.length) {
            System.out.println("Ошибка: в одной из строк данных количество значений отличается от количества столбцов. Текст строки: " + entryLine);
            return null;
        }
        res = new Entry<>(columns, values);
        return res;
    }

    @Override
    public void parseDictionaryColumn(String columnLine, Dictionary<String> dictionary) {
        String[] columnStringSplit = columnLine.split(":");
        if(columnStringSplit.length == 2) {
            String columnName = columnStringSplit[0];
            String[] columnValues = columnStringSplit[1].split(",");
            List<String> columnValuesList = new ArrayList<>();
            Collections.addAll(columnValuesList, columnValues);
            dictionary.addColumn(columnName, columnValuesList);
        } else {
            System.out.println("Ошибка: в одной из строк словаря не найдено знака-разделителя (\":\") после названия столбца. " +
                    "Строка не может быть включена в словарь. Текст строки: " + columnLine);
        }
    }

}
