package com.alexander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        ChekInputParametr chekInputParametr = new ChekInputParametr();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String enter;
        while (true) {
            enter = bufferedReader.readLine();
            if(enter.isEmpty()) break;
                chekInputParametr.showAll(enter);
        }
    }
}
