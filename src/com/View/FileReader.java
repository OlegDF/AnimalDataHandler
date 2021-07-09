package com.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, построчно считывающий текстовые файлы.
 */
public class FileReader {

    public FileReader() {}

    /**
     * Считывает файл по указанному относительному пути и возвращает его содержимое в виде списка строк.
     *
     * @param fileName - путь к файлу.
     * @return список строк в файле.
     */
    public List<String> getStringsFromFile(String fileName) {
        List<String> res = new ArrayList<>();
        try {
            BufferedReader lineReader = new BufferedReader(new java.io.FileReader(fileName));String line = lineReader.readLine();
            while (line != null) {
                res.add(line);
                line = lineReader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

}
