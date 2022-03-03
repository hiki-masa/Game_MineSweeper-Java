package main;

import java.util.ArrayList;

public class MineSweeper {
	final int FieldSize;
	final int MassSize = 30;
	ArrayList<ArrayList<Mine>> Field = new ArrayList<ArrayList<Mine>>();
	Window Frame;
	
	/* コンストラクタ */
	public MineSweeper(int _FieldSize) {
		FieldSize = _FieldSize;
		// ウィンドウクラスの作成
		Frame = new Window("MineSweeper", MassSize * FieldSize, MassSize * FieldSize);
		// リストの作成
		for (int y = 0; y < FieldSize; y++) {
			ArrayList<Mine> tmp = new ArrayList<>();
			for (int x = 0; x < FieldSize; x++) {
				tmp.add(new Mine());
			}
			Field.add(tmp);
		}
		
		Frame.drawField(MassSize, Field);
	}
	
	// コマンドプロンプトでの Field の表示
	public void dispCMD() {
		for (int y = 0; y < FieldSize; y++) {
			ArrayList<Mine> tmp_Field = Field.get(y);
			for (int x = 0; x < FieldSize; x++) {
				if (tmp_Field.get(x).isMine()) {
					System.out.print("□");
				}
				else {
					System.out.print("■");
				}
			}
			System.out.println();
		}
	}
	
	// Mineの設置
	public void setMine() {
		for (int y = 0; y < FieldSize; y++) {
			for (int x = 0; x < FieldSize; x++) {
				Field.get(y).get(x).setOpen(true);
			}
			Field.get(y).get(0).setMine(true);
		}
		Frame.repaint();
	}
}

class Mine {
	// マスが開けられたかの判定
	private Boolean Open;
	// 地雷の有無
	private Boolean Mine;
	// チェックの有無
	private Boolean Check;
	
	/* コンストラクタ */
	public Mine() {
		Open = false;
		Mine = false;
		Check = false;
	}
	
	// ゲッター
	public Boolean isOpen()  { return this.Open; }
	public Boolean isMine()  { return this.Mine; }
	public Boolean isCheck() { return this.Check; }
	
	// セッタ―
	public void setOpen(boolean _input)  { this.Open = _input; }
	public void setMine(boolean _input)  { this.Mine = _input; }
	public void setCheck(boolean _input) { this.Check = _input; }
}