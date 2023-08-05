import java.util.Scanner;

/**
 * @author Husain Raja rajaho@mail.uc.edu
 */

public class SafeInput
{
    /**
     * Design constraints for our SafeInput file of Static Methods:
     *
     * All methods block and repeat until they get a valid input
     *
     *
     * We pass in the Scanner - we will call that parameter pipe
     * We usually have a String prompt next
     * then we have a numeric low and high range values OR
     * a RegEx String pattern to test the input against
     *  public static TYPE METHOD_NAME( Param list TYPE:ParamName
     *
     *  Examples:
     *  public static String getNonZeroLengthString(Scanner pipe, String prompt)
     *
     *  public static int getInt(Scanner pipe, String prompt)
     *  public static int getDouble(Scanner pipe, String prompt)
     *  public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
     *  public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
     *  public static boolean getYNConfirm(Scanner pipe, String prompt)
     *  public static String getRegExString(Scanner in, String prompt, String regEx)
     *
     *  public static String getPassWord(Scanner in, String prompt)
     *  public static String getMinLenString(Scanner in, String prompt, int minLength)
     *  public static String getRangedLenString(Scanner in, String prompt, int minLength, int maxLength)
     */

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;
    }

    /**
     * Get an integer value from the user with no constraints
     *
     * @param pipe Scanner to use for input
     * @param prompt User prompt
     * @return an int value provided by the user
     */

    public static int getInt(Scanner pipe, String prompt)
    {
        int retValue = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine(); // clear key buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not: " + trash);
            }
        }while (!done);

        return retValue;
    }

    /**
     * Get a double value from the user with no constraints
     *
     * @param pipe Scanner to use for input
     * @param prompt User prompt
     * @return a double value provided by the user
     */

    public static double getDouble(Scanner pipe, String prompt)
    {
        double retValue = 0.0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine(); // clear key buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not: " + trash);
            }
        }while (!done);

        return retValue;
    }

    /**
     * Get an integer value from the user within a specified inclusive low - high range
     *
     * @param pipe Scanner to use for input
     * @param prompt User prompt
     * @param low    low value for the range
     * @param high   high value for the range
     * @return an int value provided by the user within the specified range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retValue = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine(); // clear key buffer
                if(retValue >= low && retValue <= high)
                    done = true;
                else
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "] not: " + retValue);
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not: " + trash);
            }
        }while (!done);

        return retValue;
    }

    /**
     * Get a double value from the user within a specified inclusive low - high range
     *
     * @param pipe Scanner to use for input
     * @param prompt User prompt
     * @param low    low value for the range
     * @param high   high value for the range
     * @return a double value provided by the user within the specified range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retValue = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine(); // clear key buffer
                if(retValue >= low && retValue <= high)
                    done = true;
                else
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "]: " + retValue);
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not: " + trash);
            }
        }while (!done);

        return retValue;
    }

    /**
     * Get a [Y/N] confirmation from the user
     *
     * @param pipe Scanner to use for input
     * @param prompt User prompt
     * @return true for yes, false for no
     */

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        boolean retVal = true;
        String response = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if(response.equalsIgnoreCase("Y"))
            {
                done = true;
                retVal = true;
            }
            else if(response.equalsIgnoreCase("N"))
            {
                done = true;
                retVal = false;
            }
            else {
                System.out.println("You must answer [Y/N]! Not: " + response);
            }

        }while (!done);

        return retVal;
    }

    /**
     * Gets a string that matches a certain regular expression
     *
     * @param pipe Scanner to use for input
     * @param prompt User prompt
     * @param regExPattern Pattern which input is checked against
     * @return A string value that matches the regEx pattern
     */
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern)
    {
        String retVal = "";
        boolean gotAVal = false;

        do
        {
            System.out.print("\n" + prompt + ": ");
            retVal = pipe.nextLine();

            if(retVal.matches(regExPattern))
            {
                gotAVal = true;
            }
            else
            {
                System.out.println("\nInvalid input: " + retVal);
            }

        }while(!gotAVal);

        return retVal;
    }

    /**
     * Creates an elaborate header
     *
     * @param msg The message that should be in the header
     */

    public static void prettyHeader(String msg)
    {
        for (int i = 0; i < 60; i++) {
            System.out.printf("*");
        }

        System.out.println("\n");

        for (int i = 0; i < 3; i++) {
            System.out.printf("*");
        }

        int spacing = (54 - msg.length()) / 2;
        String space = " ";
        System.out.printf("%" + spacing + "s", space);
        System.out.printf("%s", msg);
        System.out.printf("%" + spacing + "s", space);

        for (int i = 0; i < 3; i++) {
            System.out.printf("*");
        }

        System.out.println("\n");

        for (int i = 0; i < 60; i++) {
            System.out.printf("*");
        }

    }
}

