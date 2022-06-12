package main;

import java.util.ArrayList;
import java.util.Random;

public class MineSweeper {
	static final int MASS_SIZE = 50;
	private final int fieldWidthSize;
	private final int fieldHeightSize;
	private final int mineCount;
	private final ArrayList<ArrayList<Mine>> field = new ArrayList<ArrayList<Mine>>();

	/* コンストラクタ */
	public MineSweeper(int fieldWidthSize, int fieldHeightSize) {
		this.fieldWidthSize = fieldWidthSize;
		this.fieldHeightSize = fieldHeightSize;
		this.mineCount = (int) (this.fieldWidthSize * this.fieldHeightSize * 0.175);
		// リストの作成
		for (int y = 0; y < this.fieldHeightSize; y++) {
			field.add(new ArrayList<>());
			for (int x = 0; x < this.fieldWidthSize; x++) {
				field.get(y).add(new Mine());
			}
		}
		this.setMine();
		this.countRoundMine();
	}

	/* ゲッター */
	public ArrayList<ArrayList<Mine>> getField() {
		ArrayList<ArrayList<Mine>> CopyField = new ArrayList<ArrayList<Mine>>();
		for (int y = 0; y < fieldHeightSize; y++) {
			CopyField.add(new ArrayList<>());
			for (int x = 0; x < fieldWidthSize; x++) {
				CopyField.get(y).add(field.get(y).get(x).clone());
			}
		}
		return CopyField;
	}

	/* コマンドプロンプトでの Field の表示 */
	public void dispCMD() {
		for (int y = 0; y < fieldHeightSize; y++) {
			for (int x = 0; x < fieldWidthSize; x++) {
				if (field.get(y).get(x).isMine())
					System.out.print("□");
				else
					System.out.print("■");
			}
			System.out.println();
		}
	}

	/* Mineの設置 */
	private void setMine() {
		Random random = new Random();
		int counter = 0;
		while (counter < mineCount) {
			int x = random.nextInt(fieldWidthSize);
			int y = random.nextInt(fieldHeightSize);
			if (!field.get(y).get(x).isMine()) {
				field.get(y).get(x).setMine(true);
				counter++;
			}
		}
	}

	/* 周辺のマスのMine数をカウント */
	private void countRoundMine() {
		for (int Y = 0; Y < fieldHeightSize; Y++) {
			for (int X = 0; X < fieldWidthSize; X++) {
				// 対象マスの周辺のMine数の計測
				int counter = 0;
				for (int y = (0 <= Y - 1 ? Y - 1 : 0); y <= (Y + 1 < fieldHeightSize ? Y + 1 : fieldHeightSize - 1); y++) {
					for (int x = (0 <= X - 1 ? X - 1 : 0); x <= (X + 1 < fieldWidthSize ? X + 1 : fieldWidthSize - 1); x++) {
						if (field.get(y).get(x).isMine())
							counter++;
					}
				}
				// カウントした数を記録
				field.get(Y).get(X).setRoundMineCounter(counter);
			}
		}
	}

	/* Game Over */
	private void GameOver() {
		// 結果表示のためにすべてのマスをOpen
		for (int y = 0; y < fieldHeightSize; y++) {
			for (int x = 0; x < fieldWidthSize; x++) {
				field.get(y).get(x).setOpen(true);
			}
		}
		System.out.println("Game Over");
	}

	/* クリア判定 */
	private void isClear() {
		int counter = 0;
		for (int y = 0; y < fieldHeightSize; y++) {
			for (int x = 0; x < fieldWidthSize; x++) {
				if (field.get(y).get(x).isCheck() && field.get(y).get(x).isMine())
					counter++;
			}
		}
		// すべてのMineにCheckをつけた場合
		if (counter == mineCount) {
			for (int y = 0; y < fieldHeightSize; y++) {
				for (int x = 0; x < fieldWidthSize; x++) {
					if (!field.get(y).get(x).isCheck())
						field.get(y).get(x).setOpen(true);
				}
			}
			System.out.println("Game Clear");
		}
	}

