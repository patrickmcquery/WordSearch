import java.util.ArrayList;
import java.util.Scanner;

public class WordSearch
{
    static Board board;
    public static void main(String[] args)
    {
        printIntro();
        boolean running = true;
        while(running)
        {
            System.out.println("Please select an option:");
            System.out.println("(G)enerate a new word search");
            System.out.println("(P)rint out your word search");
            System.out.println("(S)how the solution to your word search");
            System.out.println("(Q)uit the program");
            Scanner console = new Scanner(System.in);
            String resp = console.nextLine();
            System.out.println();
            switch (resp.toLowerCase().charAt(0))
            {
                case 'g':
                    board = newBoard();
                    break;
                case 'p':
                    printBoard(false);
                    break;
                case 's':
                    printBoard(true);
                    break;
                case 'q':
                    running = false;
                    break;
                default:
                    System.out.println("Invalid response given, please try again.");
            }

            
        }

    }

    public static Board newBoard()
    {

        System.out.println("How many words would you like to add?");
        Scanner console = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>(0);
        int wordCount = console.nextInt();
        console.nextLine();
        System.out.println("Please enter those words:");
        for(int i = 0; i < wordCount; i++)
        {
            String resp = console.nextLine();
            words.add(resp.toUpperCase());
        }
        return new Board(words);
    }
    public static void printBoard(boolean solution)
    {
        if(board == null)
        {
            System.out.println("Please create a new board first.");
        }
        else
        {
            board.showBoard(solution);
        }
    }
    public static void printIntro()
    {
        System.out.println("Welcome to my word search generator!");
        System.out.println("This program will allow you to generate your own word search puzzle");
    }
}