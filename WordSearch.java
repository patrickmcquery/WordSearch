/*
 * This is a word search generator. It can generate word searches
 * of variable size, depending on the length of words you use.
 * You can also read from a file to generate the word search.
 * You can also save the word search to a file for later use.
 * 
 * Patrick McQuery
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            System.out.println("(R)ead words from a file");
            System.out.println("(P)rint out your word search");
            System.out.println("(S)how the solution to your word search");
            System.out.println("Sa(v)e your word search.");
            System.out.println("(Q)uit the program");
            Scanner console = new Scanner(System.in);
            String resp = console.nextLine();
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
                case 'r':
                    board = readBoard();
                    break;
                case 'v':
                    saveBoard();
                    break;
                case 'q':
                    running = false;
                    break;
                default:
                    System.out.println("Invalid response given, please try again.");
            }

            
        }

    }
    /*
     * Starts the generation of a new board with the words given by the user.
     */
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
    /*
     * Reads words from a file to generate a board.
     */
    public static Board readBoard()
    {
        System.out.println("What is the file name you would like to read words from?\nPlease include the file extension.");
        Scanner console = new Scanner(System.in);
        String resp = console.nextLine();
        Scanner file;
        ArrayList<String> words = new ArrayList<String>(0);
        try 
        {
            file = new Scanner(new File(resp));
            while (file.hasNextLine())
            {
                String data = file.nextLine();
                words.add(data.toUpperCase());
            }
            file.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File with that name not found. Please enter a valid file name.");
        }
        return new Board(words);
    }
    /*
     * Prints your current board to the console.
     */
    public static void printBoard(boolean solution)
    {
        if(board == null)
        {
            System.out.println("\nPlease create a new board first.\n");
        }
        else
        {
            board.showBoard(solution);
        }
    }
    /*
     * Saves your current board to a file.
     */
    public static void saveBoard()
    {
        if(board == null)
        {
            System.out.println("\nPlease create a new board first.\n");
            return;
        }
        System.out.println("What would you like to name your file?\nPlease include the file extenstion.");
        Scanner console = new Scanner(System.in);
        String resp = console.nextLine();
            File file = new File(resp);
            try 
            {
                new File(resp).createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write(board.toString());
                writer.close();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
    }
    public static void printIntro()
    {
        System.out.println("Welcome to my word search generator!");
        System.out.println("This program will allow you to generate your own word search puzzle");
    }
}