package com.honny.two;

public class BuildeMain {
	public static void main(String[] args) {
		int n=5;
		MagicSquareDirector director=new MagicSquareDirector();
		IMagicSquareBuilder bms=new MagicSquareBuilder(n);
		director.builder(bms);
		MagicSquare magic=director.createMagicSquare();
		magic.print();

		//director���� builder�� �̿��Ͽ�
		//MagicSquare�� ���� �� ����
	}
}
