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
        while(!generateBoard());
    }

    private boolean generateBoard()
    {
        int max = getLongest() + 2;
        board = new char[max][max];
        solBoard = new char[max][max];
        for(String word: words)
        {
            boolean putting = true;
            int tries = 0;
            while(putting)
            {
                tries++;
                if(tries > 10000)
                {
                    System.out.println("Retrying generation.");
                    return false;
                }
                int x;
                int y;
                int rand = ThreadLocalRandom.current().nextInt(0, 4);
                switch(rand)
                {
                    case 0:
                    x = ThreadLocalRandom.current().nextInt(0, max - word.length() - 1);
                    y = ThreadLocalRandom.current().nextInt(0, max);
                    if(checkWordLR(word, x, y))
                    {
                        putWordLR(word, x, y);
                        putting = false;
                    }
                    break;
                    case 1:
                    x = ThreadLocalRandom.current().nextInt(0, max);
                    y = ThreadLocalRandom.current().nextInt(0, max - word.length() - 1);
                    if(checkWordUD(word, x, y))
                    {
                        putWordUD(word, x, y);
                        putting = false;
                    }
                    break;
                    case 2:
                    x = ThreadLocalRandom.current().nextInt(0 + word.length() + 1, max);
                    y = ThreadLocalRandom.current().nextInt(0, max);
                    if(checkWordRL(word, x, y))
                    {
                        putWordRL(word, x, y);
                        putting = false;
                    }
                    break;
                    case 3:
                    x = ThreadLocalRandom.current().nextInt(0, max);
                    y = ThreadLocalRandom.current().nextInt(0 + word.length() + 1 , max);
                    if(checkWordDU(word, x, y))
                    {
                        putWordDU(word, x, y);
                        putting = false;
                    }
                    break;
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
        return true;
    }

    private void putWordLR(String word, int x, int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            board[y][x + i] = word.charAt(i);
            solBoard[y][x + i] = word.charAt(i);
        }
    }
    private boolean checkWordLR(String word, int x ,int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            if(board[y][x + i] != 0 && board[y][x + i] != word.charAt(i))
            {
                return false;
            }
        }
        return true;
    }
    private void putWordRL(String word, int x, int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            board[y][x - i] = word.charAt(i);
            solBoard[y][x - i] = word.charAt(i);
        }
    }
    private boolean checkWordRL(String word, int x ,int y)
    {

        for(int i = 0; i < word.length(); i++)
        {
            if(board[y][x - i] != 0 && board[y][x - i] != word.charAt(i))
            {
                return false;
            }
        }
        return true;
    }
    private void putWordUD(String word, int x, int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            board[y + i][x] = word.charAt(i);
            solBoard[y + i][x] = word.charAt(i);
        }
    }
    private boolean checkWordUD(String word, int x ,int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            if(board[y + i][x] != 0 && board[y + i][x] != word.charAt(i))
            {
                return false;
            }
        }
        return true;
    }
    private void putWordDU(String word, int x, int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            board[y - i][x] = word.charAt(i);
            solBoard[y - i][x] = word.charAt(i);
        }
    }
    private boolean checkWordDU(String word, int x ,int y)
    {
        for(int i = 0; i < word.length(); i++)
        {
            if(board[y - i][x] != 0 && board[y - i][x] != word.charAt(i))
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

    public void showBoard(boolean solution)
    {
        char[][] tempboard;
        if(!solution)
        {
            tempboard = board;
        }
        else
        {
            tempboard = solBoard;
        }
        String seperator = " ";
        String divider = " ";
        String blank = ".";
        for(int i = 0; i < tempboard.length; i++)
        {
            System.out.print(divider + divider + divider + divider);
        }
        System.out.println(divider);
        for(int i = 0; i < tempboard.length; i++)
        {
            for(int j = 0; j <tempboard[i].length; j++)
            {
                
                System.out.print(seperator);
                if(tempboard[i][j] == 0)
                {
                    System.out.print(" " + blank + " ");
                }
                else
                {
                    System.out.print(" " + tempboard[i][j] + " ");
                }
            }
            System.out.print(seperator);
            System.out.println();
            for(int j = 0; j <tempboard[i].length; j++)
            {
                System.out.print(divider + divider + divider + divider);
            }
            System.out.print(divider);
            System.out.println();
        }
        System.out.println("Word Bank:");
        int charcount = 0;
        for(String word: words)
        {
            charcount += word.length();
            if(charcount > 50)
            {
                System.out.println();
                charcount = word.length();
            }
            System.out.print(word + " ~ ");
        }
        System.out.println();
    }
}