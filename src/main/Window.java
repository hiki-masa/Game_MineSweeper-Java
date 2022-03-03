package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	/* コンストラクタ */
	public Window(String _WindowName, int _Width, int _Height) {
		setTitle(_WindowName);
		setSize(_Width, _Height);
		setResizable(false);
		
		// Windowの閉じるボタンを押せば，プログラムが終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 背景色の設定
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.GREEN);
	}
	
	public void drawField(int _MassSize, ArrayList<ArrayList<Mine>> _Field) {
		add(new Canvas(_MassSize, _Field));
	}
}

class Canvas extends JPanel {
	Image BasicImg = Toolkit.getDefaultToolkit().getImage("src/basic.png");
	Image OpendImg = Toolkit.getDefaultToolkit().getImage("src/opend.png");
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
        				g.drawImage(MineImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
        			else
        				g.drawImage(OpendImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
        				//g.fillRect(MassSize * x, MassSize * y, MassSize, MassSize);
        		}
        		else {
        			g.drawImage(BasicImg, MassSize * x, MassSize * y, MassSize, MassSize, this);
        		}
        	}
        }
    }
}