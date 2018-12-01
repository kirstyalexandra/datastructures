package Lab04;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * A class that implements math operations utilizing a stack.
 *
 * @author Kirsty Alexandra Nguegang
 * @version 02/13/2018
 */
public class FunWithStack
{
    public void decimalToBinary()
    {
        System.out.println("DECIMAL TO BINARY CONVERTER");
        // TODO PROJECT #1
        Scanner keyboard = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        try
        {
            do
            {
                System.out.println("\nPlease enter a positive integer, or type \"stop\"");
                int decimalNumber = keyboard.nextInt();
                System.out.print(decimalNumber + " in binary is --> ");
                // YOUR CODE GOES HERE
                int mod = 0;
                while (decimalNumber > 0)
                {
                    mod = decimalNumber % 2;
                    if (mod == 0 || mod == 1)
                    {
                        stack.push(mod);
                        decimalNumber = decimalNumber / 2;
                    }
                }
                int num = 0;
                while (!stack.empty())
                {
                    num = stack.pop();
                    System.out.print(num);
                }
                System.out.println();
            } while (true);
        } catch (InputMismatchException ime)
        {
            System.out.println("Done multiplying\n");
        }
    }

    public void ancientMultiplier()
    {
        // TODO PROJECT #1
        // http://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication
        Stack<Integer> op1 = new Stack<>();
        Stack<Integer> op2 = new Stack<>();
        Scanner keyboard = new Scanner(System.in);
        NumberFormat nf = NumberFormat.getNumberInstance();
        try
        {
            do // use decimal format, to make output look pretty
            {
                System.out.println("\nPlease enter operand1, or type stop");
                int oper1 = keyboard.nextInt();
                System.out.println("Please enter operand2 ");
                int oper2 = keyboard.nextInt();
                int smallestNumber, largestNumber, op1Check, op2Check;
                int sum = 0;
                int multiple = 1;

                if (oper1 > oper2)
                {
                    largestNumber = oper1;
                    smallestNumber = oper2;
                } else
                {
                    largestNumber = oper2;
                    smallestNumber = oper1;
                }

                System.out.println("The smaller operand is: " + smallestNumber + "; and the larger operand is: " + largestNumber);
                System.out.println("--> Creating the mapping table: ");

                while (multiple <= smallestNumber)
                {
                    System.out.println(multiple + " --> " + nf.format(largestNumber));
                    op1.push(multiple);
                    op2.push(largestNumber);
                    multiple *= 2;
                    largestNumber *= 2;
                }

                System.out.println("--> Calculating result ");
                System.out.print(nf.format(oper1) + " * " + nf.format(oper2) + " is: ");
                while (!(op1.empty() || op2.empty()))
                {
                    op1Check = op1.pop();
                    op2Check = op2.pop();
                    if (smallestNumber >= op1Check)
                    {
                        smallestNumber -= op1Check;
                        sum += op2Check;
                        System.out.print(nf.format(op2Check));
                        if (!op2.empty() && op2.size() != 1)
                        {
                            System.out.print(" + ");
                        }
                    }

                }
                System.out.print(" = " + nf.format(sum));
            } while (true);
        } catch (InputMismatchException ime)
        {
            System.out.println("Done with conversion.\n");
        }

    }

    public ArrayList<Integer> noAdjacentDuplicates(int... input)
    {
        // TODO PROJECT #1
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean repeat = false;
        //pop when elements are not the same, and flag is true

        System.out.println("input = " + Arrays.toString(input));
        int index = 0;
        while (index < input.length)
        {
            if (stack.empty())
            {
                stack.push(input[index]);
                index++;
            } else
            {
                if (input[index] == stack.peek())
                {
                    repeat = true;
                    index++;
                } else if (repeat == false)
                {
                    stack.push(input[index]);
                    index++;
                } else if (repeat)
                {
                    stack.pop();
                    repeat = false;
                }
            }
        }
        if (!stack.isEmpty() && (repeat))
        {
            stack.pop();
        }
        while (!stack.empty())
        {
            result.add(0, stack.pop());
        }
        return result;
    }

    public static void main(String[] args)
    {
        FunWithStack funWithStack = new FunWithStack();
        System.out.println("*** DECIMAL TO BINARY CONVERTER ***");
        funWithStack.decimalToBinary();
        System.out.println("*** ANCIENT MULTIPLIER ***");
        funWithStack.ancientMultiplier();

        System.out.println("*** ELIMINATING ADJACENT DUPLICATES ***");

        System.out.println("--> testcase #1");
        ArrayList<Integer> result = funWithStack.noAdjacentDuplicates(1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #2");
        result = funWithStack.noAdjacentDuplicates(1, 9, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        expected.add(1);
        expected.add(9);
        expected.add(5);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #3");
        result = funWithStack.noAdjacentDuplicates(1, 1, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        expected.add(5);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #4");
        result = funWithStack.noAdjacentDuplicates(1, 1, 1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("Done!");
    }
}