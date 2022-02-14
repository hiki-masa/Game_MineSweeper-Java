package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
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
	
	public void FillRect(int _x, int _y, int _width, int _height) {
		add(new Canvas(_x, _y, _width, _height));
	}
}

class Canvas extends JPanel {
	//Image Img = Toolkit.getDefaultToolkit().getImage("src/sample.png");
	int X, Y, Width, Height;
	public Canvas(int _x, int _y, int _width, int _height) {
		X = _x;
		Y = _y;
		Width = _width;
		Height = _height;
	}
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(Img, X, Y, Width, Height, this);
        g.fillRect(X, Y, Width, Height);
    }
}