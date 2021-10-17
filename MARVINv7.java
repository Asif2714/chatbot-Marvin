/*
===========================
Author:  Abdullah Al Asif
Date:    01 December 2020
Version: 1
Purpose: A java porgram to develop a Help Bot which will respond to the questions made by the user.
===========================
*/

import java.util.Scanner; // Needed to make Scanner available
import java.io.*;

//Creation of a new type: questionRecord
//
class questionRecord
{
    String trigger;
    String question;
    String answer;
    int uses;
}


public class MARVINv7 {
    
    public static void main (String [] args) throws IOException
    {
        introBot();
        int choice = inputInt("Please enter your choice: (1/2)");

        System.out.println(choice);

        if (choice==1)
        {
            System.out.println("Initiating Normal Mode, please wait...");
            System.out.println("");
            normalMode();

        }
        else if (choice==2)
        {
            System.out.println("Initiating ADMIN MODE, please wait for further instructions...");
            System.out.println("");
            adminMode();
        }
        else 
        {
            System.out.println("Invalid choice number entered, please try again.");
        }


    }//END of main

    //The method which uses regular operation of the chatbot by using information that is stored inside the chatbot program
    //
    public static void normalMode()
    {
        //Initializing records and storing them in an array
        //
        questionRecord q1 = createRecord("language", "What programming language is MARVIN coded on?", "MARVIN is programmed in the Java programming language");
        questionRecord q2 = createRecord("development", "What is the status of MARVIN's development?", "MARVIN is still in the development stage.");
        questionRecord q3 = createRecord("intelligent", "Is MARVIN as intelligent as a human being?", "MARVIN is not even close to an intelligent animal, let alone human beings.");
        
        questionRecord [] arrayOfRecord = {q1, q2, q3}; 


        for (int counter = 0; counter < arrayOfRecord.length; counter++)
        {
            System.out.println(arrayOfRecord[counter].trigger + ": "+ arrayOfRecord[counter].question);
        }

        
        String continuous = "Y"; //The variable responsible for continuation of the while loop below

        
        //While loop starts checkign the reply and giving answers by using conditional statement
        //
        while (continuous.equals("Y")|continuous.equals("y"))
        {
            System.out.println("");
            System.out.println("The keywords are given below:");

            //For loop to print out the available trigger words.
            //
            for (int counter = 0; counter < arrayOfRecord.length; counter++)
            {
                System.out.println(arrayOfRecord[counter].trigger);   
            }


            System.out.println("");

            System.out.println("Input not matching the list will not be accepted.");
            String reply = inputString("Please enter a keyword: ");


            //for loop responsible for bubble sort
            //
            for(int counter = 0; counter < arrayOfRecord.length; counter++)
            {
                if (reply.equals(getTrigger(arrayOfRecord[counter])))
                {
                    printArrayOfRecord(counter, arrayOfRecord);
                    
                    arrayOfRecord[counter].uses = arrayOfRecord[counter].uses +1;

                }

            } 
            

            //Asks user to try again after each question and answer
            //
            continuous = inputString("If you want to ask again, press Y.");

            

        }

        sorting(arrayOfRecord);

        //Goodbye Message
        System.out.println("");
        System.out.println("Thankyou for using MARVIN Bot.");

        return;
    
    }//END of normalMode

    //Method which executes along with file input and output
    //
    public static void adminMode() throws IOException
    {
        String filename = inputString("Enter the name of the file you want to use (including the filename extension, eg .txt).");
        BufferedReader inputStream = new BufferedReader (new FileReader(filename));

        //setting the number of trigger-response records
        //
        final int questionCount = Integer.parseInt(inputStream.readLine());

        questionRecord [] arrayOfRecord = new questionRecord[questionCount];

        for(int counter = 0; counter < questionCount; counter++)
        {
            //reading a line one by one in each loop 
            //
            String questionInput = inputStream.readLine();
            
            //questionInput divided by commas into parts in an array 
            //
            String [] questionComponents = questionInput.split(",");
            
            //Creating records
            //
            questionRecord q = createRecord(questionComponents[0], questionComponents[1],questionComponents[2]);
            arrayOfRecord [counter] = q;
        }

        inputStream.close();

        for (int counter = 0; counter < arrayOfRecord.length; counter++)
        {
            System.out.println(arrayOfRecord[counter].trigger + ": "+ arrayOfRecord[counter].question);
        }

        String continuous = "Y"; //The variable responsible for continuation of the while loop below

            
        //While loop starts checking the reply and giving answers by using conditional statement
        //
        while (continuous.equals("Y")|continuous.equals("y"))
        {
            System.out.println("");
            System.out.println("The keywords are given below:");

            //For loop to print out the available trigger words.
            //
            for (int counter = 0; counter < arrayOfRecord.length; counter++)
            {
                System.out.println(arrayOfRecord[counter].trigger);   
            }


            System.out.println("");

            System.out.println("Input not matching the list will not be accepted.");
            String reply = inputString("Please enter a keyword: ");


            //for loop responsible for bubble sort
            //
            for(int counter = 0; counter < arrayOfRecord.length; counter++)
            {
                if (reply.equals(getTrigger(arrayOfRecord[counter])))
                {
                    printArrayOfRecord(counter, arrayOfRecord);
                        
                    arrayOfRecord[counter].uses = arrayOfRecord[counter].uses +1;

                }

            } 
                

            //Asks user to try again after each question and answer
            //
            continuous = inputString("If you want to ask again, press Y.");

                

        }

        sorting(arrayOfRecord);

        String outputPrompt = inputString("Do you want to generate a file about the results? Y/N");
        if (outputPrompt.equals("Y")|outputPrompt.equals("y"))
        {
            String name = "output";
            PrintWriter outputStream = new PrintWriter(new FileWriter(name + ".txt"));
            outputStream.println("The results are in the order of most used to the least:");
            outputStream.println(sortingText(arrayOfRecord));
            outputStream.close();

            System.out.println("Your request is accepted. A file named output.txt is in the source directory which contains the results.");
                
        }

        //Goodbye Message
        System.out.println("");
        System.out.println("Thankyou for using MARVIN Bot.");

        return;

    }//END of adminMode 

    
    //Method which is used to sort the records and print out the keywords based on their most usefulness
    //
    public static void sorting(questionRecord[] array)
    {
        for (int counter = 0; counter < array.length; counter++)
        {
            System.out.println("Keyword: "+ array[counter].trigger + " is used " + array[counter].uses + " times.");
        }

        boolean changed = true;
        int pass = 0;

        //while and for loop implementing the bubble sorting
        //
        while ((pass <= (array.length)-2) & (changed = true))
        {
            changed = false;

            for (int position = 0; position <= ((array.length)-2);  position++)
            {
                if (array[position + 1].uses > array[position].uses)
                {
                    questionRecord temp = array[position];
                    array[position] = array[position+1];
                    array[position+1] = temp;

                    changed = true;

                }
            }
            pass = pass +1;

        }

        System.out.println("");
        
        for(int counter = 0; counter < array.length; counter++)
        {
            System.out.println("The the keyword ranked No." + (counter+1) + " is: " + array[counter].trigger);
        }

    }//END of sorting

