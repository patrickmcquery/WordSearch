import java.util.ArrayList;

public class WordSearch
{
    public static void main(String[] args)
    {
        ArrayList<String> temp = new ArrayList<String>(0);
        temp.add("JELLO");
        temp.add("CAKE");
        temp.add("BROWNIE");
        temp.add("PIE");
        temp.add("COOKIE");
        Board board = new Board(temp);
    }
}