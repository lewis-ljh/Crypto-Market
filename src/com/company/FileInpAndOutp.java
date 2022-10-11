package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This class is abstract as an object cannot be created from it
abstract class FileInpAndOutp {
    public static void writeInput(String Input){
        try {
            FileWriter Age = new FileWriter("DepositHistory.txt", true); //Creates the file
            Age.write(Input + "\n");
            Age.flush();
            Age.close();
//In case of an error
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void sortInputFile(){
//Creates an arraylist to store the values while sorting

        List<String> inputsList = new ArrayList<>();
        try {
//Reads the file with a buffer to realise it's at the end
            BufferedReader reader = new BufferedReader(new FileReader("DepositHistory.txt"));
            String Ages = reader.readLine();
            int counter = 0; //finds the length
            while (Ages != null) {
                inputsList.add(Ages);
                Ages = reader.readLine();
                counter++;
            }
//Puts the values into an array to be sorted
            String[] inputArray = new String[inputsList.size()];
            inputArray = inputsList.toArray(inputArray);
//Bubble sort with a loop inside a loop
            for(int i = 0; i<counter-1; i++)
            {
                for (int j = i+1; j<inputsList.size(); j++)
                {
                    if((inputArray[i].compareTo(inputArray[j]))>0)
                    {
                        String tempSort = inputArray[i];
                        inputArray[i] = inputArray[j];
                        inputArray[j] = tempSort;
                    }
                }

            }
//Adds the sorted array back to the file
            FileWriter myWriter = new FileWriter("DepositHistory.txt", false);
            for (String s : inputArray) {
                myWriter.write(s + "\n");
            }
            myWriter.flush();
            myWriter.close();
//In case of an error
        } catch(Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
    }
//Outputs file
    public static void OutputFile() throws IOException {
        BufferedReader OutputFile= new BufferedReader(new FileReader("DepositHistory.txt"));
        Scanner TextFile = new Scanner(OutputFile);
        while (TextFile.hasNextLine()){
            String Output = TextFile.nextLine();
            System.out.println(Output);
        }
        OutputFile.close();
    }
}
