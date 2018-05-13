package com.honny.absfac;

public abstract class MagicSquare {
	protected int[][] magic;
	protected int n;

	public MagicSquare(int n) {
		this.n=n;
		magic=new int[n][n];
	}

	public int[][] getMagic() {
		return magic;
	}

	public void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%d\t",magic[i][j]);
			}
			System.out.println();
		}
	}

	public abstract void make() ;
}
