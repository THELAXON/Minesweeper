import java.util.*;

public class BombSquare extends GameSquare
{
	private boolean thisSquareHasBomb = false;
	private boolean revealed;
	private ArrayList<BombSquare> adjacentbombsquares;
	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	// Function where recursion takes place when the bombcount is equal to 0 so it clicks the surrounding squares
	private void recursion(ArrayList<BombSquare> adjacentbombsquares)
	{
		for(int i=0;i<adjacentbombsquares.size();i++)
			{
				if(adjacentbombsquares.get(i).thisSquareHasBomb == false && adjacentbombsquares.get(i).revealed == false)
				{
					adjacentbombsquares.get(i).clicked();
				}
			}
	}

	// When it is clicked the bombcount is initated and checks if the current square is a bomb and if not checks the surrounding squares
	// When the bombcount is 0 the recursion is initiated while carrying the arraylist as a parameter
	public void clicked()
	{
		int bombcount=0;
		if(thisSquareHasBomb)
		{
			this.setImage("images/bomb.png");
		}
		else if(thisSquareHasBomb == false && revealed == false)
		{

			adjacentbombsquares = new ArrayList<>();
			for(int i=-1;i<=1;i++)
			{
				for(int j=-1;j<=1;j++)
				{
					if(this.board.getSquareAt(this.xLocation + i, this.yLocation + j) != null)
					{
						adjacentbombsquares.add((BombSquare) board.getSquareAt(this.xLocation + i, this.yLocation + j));
					}
				}
			}

			for(int i=0;i<adjacentbombsquares.size();i++)
			{
				if(adjacentbombsquares.get(i).thisSquareHasBomb)
				{
					bombcount++;
				}
			}
			this.setImage("images/"+bombcount+".png");
			revealed = true;

			if(bombcount == 0)
			{
				recursion(adjacentbombsquares);
			}
		}
	}

}
