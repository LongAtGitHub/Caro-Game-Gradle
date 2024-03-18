package Mini_Components;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

/**
 * Everything is related to the graphicsgroup, so set grid and image at 0,0 is the right choice. 
 */
public class Grid extends GraphicsGroup {
    private Rectangle rectangle;
    private Image image;
    private double imageMargin;
    private int indexX; 
    private int indexY; 
    private Character value;
    private double x,y, gridSize;

    // public Grid(double size, double x, double y) {
    //     this(size, x, y, 0, 0);
    // }

    public Grid(double size, double x, double y, int indexX, int indexY) {
        super(x, y);
        this.x = x;
        this.y = y ;
        this.indexX = indexX;
        this.indexY = indexY;

        this.rectangle = new Rectangle(0, 0, size, size);
        this.rectangle.setStrokeWidth(3);
        this.add(rectangle);
        this.gridSize = size;

        this.imageMargin  = size/10;
    }

    public double getSquareSize() {
        return rectangle.getHeight();
    }

    public Point getIndices() {
        return new Point(this.indexX, this.indexY);
    }

    public Boolean setCharValue(Character c) {
        if (this.value != null) {return false;}
        else if (c!='X' && c!='O') {return false;}
        else if (c=='X') {
            this.value = 'X';
            this.image = new Image(imageMargin, imageMargin, "img/X.png");  
        }
        else if (c=='O') {
            this.value = 'O';
            this.image = new Image(imageMargin, imageMargin, "img/O.png");  
        }
        else {}
        this.image.setMaxWidth(gridSize);
        this.image.setMaxHeight(gridSize);

        this.add(image, 0,0);
        // System.out.println("I am grid" + indexX + " " + indexY);
        return true;
    }
}
