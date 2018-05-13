package kr.re.kitri.play;

public class SixMagicSquare extends MagicSquare {

	
	public SixMagicSquare(int n) {
		super(n);
	}
	public SixMagicSquare() {
		this(6);
	}
	@Override
	public void make() {
		makeA();
		makeB();
		makeCD();
		makeMul();
		makeMagic();
	}
	private void makeMagic() {
		OddMagicSquare odd=new OddMagicSquare(n/2);
		odd.make();
		int[][] m=odd.getMagic();
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j <n/2; j++) {
				magic[i][j] += m[i][j] ;
				magic[i][j+n/2] += m[i][j] ;
				magic[i+n/2][j] += m[i][j] ;
				magic[i+n/2][j+n/2] += m[i][j] ;
			}
		}
	}
	public void makeMul() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <n; j++) {
				magic[i][j] *= (n/2*n/2) ;
			}
		}
	}
	private void makeCD() {
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j <n/2; j++) {
				if(magic[i][j]==3){
					magic[i+n/2][j]=0;
				}else{
					magic[i+n/2][j]=3;
				}
				
				if(magic[i][j+n/2]==1){
					magic[i+n/2][j+n/2]=2;
				}else{
					magic[i+n/2][j+n/2]=1;
				}
				
			}
		}
	}
	private void makeB() {
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j <n/2; j++) {
				magic[i][j+n/2]=1;
			}
		}
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j <n/2-(n/4-1); j++) {
				magic[i][j+n/2]=2;
			}
		}
	}
	private void makeA() {
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j <n/4; j++) {
				if(n/4==i){
					this.magic[i][j+1]=3;
				}else{
					this.magic[i][j]=3;
				}
			}
		}
	}

}
