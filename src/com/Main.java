package com;

import com.Controller.FileController;
import com.Model.Parsers.Parser;
import com.Model.Parsers.ParserStandard;
import com.View.Displays.ResultsDisplay;
import com.View.Displays.TextlineDisplay;

public class Main {

    public static void main(String[] args) {
        Parser<String> parser = new ParserStandard();
        ResultsDisplay display = new TextlineDisplay();
        FileController controller = new FileController(parser, display);
        controller.processData("animals_data.csv");
    }

}
