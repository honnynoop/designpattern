package kr.re.kitri.play;

public class MagicSquareException extends Exception {

	public MagicSquareException() {
		super("3미만의 마방진은 존재하지 않습니다.");
	}

	public MagicSquareException(String message) {
		super(message);
	}
}
