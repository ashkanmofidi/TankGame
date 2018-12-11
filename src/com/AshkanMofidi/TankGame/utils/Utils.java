package com.AshkanMofidi.TankGame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
    This class has bunch of helper functions that are going to assist me in my game
 */
public class Utils {
    public static String loadFileAsString(String path){
        /*
            StringBuilder allows me to add character to String very easily
         */
        StringBuilder builder = new StringBuilder();

        /*
            We need a try and catch in case any methods happen
         */
        try{
            /*
                Here is the thing that we have to load from a file
             */
            BufferedReader br = new BufferedReader(new FileReader(path));
            //This is the current line of the file that we are working on
            String line;
            while((line = br.readLine()) != null){
                //I want to use my string builder to
                builder.append(line + "\n");
            }
            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        //Now we have to return the file that we just loaded
        return builder.toString();
    }

    /*
        This method is going to take a String as an argument like "5" and convert it to integer 5
     */
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch (NumberFormatException e){
            //This prints the error to the screen
            e.printStackTrace();
            //So that our program doesn't crash we return 0 as default
            return 0;
        }
    }
}
