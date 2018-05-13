package com.honny.two;

public class MagicSquareDirector {
	
	IMagicSquareBuilder bms;
	public  void builder(IMagicSquareBuilder bms) {
		this.bms=bms;
	}
	public MagicSquare createMagicSquare() {
		MagicSquare ms=bms.build();
		bms.init();
		bms.make();
		ms.setMagic(bms.getMegic());
		return ms;
	}
}
