package com.honny.absfac;

public class FourMagicSquareCreator extends MagicSquareCreator {

	public FourMagicSquareCreator() {
		this(4);
	}
	public FourMagicSquareCreator(int n) {
		super(4);
	}

	@Override
	public MagicSquare creatMagicSquare() {
		MagicSquare magic=new FourMagicSquare(n);
		return magic;
	}

}
