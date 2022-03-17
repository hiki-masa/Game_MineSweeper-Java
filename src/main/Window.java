package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	/* コンストラクタ */
	public Window(String _WindowName, int _Width, int _Height) {
		// Windowの設定
		setTitle(_WindowName);
		setSize(_Width, _Height);
		setResizable(true);
		
		// Windowの閉じるボタンを押せば，プログラムが終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 背景色の設定
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.GREEN);
	}
}

class Canvas extends JPanel {
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
	private ArrayList<ArrayList<Mine>> Field;
	
	// コンストラクタ
	public Canvas(final ArrayList<ArrayList<Mine>> _Field) {
		Field = _Field;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int MassSize = MineSweeper.MassSize;
		
		for (int x = 0; x < Field.get(0).size(); x++) {
			for (int y = 0; y < Field.size(); y++) {
				if (Field.get(y).get(x).isOpen()) {
					if (Field.get(y).get(x).isMine())
						g.drawImage(MineImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
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
					if (Field.get(y).get(x).isCheck())
						g.drawImage(CheckImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
					else
						g.drawImage(BasicImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
				}
			}
		}
	}
}