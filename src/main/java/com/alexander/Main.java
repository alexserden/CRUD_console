package com.alexander;

import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
           ChekInputParametr chekInputParametr = new ChekInputParametr();
            if(args.length!=0){
            chekInputParametr.showAll(args);
            }else{
                System.out.println("Начальные параметры не заданы");
            }
    }
}
