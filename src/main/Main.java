package main;

public class Main {
	public static void main(String[] args) {
		MineSweeper App = new MineSweeper(15, 10);
		App.Frame.setVisible(true);
		App.dispCMD();
	}
}