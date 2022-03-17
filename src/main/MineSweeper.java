package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class MineSweeper implements MouseListener{
	static  final int MassSize = 50;
	private final int FieldWidthSize;
	private final int FieldHeightSize;
	private final int MineCount;
	private final ArrayList<ArrayList<Mine>> Field = new ArrayList<ArrayList<Mine>>();
	private Window Frame;
	
	/* コンストラクタ */
	public MineSweeper(int _FieldWidthSize, int _FieldHeightSize) {
		FieldWidthSize  = _FieldWidthSize;
		FieldHeightSize = _FieldHeightSize;
		MineCount = (int) (FieldWidthSize * FieldHeightSize * 0.175);
		// ウィンドウクラスの作成
		Frame = new Window("MineSweeper", MassSize * FieldWidthSize, MassSize * FieldHeightSize);
		// リストの作成
		for (int y = 0; y < FieldHeightSize; y++) {
			Field.add(new ArrayList<>());
			for (int x = 0; x < FieldWidthSize; x++) {
				Field.get(y).add(new Mine());
			}
		}
		this.setMine();
		this.countRoundMine();
		Frame.add(new Canvas(Field));
		Frame.setVisible(true);
		
		// マウスイベントの感知
		this.Frame.addMouseListener(this);
	}
	
	/* ゲッター（不使用）
	public ArrayList<ArrayList<Mine>> getField() {
		ArrayList<ArrayList<Mine>> CopyField = new ArrayList<ArrayList<Mine>>();
		for (int y = 0; y < FieldHeightSize; y++) {
			CopyField.add(new ArrayList<>());
			for (int x = 0; x < FieldWidthSize; x++) {
				CopyField.get(y).add(Field.get(y).get(x).clone());
			}
		}
		return CopyField;
	}*/
	
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
		while (counter < MineCount) {
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
					for (int x = (0 <= X - 1 ? X - 1 : 0); x <= (X + 1 < FieldWidthSize ? X + 1 : FieldWidthSize - 1); x++) {
						if (Field.get(y).get(x).isMine())
							counter++;
					}
				}
				// カウントした数を記録
				Field.get(Y).get(X).setRoundMineCounter(counter);
			}
		}
	}
	
	// Game Over
	private void GameOver() {
		// 結果表示のためにすべてのマスをOpen
		for (int y = 0; y < FieldHeightSize; y++) {
			for (int x = 0; x < FieldWidthSize; x++) {
				Field.get(y).get(x).setOpen(true);
			}
		}
		Frame.repaint();
		System.out.println("Game Over");
	}
	
	// クリア判定
	private void isClear() {
		int counter = 0;
		for (int y = 0; y < FieldHeightSize; y++) {
			for (int x = 0; x < FieldWidthSize; x++) {
				if (Field.get(y).get(x).isCheck() && Field.get(y).get(x).isMine())
					counter++;
			}
		}
		// すべてのMineにCheckをつけた場合
		if (counter == MineCount) {
			for (int y = 0; y < FieldHeightSize; y++) {
				for (int x = 0; x < FieldWidthSize; x++) {
					if (!Field.get(y).get(x).isCheck())
						Field.get(y).get(x).setOpen(true);
				}
			}
			System.out.println("Game Clear");
		}
	}
	
	// 指定マスをOpen
	public void Open(int _x, int _y) {
		// 範囲内のマスが指定されているかの確認
		if (0 <= _x && _x < FieldWidthSize
				&& 0 <= _y && _y < FieldHeightSize) {
			
			// Openしてないマスが選択された場合
			if (!Field.get(_y).get(_x).isOpen()) {
				// Check済みでない場合
				if (!Field.get(_y).get(_x).isCheck()) {
					// Mineが存在する場合
					if (Field.get(_y).get(_x).isMine()) {
						GameOver();
					}
					// Mineが存在しない場合
					else {
						Field.get(_y).get(_x).setOpen(true);
						// 周囲にMineがない場合，周辺のマスもOpen
						if (Field.get(_y).get(_x).getRoundMineCounter() == 0) {
							for (int y = (0 <= _y - 1 ? _y - 1 : 0); y <= (_y + 1 < FieldHeightSize ? _y + 1 : FieldHeightSize - 1); y++) {
								for (int x = (0 <= _x - 1 ? _x - 1 : 0); x <= (_x + 1 < FieldWidthSize ? _x + 1 : FieldWidthSize - 1); x++) {
									if (!Field.get(y).get(x).isOpen()) {
										Open(x, y);
									}
								}
							}
						}
						Frame.repaint();
					}
				}
			}
			// Openしたマスが選択された場合
			else {
				int counter = 0;
				// 指定したマスの周辺のCheckの数の確認
				for (int y = (0 <= _y - 1 ? _y - 1 : 0); y <= (_y + 1 < FieldHeightSize ? _y + 1 : FieldHeightSize - 1); y++) {
					for (int x = (0 <= _x - 1 ? _x - 1 : 0); x <= (_x + 1 < FieldWidthSize ? _x + 1 : FieldWidthSize - 1); x++) {
						if (Field.get(y).get(x).isCheck())
							counter++;
					}
				}
				// 周辺のMineの数とCheckの数が一致した場合，周辺のマスをOpen
				if (counter == Field.get(_y).get(_x).getRoundMineCounter()) {
					for (int y = (0 <= _y - 1 ? _y - 1 : 0); y <= (_y + 1 < FieldHeightSize ? _y + 1 : FieldHeightSize - 1); y++) {
						for (int x = (0 <= _x - 1 ? _x - 1 : 0); x <= (_x + 1 < FieldWidthSize ? _x + 1 : FieldWidthSize - 1); x++) {
							if (!Field.get(y).get(x).isOpen())
								Open(x, y);
						}
					}
				}
			}
		}
	}
	
	// 指定マスをCheck
	public void Check(int _x, int _y) {
		if (0 <= _x && _x < FieldWidthSize
				&& 0 <= _y && _y < FieldHeightSize) {
			// OpenされてないマスのみをCheckする
			if (!Field.get(_y).get(_x).isOpen())
				Field.get(_y).get(_x).setCheck(!Field.get(_y).get(_x).isCheck());
		}
		// すべてのMineにCheckをつけたか確認
		isClear();
		Frame.repaint();
	}
	
	/* マウスイベントの設定 */
	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
		// 左クリック
		case MouseEvent.BUTTON1:
			this.Open(e.getPoint().x / MassSize, (e.getPoint().y - MassSize / 2) / MassSize);
			break;
		// 右クリック
		case MouseEvent.BUTTON3:
			this.Check(e.getPoint().x / MassSize, (e.getPoint().y - MassSize / 2) / MassSize);
			break;
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
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
	
	/* コピー（不使用）
	@Override
	public Mine clone() {
		Mine copy = new Mine();
		copy.Open = this.Open;
		copy.Mine = this.Mine;
		copy.Check = this.Check;
		copy.RoundMineCounter = this.RoundMineCounter;
		return copy;
	}*/
}