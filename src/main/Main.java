package main;

public class Main {
	public static void main(String[] args) {
		MineSweeper App = new MineSweeper(15, 10);
		App.Frame.setVisible(true);
		App.dispCMD();
		
		/*
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		App.setMine();
		*/
	}
}