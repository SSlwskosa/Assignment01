package com.sstack.lkosa.assignments;

import java.util.Random;
import java.util.Scanner;
import static java.lang.System.exit;
import static java.lang.System.in;

public class NumberGame
{
    // Min value for Secret Number
    private static int minSecret;
    // Max value for Secret Number
    private static int maxSecret;
    // Nymber the player must find
    private static int secretNumber;
    // Player's most recent guess
    private static int playerGuess;
    // Player's best guess | FIXES danger Zone exploit
    public static  int bestGuess;
    // How many times the player can guess
    private static int guessesLeft;
    // What is the threshold for danger zone
    private static int guessRange;
    // Bonus guesses awarded to player if they were outside threshold and had no more guesses left
    private static int extraGuesses;
    // Is the player currently playing?
    private static boolean isPlaying;
    // Has the player used their bonus guesses yet?
    private static boolean safeLineUsed;
    // Allows printing debug / cheat information
    private static boolean showDebugInfo;

    public static void main(String[] args)
    {
        playGame();
    }

    /***
     * Initialize game with desired rules
     */
    private static void initializeGame()
    {
        minSecret     = 1;
        maxSecret     = 100;
        secretNumber  = newSecretNumber(minSecret,maxSecret);
        guessesLeft   = 10;
        guessRange    = 10;
        bestGuess     = maxSecret + 1;
        extraGuesses  = 5;
        isPlaying     = true;
        safeLineUsed  = false;
        showDebugInfo = false;

    }

    /***
     * Game logic
     * TODO: Refactor code to modularize logic
     */
    private static void playGame()
    {
        initializeGame();
        System.out.println(textColor("white","I have thought of a number, can you guess it?"));
        Scanner guess = new Scanner(in);
        while(isPlaying)
        {
            // Give play information on how many guesses they have left
            System.out.print(textColor("blue", "Guesses left: "));
            System.out.println(textColor("blue", Integer.toString(guessesLeft)));
            if(showDebugInfo)
            {
                System.out.println(textColor("white", String.format(" %d (%d)",bestGuess,secretNumber)));
            }
            // Ask for user input / guess
            System.out.println(textColor("cyan", "Enter your guess: "));
            playerGuess = guess.nextInt();
            if (playerGuess > maxSecret)
            {
                // Cap player guess to max possible value
                System.out.print(textColor("yellow","Guess is higher than possible maximum.\nGuess is set to: "));
                System.out.println(textColor("cyan",Integer.toString(maxSecret)));
            }
            else if(playerGuess < minSecret)
            {
                // Cap player guess to min possible value
                System.out.print(textColor("yellow","Guess is smaller than possible maximum.\nGuess is set to: "));
                System.out.println(textColor("cyan",Integer.toString(minSecret)));
            }
            if (playerGuess == secretNumber)
            {
                // Player won
                System.out.print(textColor("white","Congratulations! you have found my number: "));
                System.out.println(textColor("cyan",Integer.toString(secretNumber)));
                exitGame();
            }
            else if (secretNumber < playerGuess)
            {
                // Secret number is smaller than Player's guess
                System.out.printf("My number is %s\n",textColor("red","LOWER"));

                if(playerGuess - secretNumber < bestGuess)
                {
                    bestGuess = playerGuess - secretNumber;
                }
            }
            else if (secretNumber > playerGuess)
            {
                // Secret number is larger than Player's guess
                System.out.printf("My number is %s\n",textColor("yellow","HIGHER"));
                if (secretNumber - playerGuess < bestGuess)
                {
                    bestGuess = secretNumber - playerGuess;
                }
            }
            if (isPlaying)
            {
                switch(guessesLeft)
                {
                    case 1:
                        if(dangerZone(bestGuess))
                        {
                            // Player got close to finding the Secret Number, but still failed
                            System.out.println(textColor("white","You almost got me! My number was "));
                            System.out.println(textColor("yellow",Integer.toString(secretNumber)));
                            isPlaying = false;

                        }
                        else
                        {
                            if (safeLineUsed)
                            {
                                // Player could not find solution even after bonus guesses awarded
                                System.out.print(textColor("white","Sorry, the number I thought was "));
                                System.out.println(textColor("yellow",Integer.toString(secretNumber)));
                                isPlaying = false;
                            }
                            else
                            {
                                // Bonus Guesses award is triggered
                                System.out.print(textColor("white","Don't give up, here is "));
                                System.out.print(textColor("cyan",Integer.toString(extraGuesses)));
                                System.out.println(textColor("white"," extra guesses"));
                                safeLineUsed = true;
                                guessesLeft += extraGuesses-1;
                            }
                        }
                        break;

                    default:
                        // Keep reducing Guesses Left number
                        guessesLeft--;
                        break;
                }

            }
        }

    }

    /***
     * Decide if play would have a chance to find the number, safety net
     * @param closestGuess How far off the player is from the solution
     * @return TRUE/FALSE - Is the user within range of threshold
     */
    private static boolean dangerZone(int closestGuess)
    {
        return (closestGuess < guessRange);
    }

    /***
     * Generate new pseudo-random number
     * @param min Minimum value of Secret number
     * @param max Maximum value of Secret Number
     * @return Pseudo-random number specified by range
     */
    private static int newSecretNumber(int min, int max)
    {
        Random rnd = new Random();
        return rnd.nextInt((max + 1 - min) + min);
    }

    /***
     * Quit the process
     */
    private static void exitGame()
    {
        exit(0);
    }

    //-------- TEXT COLORS

    /***
     * Use colors in Console
     * @param color Name of color
     * @param message Message to be printed with chosen color
     * @return Color-formatted message
     */
    public static String textColor(String color,String message)
    {
        String colorCode = null;
        switch (color.toLowerCase())
        {
            case "red":
            colorCode = "\033[1;91m";
                break;

            case "green":
            colorCode = "\033[1;92m";
                break;

            case "blue":
                colorCode = "\033[1;94m";
                break;

            case "yellow":
                colorCode = "\033[1;93m";
                break;

            case "purple":
                colorCode = "\033[1;95m";
                break;

            case "cyan":
                colorCode = "\033[1;96m";
                break;

            case "white":
                colorCode = "\033[1;97m";
                break;

            default:
            colorCode = "\033[0m";
            break;

        }
        return String.format("%s%s%s",colorCode,message,"\033[0m");
    }








}
