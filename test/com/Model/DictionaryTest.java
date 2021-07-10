package com.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    private Dictionary<String> dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary<>();
        List<String> weightValues = new ArrayList<>();
        weightValues.add("ЛЕГКОЕ");
        weightValues.add("СРЕДНЕЕ");
        weightValues.add("ТЯЖЕЛОЕ");
        dictionary.addColumn("ВЕС", weightValues);
        List<String> heightValues = new ArrayList<>();
        heightValues.add("МАЛЕНЬКОЕ");
        heightValues.add("НЕВЫСОКОЕ");
        heightValues.add("ВЫСОКОЕ");
        dictionary.addColumn("РОСТ", heightValues);
        List<String> typeValues = new ArrayList<>();
        typeValues.add("ТРАВОЯДНОЕ");
        typeValues.add("ПЛОТОЯДНОЕ");
        typeValues.add("ВСЕЯДНОЕ");
        dictionary.addColumn("ТИП", typeValues);
    }

    @Test
    void addColumn() {
        assertFalse(dictionary.checkColumnName("ЦВЕТ"));
        List<String> colorValues = new ArrayList<>();
        colorValues.add("КРАСНОЕ");
        colorValues.add("СИНЕЕ");
        dictionary.addColumn("ЦВЕТ", colorValues);
        assertTrue(dictionary.checkColumnName("ЦВЕТ"));
        assertTrue(dictionary.checkValue("ЦВЕТ", "КРАСНОЕ"));
        assertFalse(dictionary.checkValue("ЦВЕТ", "ЗЕЛЕНОЕ"));
    }

    @Test
    void checkColumnName() {
        assertTrue(dictionary.checkColumnName("ВЕС"));
        assertTrue(dictionary.checkColumnName("РОСТ"));
        assertTrue(dictionary.checkColumnName("ТИП"));
        assertFalse(dictionary.checkColumnName("ЦВЕТ"));
    }

    @Test
    void checkColumnList() {
        String[] columnNames1 = {"ВЕС", "РОСТ", "ТИП"};
        String[] columnNames2 = {"ВЕС", "РОСТ"};
        String[] columnNames3 = {"ВЕС", "РОСТ", "ЦВЕТ"};
        assertTrue(dictionary.checkColumnList(columnNames1));
        assertFalse(dictionary.checkColumnList(columnNames2));
        assertFalse(dictionary.checkColumnList(columnNames3));
    }

    @Test
    void checkValue() {
        assertTrue(dictionary.checkValue("ВЕС", "СРЕДНЕЕ"));
        assertFalse(dictionary.checkValue("ВЕС", "ЗЕЛЕНОЕ"));
        assertTrue(dictionary.checkValue("ТИП", "ПЛОТОЯДНОЕ"));
        assertFalse(dictionary.checkValue("ТИП", "ЗЕЛЕНОЕ"));
    }
}