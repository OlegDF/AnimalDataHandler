package com.View.Displays;

import java.util.List;

/**
 * Класс, выводящий результаты работы программы в консоль стандартного вывода.
 */
public class TextlineDisplay implements ResultsDisplay {

    @Override
    public void startDisplay() {
        System.out.println("Начинается подсчет по правилам...");
    }

    @Override
    public void displayCounts(List<Integer> counts) {
        for(int i = 0; i < counts.size(); i++) {
            if(counts.get(i) > 0) {
                System.out.println("Правилу " + (i + 1) + " соответствуют " + counts.get(i) + " строк.");
            } else {
                System.out.println("Правило " + (i + 1) + " не было проверено.");
            }
        }
    }

}
