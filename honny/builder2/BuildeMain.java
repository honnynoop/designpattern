package com.honny.two;

public class BuildeMain {
	public static void main(String[] args) {
		int n=5;
		MagicSquareDirector director=new MagicSquareDirector();
		IMagicSquareBuilder bms=new MagicSquareBuilder(n);
		director.builder(bms);
		MagicSquare magic=director.createMagicSquare();
		magic.print();

		//director에서 builder를 이용하여
		//MagicSquare를 생성 및 조립
	}
}
