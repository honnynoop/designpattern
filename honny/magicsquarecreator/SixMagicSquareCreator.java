package com.honny.absfac;

public class SixMagicSquareCreator extends MagicSquareCreator {

	public SixMagicSquareCreator() {
		this(6);
	}
	public SixMagicSquareCreator(int n) {
		super(6);
	}

	@Override
	public MagicSquare creatMagicSquare() {
		MagicSquare magic=new SixMagicSquare(n);
		return magic;
	}

}