    //
    //
    public static String sortingText(questionRecord[] array)
    {
        boolean changed = true;
        int pass = 0;

        //while and for loop implementing the bubble sorting
        //
        while ((pass <= (array.length)-2) & (changed = true))
        {
            changed = false;

            for (int position = 0; position <= ((array.length)-2);  position++)
            {
                if (array[position + 1].uses > array[position].uses)
                {
                    questionRecord temp = array[position];
                    array[position] = array[position+1];
                    array[position+1] = temp;

                    changed = true;

                }
            }
            pass = pass +1;

        }

        //Text which will be used for file output
        //
        String text = "";
        for (int counter = 0;counter < array.length; counter++)
        {
            text = (text + array[counter].trigger + " is used "+ array[counter].uses + " times. ");
        }

        //Returning the text for file output
        return text;
    }//END of sortingText


    //Method which prints initial greeting messages from the chatbot
    //
    public static void introBot()
    {
        System.out.println("Hello, I am MARVIN, your personal assistance Bot.");
        System.out.println("1: NORMAL MODE - Executes program based on information is available in the chatbot program.");
        System.out.println("2: ADMIN MODE - Enables File Input/Output.");
        
        
    }//END of introBot



    //Method to make the records and initialize the fields of the records.
    //
    public static questionRecord createRecord(String trig, String ques, String ans)
    {
        questionRecord record = new questionRecord();

        setTrigger(record, trig);
        setQuestion(record, ques);
        setAnswer(record, ans);
        setUsesTo0(record);

        return record;

    }//END of questionRecord

    //Setter method to set the values of the trigger field
    //
    public static questionRecord setTrigger(questionRecord record,String trigSet)
    {
        record.trigger = trigSet;
        return record;
    }//END of setTrigger

    //Setter method to set the values of the question field
    //
    public static questionRecord setQuestion(questionRecord record, String quesSet)
    {
        record.question = quesSet;
        return record;
    }//END of setQuestion

    //Setter method to set the values of the answer field
    //
    public static questionRecord setAnswer(questionRecord record, String ansSet)
    {
        record.answer = ansSet;
        return record;
    }//END of setAnswer

    public static questionRecord setUsesTo0(questionRecord record)
    {
        record.uses = 0;

        return record;
    }//END of setUsesTo0

    
         
    
    //Method which gets response form the createRecord methods
    //
    public static String getAnswer(questionRecord record)
    {
        return record.answer;

    }//END of getAnswer
    
    //Method which gets trigger from the createRecord methods
    //
    public static String getTrigger (questionRecord record)
    {
        return record.trigger;

    }//END of getTrigger

    //Method to print out the detailed reply as a response to the trigger
    //
    public static void printArrayOfRecord(int index, questionRecord[] array)
    {
        System.out.println("You asked for: "+ array[index].trigger + ". Answer: " + array[index].answer);
    }//END of printArrayOfRecord

    //Method for getting a String input from the user after printing a message
    //
    public static String inputString(String message)
    {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);

        String stringInput = scanner.nextLine();

        return stringInput;
    }//END of inputString

    //Method for getting a integer input
    //
    public static int inputInt(String message)
    {
        String stringInput = inputString(message);

        int intOutput = Integer.parseInt(stringInput);

        return intOutput;
    }//END of inputInt


}//END of main


