package main;

import java.util.ArrayList;

public class MineSweeper {
	final int FieldWidthSize;
	final int FieldHeightSize;
	final int MassSize = 50;
	private ArrayList<ArrayList<Mine>> Field = new ArrayList<ArrayList<Mine>>();
	Window Frame;
	
	/* コンストラクタ */
	public MineSweeper(int _FieldWidthSize, int _FieldHeightSize) {
		FieldWidthSize  = _FieldWidthSize;
		FieldHeightSize = _FieldHeightSize;
		// ウィンドウクラスの作成
		Frame = new Window(this, "MineSweeper", MassSize * FieldWidthSize + MassSize / 4, MassSize * FieldHeightSize + MassSize * 3 / 4);
		// リストの作成
		for (int y = 0; y < FieldHeightSize; y++) {
			Field.add(new ArrayList<>());
			for (int x = 0; x < FieldWidthSize; x++) {
				Field.get(y).add(new Mine());
			}
		}
		Frame.drawField();
		this.setMine();
		this.countRoundMine();
	}
	
	// ゲッター
	public ArrayList<ArrayList<Mine>> getField(){
		return this.Field;
	}
	
	// コマンドプロンプトでの Field の表示
	public void dispCMD() {
		for (int y = 0; y < FieldHeightSize; y++) {
			for (int x = 0; x < FieldWidthSize; x++) {
				if (Field.get(y).get(x).isMine())
					System.out.print("□");
				else
					System.out.print(Field.get(y).get(x).RoundMineCounter);
			}
			System.out.println();
		}
	}
	
	// Mineの設置
	private void setMine() {
		for (int y = 0; y < FieldHeightSize; y++) {
			Field.get(y).get(0).setMine(true);
		}
	}
	
	private void countRoundMine() {
		for (int Y = 0; Y < FieldHeightSize; Y++) {
			for (int X = 0; X < FieldWidthSize; X++) {
				// 対象マスの周辺のMine数の計測
				int counter = 0;
				for (int y = (0 <= Y - 1 ? Y - 1 : 0); y <= (Y + 1 < FieldHeightSize ? Y + 1 : FieldHeightSize - 1); y++) {
					for (int x = (X - 1 >= 0 ? X - 1 : 0); x <= (X + 1 < FieldWidthSize ? X + 1 : FieldWidthSize - 1); x++) {
						if (this.Field.get(y).get(x).isMine())
							counter++;
					}
				}
				//
				Field.get(Y).get(X).RoundMineCounter = counter;
			}
		}
	}
	
	// 指定マスをOpen
	public void Open(int _MouseX, int _MouseY) {
		if (0 <= _MouseX / MassSize && _MouseX / MassSize < FieldWidthSize
				&& 0 <= _MouseY / MassSize && _MouseY / MassSize < FieldHeightSize)
			this.Field.get(_MouseY / MassSize).get(_MouseX / MassSize).setOpen(true);
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
	// 周囲のMineの数
	int RoundMineCounter;
	
	/* コンストラクタ */
	public Mine() {
		Open = false;
		Mine = false;
		Check = false;
		RoundMineCounter = Integer.MAX_VALUE;
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