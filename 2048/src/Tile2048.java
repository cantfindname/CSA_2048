
public class Tile2048 extends Tile {
	private int red=255;
	private int green=255;
	private int blue=255;
	private static int totalNum=0;
	
	public Tile2048(int x, int y, int size) {
		super(x,y,size);
		
		if (totalNum<7) {
			int isTwo = (int)(Math.random()*10+1);
			if (isTwo>=7) {
				setValue(2);
				blue = 230;
				green = 250;

			}
			else {
				setValue(4);
				blue = 215;
				green = 245;

			}
		}
		else if (totalNum>=7) {
			int isTwo = (int)(Math.random()*10+1);
			if (isTwo<=3) {
				setValue(2);
				blue = 230;
				green = 250;

			}
			else {
				setValue(4);
				blue = 215;
				green = 245;

			}
		}
		
		setColor(red, green, blue);
		totalNum++;
	}
	
	public void doubleValue(int x, int y) {
		setValue(getValue()*2);
		blue-=20;
		green-=10;

		setColor(red,green,blue);
		moveText(x,y);
	}
	
	public static int getNumTiles() { return totalNum;}
		
	public static void decreaseTiles () { totalNum--;}
	
}
