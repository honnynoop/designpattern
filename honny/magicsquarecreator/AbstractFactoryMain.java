package com.honny.absfac;

public class AbstractFactoryMain {

	public static void main(String[] args) {
		
		MagicSquareCreator msc=MagicSquareCreator.factory(6);
		MagicSquare magic=msc.creatMagicSquare();
		MagicShow.magicPrint(magic);
		
	}

}
