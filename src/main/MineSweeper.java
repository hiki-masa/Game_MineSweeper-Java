package main;

import java.util.ArrayList;

public class MineSweeper {
	int FieldSize;
	final int MassSize = 50;
	ArrayList<ArrayList<Mine>> Field = new ArrayList<ArrayList<Mine>>();
	Window Frame;
	
	// コンストラクタ
	public MineSweeper(int _FieldSize) {
		FieldSize = _FieldSize;
		Frame = new Window("MineSweeper", MassSize * FieldSize, MassSize * FieldSize);
		
		for (int i = 0; i < FieldSize; i++) {
			ArrayList<Mine> tmp = new ArrayList<>();
			for (int j = 0; j < FieldSize; j++) {
				tmp.add(new Mine());
			}
			Field.add(tmp);
		}
		Frame.FillRect(0, 0, MassSize, MassSize);
	}
	
	// マップ全体の表示
	public void disp() {
		for (int x = 0; x < FieldSize; x++) {
			ArrayList<Mine> tmp_Field = Field.get(x);
			for (int y = 0; y < FieldSize; y++) {
				if (tmp_Field.get(y).Open) {
					System.out.print("□");
				}
				else {
					System.out.print("■");
				}
			}
			System.out.println();
		}
	}
}

class Mine {
	// マスが開けられたかの判定
	Boolean Open;
	// 地雷の有無
	Boolean Mine;
	// チェックの有無
	Boolean Check;
	
	// コンストラクタ
	public Mine() {
		Open = false;
		Mine = false;
		Check = false;
	}
}
