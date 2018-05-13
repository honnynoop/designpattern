package com.honny.two;

public class MagicSquareBuilder implements IMagicSquareBuilder {

	protected int[][] magic;
	protected int n;

	public MagicSquareBuilder(int n) {
		this.n=n;
	}

	public MagicSquareBuilder() {
		this(3);
	}
	public void init(){
		magic=new int[n][n];
	}
	@Override
	public MagicSquare build() {
		MagicSquare  ms=new OddMagicSquare(n);
		return ms;
	}
	@Override
	public void make() {
		int x=0;
		int y=n/2;
		for (int i = 1; i <= n*n; i++) {
			int tempX=x;
			int tempY=y;
			
			this.magic[x][y]=i;
			if(x-1<0){
				x=n-1;
			}else{
				x--;
			}
			
			if(y-1<0){
				y=n-1;
			}else{
				y--;
			}
			
			if(this.magic[x][y]!=0){
				x=tempX+1;
				y=tempY;
			}
			
		}//for
	}
	@Override
	public int[][] getMegic() {
		return magic;
	}
}
