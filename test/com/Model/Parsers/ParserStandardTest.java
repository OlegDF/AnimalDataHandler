package com.Model.Parsers;

import com.Model.Dictionary;
import com.Model.Entry;
import com.Model.Rules.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserStandardTest {

    private Parser<String> parser;
    private Dictionary<String> dictionary;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        parser = new ParserStandard();
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
    void parseRule() {
        Rule rule1 = parser.parseRule("ТИП=ТРАВОЯДНОЕ", dictionary);
        assertEquals(rule1.getClass(), RuleEquals.class);
        Rule rule2 = parser.parseRule("ТИП!=ТРАВОЯДНОЕ", dictionary);
        assertEquals(rule2.getClass(), RuleNotEquals.class);
        Rule rule3 = parser.parseRule("ТИП=ТРАВОЯДНОЕ,ПЛОТОЯДНОЕ", dictionary);
        assertEquals(rule3.getClass(), RuleOr.class);
        Rule rule4 = parser.parseRule("ТИП!=ТРАВОЯДНОЕ,ПЛОТОЯДНОЕ", dictionary);
        assertEquals(rule4.getClass(), RuleAnd.class);
        Rule rule5 = parser.parseRule("ТИП=ТРАВОЯДНОЕ,ПЛОТОЯДНОЕ;РОСТ=МАЛЕНЬКОЕ", dictionary);
        assertEquals(rule5.getClass(), RuleAnd.class);
        Rule rule6 = parser.parseRule("ТИПТРАВОЯДНОЕ,ПЛОТОЯДНОЕ;РОСТ=МАЛЕНЬКОЕ", dictionary);
        assertNull(rule6);
        Rule rule7 = parser.parseRule("ТИП=РАСТЕНИЕ;РОСТ=МАЛЕНЬКОЕ", dictionary);
        assertNull(rule7);
        Rule rule8 = parser.parseRule("ЦВЕТ=КРАСНОЕ;РОСТ=МАЛЕНЬКОЕ", dictionary);
        assertNull(rule8);
    }

    @Test
    void parseEntry() {
        String[] columns = {"ВЕС", "РОСТ", "ТИП"};
        Entry entry = parser.parseEntry(columns, "СРЕДНЕЕ,НЕВЫСОКОЕ,ВСЕЯДНОЕ");
        assertEquals(entry.getValue("ВЕС"), "СРЕДНЕЕ");
        assertEquals(entry.getValue("РОСТ"), "НЕВЫСОКОЕ");
        assertEquals(entry.getValue("ТИП"), "ВСЕЯДНОЕ");
        entry = parser.parseEntry(columns, "СРЕДНЕЕ,НЕВЫСОКОЕ");
        assertNull(entry);
    }

    @Test
    void parseDictionaryColumn() {
        parser.parseDictionaryColumn("ЦВЕТ:КРАСНОЕ,СИНЕЕ", dictionary);
        assertTrue(dictionary.checkColumnName("ЦВЕТ"));
        assertTrue(dictionary.checkValue("ЦВЕТ", "КРАСНОЕ"));
        assertFalse(dictionary.checkValue("ЦВЕТ", "ЗЕЛЕНОЕ"));
    }

}