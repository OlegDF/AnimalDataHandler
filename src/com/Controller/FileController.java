package com.Controller;

import com.Model.Dictionary;
import com.Model.Entry;
import com.Model.EntryChecker;
import com.Model.Parsers.Parser;
import com.Model.Rules.Rule;
import com.View.Displays.ResultsDisplay;
import com.View.FileReader;

import java.util.List;

public class FileController {

    private final Parser<String> parser;

    private final FileReader fileReader;
    private final EntryChecker entryChecker;
    private final Dictionary<String> dictionary;

    private final ResultsDisplay display;

    public FileController(Parser<String> parser, ResultsDisplay display) {
        this.parser = parser;
        this.fileReader = new FileReader();
        this.entryChecker = new EntryChecker();
        this.dictionary = new Dictionary<>();
        this.display = display;
        fillDictionary();
        display.startDisplay();
    }

    private void fillDictionary() {
        List<String> dictionaryStrings = fileReader.getStringsFromFile("characteristics_dictionary.txt");
        for(String columnLine: dictionaryStrings) {
            parser.parseDictionaryColumn(columnLine, dictionary);
        }
    }

    private void readRules() {
        List<String> ruleStrings = fileReader.getStringsFromFile("rules.txt");
        for(String ruleLine: ruleStrings) {
            Rule newRule = parser.parseRule(ruleLine, dictionary);
            entryChecker.addRule(newRule);
        }
    }

    private void readEntries(String dataFileName) {
        List<String> entryStrings = fileReader.getStringsFromFile(dataFileName);
        String[] columns = entryStrings.get(0).split(",");
        if(!dictionary.checkColumnList(columns)) {
            System.out.println("Ошибка: столбцы не соответствуют содержанию словаря. Текст строки: " + entryStrings.get(0));
        }
        for(int i = 1; i < entryStrings.size(); i++) {
            Entry<String> newEntry = parser.parseEntry(columns, entryStrings.get(i));
            entryChecker.addEntry(newEntry);
        }
    }

    public void processData(String dataFileName) {
        readRules();
        readEntries(dataFileName);
        display.displayCounts(entryChecker.countMatches());
    }

}
