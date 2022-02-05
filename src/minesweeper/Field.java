package minesweeper;

import java.util.ArrayList;

public class Field {
	int FieldSize;
	ArrayList<ArrayList<Integer>> Field = new ArrayList<ArrayList<Integer>>();
	
	// コンストラクタ
	public Field(int _FieldSize) {
		this.FieldSize = _FieldSize;
		
		for (int i = 0; i < this.FieldSize; i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for (int j = 0; j < this.FieldSize; j++) {
				tmp.add(j);
			}
			this.Field.add(tmp);
		}
		//System.out.println(this.Field);
	}
	
	// マップ全体の表示
	public void disp() {
		for (int x =0; x < this.FieldSize; x++) {
			for (int y : this.Field.get(x)) {
				System.out.print(y);
			}
			System.out.println();
		}
	}
}