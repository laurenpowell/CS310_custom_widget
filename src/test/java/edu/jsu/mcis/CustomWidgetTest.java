package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.*;
import java.awt.event.*;

public class CustomWidgetTest {
    private CustomWidget widget;
    
    private Point getCenterOfHexagon() {
        Rectangle boundsHexagon = widget.getShapes()[0].getBounds();
        return new Point(boundsHexagon.x + boundsHexagon.width/2, boundsHexagon.y + boundsHexagon.height/2);
    }
    private Point getCenterOfOctagon(){
        Rectangle boundsOctagon = widget.getShapes()[1].getBounds();
        return new Point(boundsOctagon.x + boundsOctagon.width/2, boundsOctagon.y + boundsOctagon.height/2);
    }
    
    @Before
    public void setUp() {
        widget = new CustomWidget();
    }
    
    @Test
    public void testWidgetIsInitiallyDeselected() {
        assertFalse(widget.hexagonSelected());
        assertFalse(widget.octagonSelected());
    }
    
    @Test
    public void testClickingCenterOfHexagonSelectsIt() {
        Point center = getCenterOfHexagon();
        MouseEvent event = new MouseEvent(widget, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 
                                          0, center.x, center.y, 1, false);
        
        widget.mouseClicked(event);
        assertTrue(widget.hexagonSelected());
    }
    
    @Test
    public void testClickingCenterOfWidgetSelectsIt() {
        Point center = getCenterOfOctagon();
        MouseEvent event = new MouseEvent(widget, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 
                                          0, center.x, center.y, 1, false);
        
        widget.mouseClicked(event);
        assertTrue(widget.octagonSelected());
    }
}
