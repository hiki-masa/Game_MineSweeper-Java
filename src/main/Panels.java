package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
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
		// フォントの設定
		font = new Font("Century", Font.PLAIN, 30);
		// テキストラベルの作成
		label = new JLabel();
		// ボタンの作成
		buttonScreenTransitionToGame = new JButton("Gameへ画面遷移");
		// ボタンリスナーの登録
		titleButtonListener = new TitleButtonListener();
		panelWidth = 500;
		panelHeight = 500;
		new TitleKeyAdapter(this);
	}

	@Override
	protected void prepareComponent() {
		// パネルの背景色の設定
		super.setBackground(Color.black);

		// テキストラベルの更新
		label.setText("Title");
		// フォントの反映
		label.setFont(this.font);
		// フォントの色指定
		label.setForeground(Color.red);
		// 水平位置・垂直位置の設定
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		// 背景色の設定
		label.setOpaque(true);
		label.setBackground(Color.gray);

		// ボタンにリスナー登録
		buttonScreenTransitionToGame.addActionListener(titleButtonListener);

		// パネルに各コンポーネントを追加
		super.setLayout(new BorderLayout());
		super.add(this.label, BorderLayout.NORTH);
		super.add(this.buttonScreenTransitionToGame, BorderLayout.CENTER);
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
	static private Image BasicImg = Toolkit.getDefaultToolkit().getImage("src/basic.png");
	static private Image MineImg  = Toolkit.getDefaultToolkit().getImage("src/mine.png");
	static private Image CheckImg = Toolkit.getDefaultToolkit().getImage("src/check.png");
	static private Image OpendImg = Toolkit.getDefaultToolkit().getImage("src/opend.png");
	static private Image Open1Img = Toolkit.getDefaultToolkit().getImage("src/one.png");
	static private Image Open2Img = Toolkit.getDefaultToolkit().getImage("src/two.png");
	static private Image Open3Img = Toolkit.getDefaultToolkit().getImage("src/three.png");
	static private Image Open4Img = Toolkit.getDefaultToolkit().getImage("src/four.png");
	static private Image Open5Img = Toolkit.getDefaultToolkit().getImage("src/five.png");
	static private Image Open6Img = Toolkit.getDefaultToolkit().getImage("src/six.png");
	static private Image Open7Img = Toolkit.getDefaultToolkit().getImage("src/seven.png");
	static private Image Open8Img = Toolkit.getDefaultToolkit().getImage("src/eight.png");

	public GamePanel() {
		// キーリスナーの登録
		new GameMouseAdapter(this);
	}

	@Override
	protected void prepareComponent() {
		// パネルの背景色の設定
		super.setBackground(Color.black);
		this.mineSweeper = new MineSweeper(15, 10);
		panelWidth = MineSweeper.MassSize * 15;
		panelHeight = MineSweeper.MassSize * 10;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int MassSize = MineSweeper.MassSize;
		
		int x = 0, y = 0;
		for (ArrayList<Mine> mineList : mineSweeper.getField()) {
			x= 0;
			for (Mine mine : mineList) {
				if (mine.isOpen()) {
					if (mine.isMine())
						g.drawImage(MineImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
					else {
						switch (mineSweeper.getField().get(y).get(x).getRoundMineCounter()){
						case 0:
							g.drawImage(OpendImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 1:
							g.drawImage(Open1Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 2:
							g.drawImage(Open2Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 3:
							g.drawImage(Open3Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 4:
							g.drawImage(Open4Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 5:
							g.drawImage(Open5Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 6:
							g.drawImage(Open6Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 7:
							g.drawImage(Open7Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						case 8:
							g.drawImage(Open8Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
							break;
						}
					}
				}
				else {
					if (mineSweeper.getField().get(y).get(x).isCheck())
						g.drawImage(CheckImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
					else
						g.drawImage(BasicImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
				}
				x++;
			}
			y++;
		}
	}

	private class GameMouseAdapter extends MouseAdapter {
		/* コンストラクタ */
		private GameMouseAdapter(GamePanel p) {
			super();
			p.addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				mineSweeper.Open(e.getPoint().x / MineSweeper.MassSize, e.getPoint().y / MineSweeper.MassSize);
				repaint();
				break;
			case MouseEvent.BUTTON3:
				mineSweeper.Check(e.getPoint().x / MineSweeper.MassSize, e.getPoint().y / MineSweeper.MassSize);
				repaint();
				break;
			}
		}
	}
}