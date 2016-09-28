package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CustomWidget extends JPanel implements MouseListener {
    private java.util.List<ShapeObserver> observers;
    
    
    private final Color SELECTED_COLOR = Color.blue;
    private final Color DEFAULT_COLOR = Color.yellow;
    private boolean selected;
    private Point[] vertexHexagon;
	private Point[] vertexOctagon;
	
    
    public CustomWidget() {
        observers = new ArrayList<>();
        selected = false;
        vertexHexagon = new Point[6];
		vertexOctagon = new Point[8];
        for(int i = 0; i < vertexHexagon.length; i++) { vertexHexagon[i] = new Point(); }
		for(int i = 0; i < vertexOctagon.length; i++) { vertexOctagon[i] = new Point(); }
        Dimension dim = getPreferredSize();
        calculateVertices(dim.width, dim.height);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
    }

    
    public void addShapeObserver(ShapeObserver observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }
    public void removeShapeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        ShapeEvent event = new ShapeEvent(selected, selected);
        for(ShapeObserver obs : observers) {
            obs.shapeChanged(event);
        }
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    private void calculateVertices(int width, int height) {
		int side = Math.min(width, height) / 4;
        Point[] sign = new Point[6];
	    for (int i = 0; i < vertexHexagon.length; i++){
		   double radius = (i * (Math.PI / (vertexHexagon.length / 2)));
           vertexHexagon[i].setLocation(width/3 + (Math.cos(radius)* (side/2)), height/2 + (Math.sin(radius) * (side/2)));
		}
		for (int i = 0; i < vertexOctagon.length; i++){
		   double radius = (i *(Math.PI / (vertexOctagon.length / 2))) + (Math.PI * 0.125);
           vertexOctagon[i].setLocation((width - width/3) + (Math.cos(radius)* (side/2)), height/2 + (Math.sin(radius) * (side/2)));
		}
		
	}
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        calculateVertices(getWidth(), getHeight());
		
        Shape[] shape = getShapes();
		
        g2d.setColor(Color.black);
        g2d.draw(shape[0]);
		g2d.draw(shape[1]);
		
        if(selected) {
            g2d.setColor(SELECTED_COLOR);
            g2d.fill(shape[0]);
			g2d.fill(shape[1]);
        }
        else {
            g2d.setColor(DEFAULT_COLOR);
            g2d.fill(shape[0]);
			g2d.fill(shape[1]);           
        }
    }

    public void mouseClicked(MouseEvent event) {
        Shape[] shape = getShapes();
        if(shape[0].contains(event.getX(), event.getY())) {
            selected = !selected;
            notifyObservers();
        }
        repaint(shape[0].getBounds());
		
		
        if(shape[1].contains(event.getX(), event.getY())) {
            selected = !selected;
            notifyObservers();
        }
        repaint(shape[1].getBounds());
    }
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}
    
    public Shape[] getShapes() {
		Shape[] shape = new Shape[2];
        int[] x = new int[vertexHexagon.length];
        int[] y = new int[vertexHexagon.length];
        for(int i = 0; i < vertexHexagon.length; i++) {
            x[i] = vertexHexagon[i].x;
            y[i] = vertexHexagon[i].y;
		}
		shape[0] = new Polygon(x, y, vertexHexagon.length);
		
		x = new int[vertexOctagon.length];
        y = new int[vertexOctagon.length];
        for(int i = 0; i < vertexOctagon.length; i++) {
            x[i] = vertexOctagon[i].x;
            y[i] = vertexOctagon[i].y;
        }
		
        
		shape[1] = new Polygon(x, y, vertexOctagon.length);
		
        return shape;
    }
	
    public boolean isSelected() { return selected; }



	public static void main(String[] args) {
		JFrame window = new JFrame("Custom Widget");
        window.add(new CustomWidget());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 300);
        window.setVisible(true);
	}
}