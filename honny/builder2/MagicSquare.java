package com.honny.two;

public abstract class MagicSquare {
	protected int[][] magic;
	protected int n;
	
	public abstract int[][] getMagic();
	public abstract void setMagic(int[][] mm);
	public MagicSquare(int n) {
		this.n=n;
		magic=new int[n][n];
	}
	public void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%d\t",magic[i][j]);
			}
			System.out.println();
		}
	}
}
