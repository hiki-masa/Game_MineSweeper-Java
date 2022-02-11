package minesweeper;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class Window extends JFrame{
	public Window(String WindowName) {
		setTitle(WindowName);
		setSize(400, 400);
		setResizable(false);
		
		// Windowの閉じるボタンを押せば，プログラムが終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 背景色の設定
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.green);
		
		// Windowの表示
		setVisible(true);
	}
}