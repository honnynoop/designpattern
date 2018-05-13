package com.honny.absfac;

public abstract class MagicSquareCreator {
	protected int n;
	public MagicSquareCreator() {
		this(3);
	}
	public MagicSquareCreator(int n) {
		this.n=n;
	}
	
	public static MagicSquareCreator factory(int n){
		MagicSquareCreator msc=null;
		if(n%2==1){
			msc =new OddMagicSquareCreator(n);
		}else if(n%4==0){
			msc =new FourMagicSquareCreator(n);
		}else if(n%4==2){
			msc =new SixMagicSquareCreator(n);
		}
		return msc;
	}
	public abstract MagicSquare creatMagicSquare();
	
}
