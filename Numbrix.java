package code;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Numbrix
{
	private int[][] grid;

	private boolean[][] used;
	private int rows;
	private int cols;

	public Numbrix(String fileName) throws FileNotFoundException
    {
    	Scanner in = new Scanner(new File(fileName));
    	
    	rows = in.nextInt();
    	cols = in.nextInt();
    	grid = new int[cols][rows];
    	used = new boolean[cols][rows];
    	for(int y = 0; y < rows; y++)
    	{
    		for(int x = 0; x < cols; x++)
    		{
    			int num = in.nextInt();
    			grid[y][x]=num;
    			if(num!=0)
    				used[y][x] = true;
    		}
    	}
    }

	public void solve()
	{
		System.out.println(this);
		System.out.println("SEARCHING...\n");
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < cols; x++){
				recursiveSolve(y,x,1);
			}
		}
	}

	private void recursiveSolve(int y, int x, int n)
	{
		if(y >= rows || y < 0 || x >= cols || x < 0)
			return;
		boolean zero = grid[y][x]==0;
		if(zero && isin(grid,n))
			return;
		if(!zero && grid[y][x]!=n)
			return;
		grid[y][x]=n;
		if(n==rows*cols){
			System.out.println(this);
			return;
		}
		else{
			recursiveSolve(y+1, x, n+1);
			recursiveSolve(y-1, x, n+1);
			recursiveSolve(y, x+1, n+1);
			recursiveSolve(y, x-1, n+1);
			
			if(zero)
				grid[y][x]=0;
		}
	}
	
	public boolean isin(int[][] grid, int key){
		for(int[] i : grid){
			for(int j : i){
				if(j==key)
					return true;
			}
		}
		return false;
	}


	public String toString()
	{
		String result = "";
		String ch = "";
		for(int[] i : grid){
			for(int a : i){
				ch = Integer.toString(a);
				result += (ch.equals("0"))?"-":ch;
				result += "\t";
			}
			result+="\n";
		}
		return result;
	}
}