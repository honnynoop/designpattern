package com.honny.two;

public class OddMagicSquare extends MagicSquare{
	
	public int[][] getMagic() {
		return magic;
	}
	public OddMagicSquare(int n) {
		super(n);
	}

	public OddMagicSquare() {
		super(3);
	}
	
	@Override
	public void setMagic(int[][] mm) {
		for (int i = 0; i < mm.length; i++) {
			System.arraycopy(mm, 0, magic, 0, mm[i].length);
		}
	}

}//class
