package com.honny.absfac;
public class OddMagicSquare extends MagicSquare {

	public OddMagicSquare(int n) {
		super(n);
	}
	public OddMagicSquare() {
		this(3);
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
	}//make
}//class
