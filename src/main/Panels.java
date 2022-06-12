package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * ベースパネル
 * */
abstract class BasePanel extends JPanel {
	protected int panelWidth, panelHeight;

	abstract protected void prepareComponent();

	public int getPanelWidth() {
		return panelWidth;
	}

	public int getPanelHeight() {
		return panelHeight;
	}
}

/*
 * タイトル画面
 * */
class TitlePanel extends BasePanel {
	private Font font;
	private JLabel label;
	private JButton buttonScreenTransitionToGame;
	private TitleButtonListener titleButtonListener;

	/* コンストラクタ */
	public TitlePanel() {
		// パネルサイズの設定
		panelWidth = 500;
		panelHeight = 500;
		// フォントの設定
		font = new Font("Century", Font.PLAIN, 30);
		// テキストラベルの作成
		label = new JLabel();
		// ボタンの作成
		buttonScreenTransitionToGame = new JButton("Game Start");
		// ボタンリスナーの登録
		titleButtonListener = new TitleButtonListener();
		new TitleKeyAdapter(this);
	}

	@Override
	protected void prepareComponent() {
		// パネルの背景色の設定
		super.setBackground(Color.black);

		// テキストラベルの更新
		label.setText("MineSweeper");
		// フォントの反映
		label.setFont(this.font);
		// フォントの色指定
		label.setForeground(Color.white);
		// 水平位置・垂直位置の設定
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		// 背景色の設定
		label.setOpaque(true);
		label.setBackground(Color.black);
		// 設置位置の指定
		label.setBounds(0, 0, 500, 50);

		// ボタンにリスナー登録
		buttonScreenTransitionToGame.addActionListener(titleButtonListener);
		// 設置位置の指定
		buttonScreenTransitionToGame.setBounds(0, 450, 500, 50);

		// パネルに各コンポーネントを追加
		this.setLayout(null);
		super.add(this.label);
		super.add(this.buttonScreenTransitionToGame);
	}

	/* ボタンリスナーの設定 */
	private class TitleButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonScreenTransitionToGame) {
				Main.window.setFrontScreenAndFocus(ScreenMode.GAME);
			}
		}
	}

	/* キーリスナーの設定 */
	private class TitleKeyAdapter extends KeyAdapter {
		/* コンストラクタ */
		private TitleKeyAdapter(TitlePanel p) {
			super();
			p.addKeyListener(this);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			}
		}
	}
}

/*
 * ゲーム画面
 * */
class GamePanel extends BasePanel {
	private MineSweeper mineSweeper;
	static private Image basicImg = Toolkit.getDefaultToolkit().getImage("src/basic.png");
	static private Image mineImg = Toolkit.getDefaultToolkit().getImage("src/mine.png");
	static private Image checkImg = Toolkit.getDefaultToolkit().getImage("src/check.png");
	static private Image opendImg = Toolkit.getDefaultToolkit().getImage("src/opend.png");
	static private Image open1Img = Toolkit.getDefaultToolkit().getImage("src/one.png");
	static private Image open2Img = Toolkit.getDefaultToolkit().getImage("src/two.png");
	static private Image open3Img = Toolkit.getDefaultToolkit().getImage("src/three.png");
	static private Image open4Img = Toolkit.getDefaultToolkit().getImage("src/four.png");
	static private Image open5Img = Toolkit.getDefaultToolkit().getImage("src/five.png");
	static private Image open6Img = Toolkit.getDefaultToolkit().getImage("src/six.png");
	static private Image open7Img = Toolkit.getDefaultToolkit().getImage("src/seven.png");
	static private Image open8Img = Toolkit.getDefaultToolkit().getImage("src/eight.png");

	/* コンストラクタ */
	public GamePanel() {
		// キーリスナーの登録
		new GameMouseAdapter(this);
	}

	@Override
	protected void prepareComponent() {
		// パネルの背景色の設定
		super.setBackground(Color.black);
		this.mineSweeper = new MineSweeper(20, 15);
		panelWidth = MineSweeper.MASS_SIZE * mineSweeper.getFieldWidthSize();
		panelHeight = MineSweeper.MASS_SIZE * mineSweeper.getFieldHeightSize();
	}

	/* 表示 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int MassSize = MineSweeper.MASS_SIZE;

		int x = 0, y = 0;
		for (ArrayList<Mine> mineList : mineSweeper.getField()) {
			x = 0;
			for (Mine mine : mineList) {
				if (mine.isOpen()) {
					if (mine.isMine())
						g.drawImage(mineImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
					else {
						switch (mineSweeper.getField().get(y).get(x).getRoundMineCounter()) {
						case 0:
							g.drawImage(opendImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 1:
							g.drawImage(open1Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 2:
							g.drawImage(open2Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 3:
							g.drawImage(open3Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 4:
							g.drawImage(open4Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 5:
							g.drawImage(open5Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 6:
							g.drawImage(open6Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 7:
							g.drawImage(open7Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 8:
							g.drawImage(open8Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						}
					}
				} else {
					if (mineSweeper.getField().get(y).get(x).isCheck())
						g.drawImage(checkImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
					else
						g.drawImage(basicImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
				}
				x++;
			}
			y++;
		}
	}

	/* マウス・キーリスナーの設定 */
	private class GameMouseAdapter extends MouseKeyAdapter {
		/* コンストラクタ */
		private GameMouseAdapter(GamePanel p) {
			super();
			p.addMouseListener(this);
			p.addKeyListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				mineSweeper.open(e.getPoint().x / MineSweeper.MASS_SIZE, e.getPoint().y / MineSweeper.MASS_SIZE);
				repaint();
				break;
			case MouseEvent.BUTTON3:
				mineSweeper.check(e.getPoint().x / MineSweeper.MASS_SIZE, e.getPoint().y / MineSweeper.MASS_SIZE);
				repaint();
				break;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				Main.window.setFrontScreenAndFocus(ScreenMode.TITLE);
				break;
			}
		}
	}
}