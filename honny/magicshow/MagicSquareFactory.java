package kr.re.kitri.play;

public class MagicSquareFactory {

	private static MagicSquareFactory msf;
	
	private MagicSquareFactory() {}

	public static MagicSquareFactory getInstance(){
		if(msf==null){
			msf=new MagicSquareFactory();
		}
		return msf;
	}
	
	public IMagicSquare factory(int n) throws MagicSquareException{
		IMagicSquare magic=null;
		if(n<3){
			throw new MagicSquareException("3이상 입력하시오");
		}else if(n%2==1){
			magic=new OddMagicSquare(n);
		}else if(n%4==0){
			magic=new FourMagicSquare(n);
		}else if(n%4==2){
			magic=new SixMagicSquare(n);
		}
		return magic;
	}
	

	
	
}
