import java.awt.*;
public class SetLocation{
	static Point getCenterPoint(Dimension frame){
		Dimension desktop=Toolkit.getDefaultToolkit().getScreenSize();	
		return new Point((desktop.width-frame.width)/2,(desktop.height-frame.height)/2);
	}
}