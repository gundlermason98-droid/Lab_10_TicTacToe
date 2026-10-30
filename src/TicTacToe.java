import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        while(true)
        {
            clearBoard();
            currentPlayer = "X";

            while(true)
            {
                int row, col;
                do
                {
                    row = SafeInput.getRangedInt(in,"Player " + currentPlayer + ", enter your row move between: ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in,"Player " + currentPlayer + ", enter your column move between: ", 1, 3) - 1;
                }
                while (!isValidMove(row, col));

                board[row][col] = currentPlayer;

                if (isWin(currentPlayer))
                {
                    display();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (isTie())
                {
                    display();
                    System.out.println("It's a tie!");
                    break;

                }
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }

            System.out.println("Do you want to play again? (Y/N): ");
            String playAgain = in.next().toUpperCase();
            if (!playAgain.equals("Y"))
            {
                break;
            }
        }
        in.close();
    }
    private static void clearBoard()
    {
        for (int x = 0; x < ROW; x++)
        {
            for (int y = 0; y < COL; y++)
            {
                board[x][y] = " ";
            }
        }
    }

    private static void display()
    {
        System.out.println("-------------");
        for (int x = 0; x < ROW; x++)
        {
            for (int y = 0; y < COL; y++)
            {
                System.out.print(board[x][y] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        if (row < 0 || row >= ROW || col < 0 || col >= COL)
        {
            System.out.println("Invalid move. Try again.");
            return false;
        }

        if(!board[row][col].equals(" "))
        {
            System.out.println("Space already taken. Try again.");
            return false;
        }

        return true;
    }

    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player)
    {
        for (int x = 0; x < ROW; x++)
        {
            if (board[x][0].equals(player) && board[x][1].equals(player) && board[x][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for (int x = 0; x < COL; x++)
        {
            if (board[0][x].equals(player) && board[1][x].equals(player) && board[2][x].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) || board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie()
    {
        for (int x = 0; x < ROW; x++)
        {
            for (int y = 0; y < COL; y++)
            {
                if (board[x][y].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
