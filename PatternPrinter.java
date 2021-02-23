package com.sstack.lkosa.assignments;
/***
 * Displays the required patterns in console
 * @author Lajos Kosa
 * @version 1.0
 */
public class PatternPrinter
{
    private static int separatorAmount = 9;
    public static void main(String[] args)
    {
        //region Modular Approach
        /*Pattern 1*/
        printText("1)",true);
        patternStairs("*",5,false);

        /*Pattern 2*/
        printText("2)",true);
        patternStairs("*",5,true);

        /*Pattern 3*/
        printText("3)",true);
        patternPyramid("*",7,2,false);

        /*Pattern 4*/
        printText("4)",true);
        patternPyramid("*",7,2,true);
        //endregion
    }

    /*
        ================================
                Modular Approach
        ================================
    */
    //region  Modular Approach - Methods

    /***
     * Custom method for console output
     * @param text Enteres text will be written to Console
     * @param addNewLine Flag to determine if new line is needed
     */
    private static void printText(String text, boolean addNewLine)
    {
        if (addNewLine)
            System.out.println(text);
        else
            System.out.print(text);
    }

    /***
     * Prints multiple text instances on a single line
     * @param text Entered text will be used as output
     * @param repeatAmount Specify how many time you want to repeat text
     * @return Concatenated text
     */
    private static String GetPatternLine(String text, int repeatAmount, boolean addNewLine)
    {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < repeatAmount; i++)
        {
            line.append(text);
        }
        
        if (addNewLine && repeatAmount != 0)
        {
            line.append("\n");
        }
        return line.toString();
    }

    /***
     * Allows user to print out LEFT padded text
     * @param text Entered text will be padded to the left
     * @param padAmount Speficy how many characters text will be padded by. "text" length must be taken into account.
     * @param addNewLine Flagto determine if new line is needed
     */
    private static void printPaddedText(String text, int padAmount, boolean addNewLine)
    {
        if (addNewLine)
        {
            System.out.printf("%" + padAmount + "s%n", text);
        }
        else
        {
            System.out.printf("%" + padAmount + "s", text);
        }
    }

    /***
     * Prints Stair pattern
     * @param icon Which icon will be used for the drawn pattern
     * @param steps Length of the longest step in characters
     * @param isReverse Flag to determine if upside-down pattern is required
     */
    private static void patternStairs(String icon,int steps, boolean isReverse)
    {
        if (isReverse)
        {
            //Depending on requirements this can be moved outside of method to ensure only pattern is drawn
            printText(GetPatternLine("•",separatorAmount,false),true);
            for (int i = 0; i < steps;steps--)
            {
                printText(GetPatternLine(icon,steps,true),false  );
            }
        }
        else
        {
            for (int i = 0; i < steps;i++)
            {
                printText(GetPatternLine(icon,i,true),false  );
            }
            //Depending on requirements this can be moved outside of method to ensure only pattern is drawn
            printText(GetPatternLine("•",separatorAmount,true),true);
        }
        separatorAmount++;
    }

    /***
     * Prints Pyramid pattern
     * @param icon Which icon will be used for the drawn pattern
     * @param steps Length of widest part of PYramid specified in characters
     * @param offset How many characters from start should the PYramid padded by
     * @param isReverse Flag to determine if upside-down pattern is required
     */
    private static void patternPyramid(String icon, int steps, int offset, boolean isReverse)
    {
        int paddingAdjust = offset * 2;
        if (steps % 2 == 0)
        {
            printText("Pyramid's base is not Odd, and it will not be centered.",true);
        }

        if (isReverse)
        {
            //Depending on requirements this can be moved outside of method to ensure only pattern is drawn
            printText(GetPatternLine("•",separatorAmount,false),true);
            for (int i = steps; i > 0; i--)
            {
                String line = GetPatternLine(icon,i,false);
                int paddingAmount = (steps + line.length() + paddingAdjust) / 2;
                if (i % 2 == 1)
                {

                    printPaddedText(line,paddingAmount, false  );
                    printText("",true);
                }
            }
        }
        else
        {
            for (int i = 0; i <= steps; i++)
            {
                if (i % 2 == 1)
                {
                    String line = GetPatternLine(icon,i,false);
                    int paddingAmount = (steps + line.length() + paddingAdjust) / 2;
                    printPaddedText(line,paddingAmount, false  );
                    printText("",true);
                }
            }
            //Depending on requirements this can be moved outside of method to ensure only pattern is drawn
            printText(GetPatternLine("•",separatorAmount,true),true);
        }
        separatorAmount++;
    }
    //endregion
}/*CLASS END*/
