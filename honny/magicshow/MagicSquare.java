package kr.re.kitri.play;

public abstract class MagicSquare 
implements IMagicSquare {
	
	protected int[][] magic;
	protected int n;

	public MagicSquare(int n) {
		this.n=n;
		magic=new int[n][n];
	}

	public int[][] getMagic() {
		return magic;
	}

	@Override
	public void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%d\t",magic[i][j]);
			}
			System.out.println();
		}
	}

	@Override
	public abstract void make() ;

	
	
	
}