	/* 指定マスをOpen */
	public void open(int _x, int _y) {
		// 範囲内のマスが指定されているかの確認
		if (0 <= _x && _x < fieldWidthSize
				&& 0 <= _y && _y < fieldHeightSize) {

			// Openしてないマスが選択された場合
			if (!field.get(_y).get(_x).isOpen()) {
				// Check済みでない場合
				if (!field.get(_y).get(_x).isCheck()) {
					// Mineが存在する場合
					if (field.get(_y).get(_x).isMine()) {
						GameOver();
					}
					// Mineが存在しない場合
					else {
						field.get(_y).get(_x).setOpen(true);
						// 周囲にMineがない場合，周辺のマスもOpen
						if (field.get(_y).get(_x).getRoundMineCounter() == 0) {
							for (int y = (0 <= _y - 1 ? _y - 1 : 0); y <= (_y + 1 < fieldHeightSize ? _y + 1 : fieldHeightSize - 1); y++) {
								for (int x = (0 <= _x - 1 ? _x - 1 : 0); x <= (_x + 1 < fieldWidthSize ? _x + 1 : fieldWidthSize - 1); x++) {
									if (!field.get(y).get(x).isOpen()) {
										open(x, y);
									}
								}
							}
						}
					}
				}
			}
			// Openしたマスが選択された場合
			else {
				int counter = 0;
				// 指定したマスの周辺のCheckの数の確認
				for (int y = (0 <= _y - 1 ? _y - 1 : 0); y <= (_y + 1 < fieldHeightSize ? _y + 1 : fieldHeightSize - 1); y++) {
					for (int x = (0 <= _x - 1 ? _x - 1 : 0); x <= (_x + 1 < fieldWidthSize ? _x + 1 : fieldWidthSize - 1); x++) {
						if (field.get(y).get(x).isCheck())
							counter++;
					}
				}
				// 周辺のMineの数とCheckの数が一致した場合，周辺のマスをOpen
				if (counter == field.get(_y).get(_x).getRoundMineCounter()) {
					for (int y = (0 <= _y - 1 ? _y - 1 : 0); y <= (_y + 1 < fieldHeightSize ? _y + 1 : fieldHeightSize - 1); y++) {
						for (int x = (0 <= _x - 1 ? _x - 1 : 0); x <= (_x + 1 < fieldWidthSize ? _x + 1 : fieldWidthSize - 1); x++) {
							if (!field.get(y).get(x).isOpen())
								open(x, y);
						}
					}
				}
			}
		}
	}

	/* 指定マスをCheck */
	public void check(int _x, int _y) {
		if (0 <= _x && _x < fieldWidthSize
				&& 0 <= _y && _y < fieldHeightSize) {
			// OpenされてないマスのみをCheckする
			if (!field.get(_y).get(_x).isOpen())
				field.get(_y).get(_x).setCheck(!field.get(_y).get(_x).isCheck());
		}
		// すべてのMineにCheckをつけたか確認
		isClear();
	}

	/* ゲッター */
	public int getFieldWidthSize() {
		return fieldWidthSize;
	}

	public int getFieldHeightSize() {
		return fieldHeightSize;
	}
}

class Mine {
	// マスが開けられたかの判定
	private Boolean open;
	// 地雷の有無
	private Boolean mine;
	// チェックの有無
	private Boolean check;
	// 周囲のMineの数
	private int surroundingMineNumber;

	/* コンストラクタ */
	public Mine() {
		open = false;
		mine = false;
		check = false;
		surroundingMineNumber = -1;
	}

	/* ゲッター */
	public Boolean isOpen() {
		return this.open;
	}

	public Boolean isMine() {
		return this.mine;
	}

	public Boolean isCheck() {
		return this.check;
	}

	public int getRoundMineCounter() {
		return this.surroundingMineNumber;
	}

	/* セッタ― */
	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void setRoundMineCounter(int surroundingMineNumber) {
		if (surroundingMineNumber < 0)
			System.out.println("不適切な値が代入されようとしました");
		else
			this.surroundingMineNumber = surroundingMineNumber;
	}

	/* コピー */
	@Override
	public Mine clone() {
		Mine copy = new Mine();
		copy.open = this.open;
		copy.mine = this.mine;
		copy.check = this.check;
		copy.surroundingMineNumber = this.surroundingMineNumber;
		return copy;
	}
}