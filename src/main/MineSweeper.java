package main;

import java.util.ArrayList;
import java.util.Random;

public class MineSweeper {
	private final int FieldWidthSize;
	private final int FieldHeightSize;
	private final int MassSize = 50;
	private final ArrayList<ArrayList<Mine>> Field = new ArrayList<ArrayList<Mine>>();
	private Window Frame;
	
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
		this.setMine();
		this.countRoundMine();
		Frame.drawField();
		Frame.setVisible(true);
	}
	
	// ゲッター
	public ArrayList<ArrayList<Mine>> getField() { return Field; }
	public int getMassSize() { return MassSize; }
	
	// コマンドプロンプトでの Field の表示
	public void dispCMD() {
		for (int y = 0; y < FieldHeightSize; y++) {
			for (int x = 0; x < FieldWidthSize; x++) {
				if (Field.get(y).get(x).isMine())
					System.out.print("□");
				else
					System.out.print("■");
			}
			System.out.println();
		}
	}
	
	// Mineの設置
	private void setMine() {
		Random random = new Random();
		int counter = 0;
		while (counter < FieldWidthSize * FieldHeightSize * 0.1) {
			int x = random.nextInt(FieldWidthSize);
			int y = random.nextInt(FieldHeightSize);
			if (!Field.get(y).get(x).isMine()) {
				Field.get(y).get(x).setMine(true);
				counter++;
			}
		}
	}
	
	// 周辺のマスのMine数をカウント
	private void countRoundMine() {
		for (int Y = 0; Y < FieldHeightSize; Y++) {
			for (int X = 0; X < FieldWidthSize; X++) {
				// 対象マスの周辺のMine数の計測
				int counter = 0;
				for (int y = (0 <= Y - 1 ? Y - 1 : 0); y <= (Y + 1 < FieldHeightSize ? Y + 1 : FieldHeightSize - 1); y++) {
					for (int x = (X - 1 >= 0 ? X - 1 : 0); x <= (X + 1 < FieldWidthSize ? X + 1 : FieldWidthSize - 1); x++) {
						if (Field.get(y).get(x).isMine())
							counter++;
					}
				}
				// カウントした数を記録
				Field.get(Y).get(X).setRoundMineCounter(counter);
			}
		}
	}
	
	// 指定マスをOpen
	public void Open(int _MouseX, int _MouseY) {
		int FieldX = _MouseX / MassSize;
		int FieldY = _MouseY / MassSize;
		if (0 <= FieldX && FieldX < FieldWidthSize
				&& 0 <= FieldY && FieldY < FieldHeightSize)
			if (!Field.get(FieldY).get(FieldX).isCheck())
				Field.get(FieldY).get(FieldX).setOpen(true);
		// 周囲にMineがない場合，周辺のマスもOpen
		if (Field.get(FieldY).get(FieldX).getRoundMineCounter() == 0) {
			for (int y = (0 <= FieldY - 1 ? FieldY - 1 : 0); y <= (FieldY + 1 < FieldHeightSize ? FieldY + 1 : FieldHeightSize - 1); y++) {
				for (int x = (0 <= FieldX - 1 ? FieldX - 1 : 0); x <= (FieldX + 1 < FieldWidthSize ? FieldX + 1 : FieldWidthSize - 1); x++) {
					if (!Field.get(y).get(x).isOpen()) {
						Open(x * MassSize, y * MassSize);
					}
				}
			}
		}
		Frame.drawField();
	}
	
	// 指定マスをCheck
	public void Check(int _MouseX, int _MouseY) {
		int FieldX = _MouseX / MassSize;
		int FieldY = _MouseY / MassSize;
		if (0 <= FieldX && FieldX < FieldWidthSize
				&& 0 <= FieldY && FieldY < FieldHeightSize)
			Field.get(FieldY).get(FieldX).setCheck(!Field.get(FieldY).get(FieldX).isCheck());
		Frame.drawField();
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
	private int RoundMineCounter;
	
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
	public int getRoundMineCounter() { return this.RoundMineCounter; }
	
	// セッタ―
	public void setOpen(boolean _input)  { this.Open = _input; }
	public void setMine(boolean _input)  { this.Mine = _input; }
	public void setCheck(boolean _input) { this.Check = _input; }
	public void setRoundMineCounter(int _input) {
		if (_input < 0)
			System.out.println("不適切な値が代入されようとしました");
		else
			this.RoundMineCounter = _input;
	}
}