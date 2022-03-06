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
	MineSweeper app;
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
		add(new Canvas(this.app.MassSize, this.app.getField()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.app.Open(e.getPoint().x, e.getPoint().y - app.MassSize / 2);
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
	Image BasicImg = Toolkit.getDefaultToolkit().getImage("src/basic.png");
	Image OpendImg = Toolkit.getDefaultToolkit().getImage("src/opend.png");
	Image Open1Img = Toolkit.getDefaultToolkit().getImage("src/one.png");
	Image Open2Img = Toolkit.getDefaultToolkit().getImage("src/two.png");
	Image Open3Img = Toolkit.getDefaultToolkit().getImage("src/three.png");
	Image MineImg  = Toolkit.getDefaultToolkit().getImage("src/mine.png");
	int MassSize;
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
        			else if (Field.get(y).get(x).RoundMineCounter == 0)
        				g.drawImage(OpendImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
        			else if (Field.get(y).get(x).RoundMineCounter == 1)
        				g.drawImage(Open1Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
        			else if (Field.get(y).get(x).RoundMineCounter == 2)
        				g.drawImage(Open2Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
        			else if (Field.get(y).get(x).RoundMineCounter == 3)
        				g.drawImage(Open3Img, MassSize * x, MassSize * y, MassSize, MassSize, this);
        		}
        		else {
        			g.drawImage(BasicImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
        		}
        	}
        }
    }
}