package com.honny.absfac;

public class OddMagicSquareCreator extends MagicSquareCreator {

	
	public OddMagicSquareCreator() {
		this(3);
	}
	public OddMagicSquareCreator(int n) {
		super(n);
	}

	@Override
	public MagicSquare creatMagicSquare() {
		MagicSquare magic=new OddMagicSquare(n);
		return magic;
	}

}
