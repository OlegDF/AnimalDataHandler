package com.View.Displays;

import java.util.List;

/**
 * Интерфейс класса, который отображает результаты работы программы для каждого правила.
 */
public interface ResultsDisplay {

    void startDisplay();
    void displayCounts(List<Integer> counts);

}
