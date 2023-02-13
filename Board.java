import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board
{
    char[][] board;
    char[][] solBoard;
    ArrayList<String> words;
    public Board(ArrayList<String> words)
    {
        this.words = words;
        generateBoard();
        showBoard(board);
        showBoard(solBoard);
    }

    private void generateBoard()
    {
        int max = getLongest() + 3;
        board = new char[max][max];
        solBoard = new char[max][max];
        for(String word: words)
        {
            boolean putting = true;
            while(putting)
            {
                int x = ThreadLocalRandom.current().nextInt(0, max + 1 - word.length());
                int y = ThreadLocalRandom.current().nextInt(0, max + 1 - word.length());
                if(valid(word, x, y))
                {
                    putWord(word, x, y);
                    putting = false;
                }
            }
        }
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j <board[i].length; j++)
            {
                if(board[i][j] == 0)
                {
                    board[i][j] = (char)ThreadLocalRandom.current().nextInt(65, 91);
                }
            }
        }
    }

    private void putWord(String word, int x, int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            board[y][x + i] = word.charAt(i);
            solBoard[y][x + i] = word.charAt(i);
        }
    }
    private boolean valid(String word, int x ,int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            if(board[y][x + i] != 0)
            {
                return false;
            }
        }
        return true;
    }
    private int getLongest()
    {
        int max = 0;
        for(String word: words)
        {
            if(word.length() > max)
            {
                max = word.length();
            }
        }
        return max;
    }

    public void showBoard(char[][] board)
    {
        String seperator = " ";
        String divider = " ";
        String blank = ".";
        for(int i = 0; i < board.length; i++)
        {
            System.out.print(divider + divider + divider + divider);
        }
        System.out.println(divider);
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j <board[i].length; j++)
            {
                
                System.out.print(seperator);
                if(board[i][j] == 0)
                {
                    System.out.print(" " + blank + " ");
                }
                else
                {
                    System.out.print(" " + board[i][j] + " ");
                }
            }
            System.out.print(seperator);
            System.out.println();
            for(int j = 0; j <board[i].length; j++)
            {
                System.out.print(divider + divider + divider + divider);
            }
            System.out.print(divider);
            System.out.println();
        }
        System.out.println("Word Bank:");
        for(String word: words)
        {
            System.out.print(word + ", ");
        }
    }
}