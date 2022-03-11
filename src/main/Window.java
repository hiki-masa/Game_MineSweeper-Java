package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements MouseListener{
	private final MineSweeper app;
	/* コンストラクタ */
	public Window(MineSweeper _app, String _WindowName, int _Width, int _Height) {
		this.app = _app;
		
		// Windowの設定
		setTitle(_WindowName);
		setSize(_Width, _Height);
		setResizable(true);
		
		// Windowの閉じるボタンを押せば，プログラムが終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 背景色の設定
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.GREEN);
		
		// マウスイベントの感知
		addMouseListener(this);
	}
	
	public void drawField() {
		add(new Canvas(app.getMassSize(), app.getField()));
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
		// 左クリック
		case MouseEvent.BUTTON1:
			this.app.Open(e.getPoint().x, e.getPoint().y - app.getMassSize() / 2);
			break;
		// 右クリック
		case MouseEvent.BUTTON3:
			this.app.Check(e.getPoint().x, e.getPoint().y - app.getMassSize() / 2);
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

class Canvas extends JPanel {
	static private Image BasicImg = Toolkit.getDefaultToolkit().getImage("src/basic.png");
	static private Image OpendImg = Toolkit.getDefaultToolkit().getImage("src/opend.png");
	static private Image Open1Img = Toolkit.getDefaultToolkit().getImage("src/one.png");
	static private Image Open2Img = Toolkit.getDefaultToolkit().getImage("src/two.png");
	static private Image Open3Img = Toolkit.getDefaultToolkit().getImage("src/three.png");
	static private Image Open4Img = Toolkit.getDefaultToolkit().getImage("src/four.png");
	static private Image MineImg  = Toolkit.getDefaultToolkit().getImage("src/mine.png");
	static private Image CheckImg = Toolkit.getDefaultToolkit().getImage("src/check.png");
	private int MassSize;
	ArrayList<ArrayList<Mine>> Field;
	
	public Canvas(int _MassSize, ArrayList<ArrayList<Mine>> _Field) {
		MassSize = _MassSize;
		Field  = _Field;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int x = 0; x < Field.get(0).size(); x++) {
			for (int y = 0; y < Field.size(); y++) {
				if (Field.get(y).get(x).isOpen()) {
					if (Field.get(y).get(x).isMine())
						g.drawImage(MineImg,  MassSize * x, MassSize * y, MassSize, MassSize, this);
					else {
						switch (Field.get(y).get(x).getRoundMineCounter()){
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
						}
					}
				}
				else {
					if (Field.get(y).get(x).isCheck())
						g.drawImage(CheckImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
					else
						g.drawImage(BasicImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
				}
			}
		}
	}
}