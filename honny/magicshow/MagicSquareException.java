package kr.re.kitri.play;

public class MagicSquareException extends Exception {

	public MagicSquareException() {
		super("3�̸��� �������� �������� �ʽ��ϴ�.");
	}

	public MagicSquareException(String message) {
		super(message);
	}
}
