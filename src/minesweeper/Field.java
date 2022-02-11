package minesweeper;

import java.util.ArrayList;

public class Field {
	int FieldSize;
	ArrayList<ArrayList<Mine>> Field = new ArrayList<ArrayList<Mine>>();
	Window frame;
	
	// コンストラクタ
	public Field(int _FieldSize) {
		this.FieldSize = _FieldSize;
		//frame = new Window("MineSweeper");
		for (int i = 0; i < this.FieldSize; i++) {
			ArrayList<Mine> tmp = new ArrayList<>();
			Mine tmp_mine = new Mine();
			for (int j = 0; j < this.FieldSize; j++) {
				tmp.add(tmp_mine);
			}
			this.Field.add(tmp);
		}
	}
	
	// マップ全体の表示
	public void disp() {
		for (int x = 0; x < this.FieldSize; x++) {
			ArrayList<Mine> tmp_Field = this.Field.get(x);
			for (int y = 0; y < this.FieldSize; y++) {
				if (tmp_Field.get(y).open) {
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
	Boolean open;
	// 地雷の有無
	Boolean mine;
	// チェックの有無
	Boolean check;
	
	// コンストラクタ
	public Mine() {
		open = false;
		mine = false;
		check = false;
	}
}
