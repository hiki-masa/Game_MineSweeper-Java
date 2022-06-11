package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/*
 * キーリスナーのアダプター
 * */
class KeyAdapter implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		;
	}

}

/*
 * マウスリスナーのアダプター
 * */
class MouseAdapter implements MouseListener, MouseMotionListener, MouseWheelListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		;
	}

}

/*
 * マウス・キーリスナーのアダプター
 * */
class MouseKeyAdapter implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		;
	}

}