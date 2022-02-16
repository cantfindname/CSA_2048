import doodlepad.Shape;
import doodlepad.Text;

public class Twenty48 extends FourByFour {
	private Tile2048[][] tiles;
	
	public Twenty48() {
		tiles = new Tile2048[4][4];
		
		
		int t =0;
		while (t<2) {
			int rowRand = (int)(Math.random()*4);
			int colRand = (int)(Math.random()*4);
			if (tiles[rowRand][colRand]==null) {
				tiles[rowRand][colRand]= new Tile2048(getPosX(colRand),getPosY(rowRand),getTileWidth());
				t++;
			}
		}
		
		//tiles[0][0]= new Tile2048(getPosX(0),getPosY(0),getTileWidth());
		//tiles[2][1]= new Tile2048(getPosX(1),getPosY(2),getTileWidth());
	}
	public void addAnother() {
		int rowRand = (int)(Math.random()*4);
		int colRand = (int)(Math.random()*4);
		while (tiles[rowRand][colRand]!=null) {
			rowRand = (int)(Math.random()*4);
			colRand = (int)(Math.random()*4);
		}
		tiles[rowRand][colRand]= new Tile2048(getPosX(colRand), getPosY(rowRand), getTileWidth());
		
	}
	
	public void move(int oRow, int oCol, int nRow, int nCol) {
		/*
		Tile2048 t = tiles[oRow][oCol];
		tiles[nRow][nCol]=t;
		tiles[oRow][oCol]=null;
		t.setLocation(getPosX(nCol), getPosY(nRow));
		*/
		
		tiles[nRow][nCol]=tiles[oRow][oCol];
		int x = getPosX(nCol);
		int y = getPosY(nRow);
		tiles[nRow][nCol].setLocation(x, y);
		tiles[oRow][oCol]=null;
	}
	
	//my Code
	/*
	public void move(int oRow, int oCol, int nRow, int nCol) {
		
		tiles[oRow][oCol].setLocation(getPosY(nRow),getPosX(nCol));
		tiles[oRow][oCol].moveText(getPosY(nRow), getPosX(nCol));
	}
	*/
	
	//my code:
	/* 
	public void merge(int r1, int c1, int r2, int c2) {
		tiles[r2][c2].doubleValue(getPosX(c2), getPosY(r2));
		Shape a = (Shape)tiles[r1][c1];
		removeShape(a);
		Shape b = (Shape)tiles[r1][c1].getText();
		removeShape(b);
		tiles[r1][c1]=null;
		System.out.println("check if null " + tiles[r1][c1]); //remember to delete
		Tile2048.decreaseTiles();
	}
	*/
	
	public void merge(int r1, int c1, int r2, int c2) {
		
		/*
		Tile2048 t = tiles[r1][c1];
		Text txt = t.getText();
		removeShape(t);
		removeShape(txt);
		tiles[r2][c2].doubleValue(getPosX(c2), getPosY(r2));
		tiles[r1][c1]=null;
		Tile2048.decreaseTiles();
		*/
		
		int x = getPosX(c2);
		int y = getPosY(r2);
		tiles[r2][c2].doubleValue(x,y);
		Tile2048.decreaseTiles();
		removeShape(tiles[r1][c1]);
		removeShape(tiles[r1][c1].getText());
		tiles[r1][c1]=null;
	}
	
	public boolean moveRight() { 
		int newCol;
		boolean moved = false;
		
		for (int r =0;r<4;r++) {
			for (int c=2;c>=0;c--) {
				if (tiles[r][c]!=null) {
					newCol = c+1; 
					while (newCol<4 && tiles[r][newCol]==null) {
						newCol++;
					}
					if (newCol != 4 && tiles[r][c].getValue()==tiles[r][newCol].getValue()) {
						merge(r,c,r,newCol);
						moved=true;
					}
					else {
						if (tiles[r][newCol-1]==null) {
							move(r,c,r,newCol-1);
							moved = true;
						}
					}
				}
			}
		}
		return moved;
	}
	

	public boolean moveLeft() {
		int newCol;
		boolean moved = false;
		for (int r=0;r<tiles.length;r++) {
			for (int c=1;c<tiles[r].length;c++) {
				if (tiles[r][c]!=null) {
					newCol=c-1;
					while (newCol!=-1 && tiles[r][newCol]==null) {
						newCol--;
					}
					if (newCol!=-1 && tiles[r][c].getValue()==tiles[r][newCol].getValue()) {
						merge(r,c,r,newCol);
						moved=true;
					}
					else {
						if (tiles[r][newCol+1]==null) {
							move(r,c,r,newCol+1);
							moved = true;
						}
					}
				}
			}
		}
		return moved;
	}
	
	public boolean moveUp() {
		int newRow;
		boolean moved= false;
		for (int c=0;c<4;c++) {
			for (int r=1;r<4;r++) {
				if (tiles[r][c]!=null) {
					newRow=r-1;
					while(newRow>-1 && tiles[newRow][c]==null) {
						newRow--;
					}
					if (newRow!=-1 && tiles[r][c].getValue()==tiles[newRow][c].getValue()) {
						merge(r,c,newRow,c);
						moved=true;
					}
					else {
						if (tiles[newRow+1][c]==null) {
							move(r,c,newRow+1,c);
							moved=true;
						}
					}
				}
			}
		}
		return moved;
	}
	
	public boolean moveDown() {
		int newRow;
		boolean moved= false;
		for (int c=0;c<4;c++) {
			for (int r=2;r>=0;r--) {
				if (tiles[r][c]!=null) {
					newRow=r+1;
					while (newRow!= 4 && tiles[newRow][c]==null) {
						newRow++;
					}
					if (newRow!=4 && tiles[r][c].getValue()==tiles[newRow][c].getValue()) {
						merge(r,c,newRow,c);
						moved=true;
					}
					else {
						if (tiles[newRow-1][c]==null) {
							move(r,c,newRow-1,c);
							moved= true;
						}
					}
				}
			}
		}
		return moved;
	}
	
	public boolean noMoves() {
		for (int r=0; r<3; r++) {
			for (int c=0; c<4; c++) {
				if (tiles[r][c].getValue()==tiles[r+1][c].getValue()) {
					return false;
				}
			}
		}
		for (int c=0; c<3; c++) {
			for (int r=0; r<4; r++) {
				if (tiles[r][c].getValue()==tiles[r][c+1].getValue()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void onKeyPressed(String keyText, String key) {
		boolean valid = false;
		if (Tile2048.getNumTiles()==16 && noMoves()) {
			setEventsEnabled(false);
			Text t = new Text("GAME\nOVER", 30, 80, 80);
			return;
		}
		
		if (keyText.equals("W")) {
			valid=moveUp();
		}
		if(keyText.equals("S")) {
			valid=moveDown();
		}
		if(keyText.equals("A")) {
			valid=moveLeft();
		}
		if(keyText.equals("D")) {
			valid=moveRight();
		}
		if (valid) {
			addAnother();
		}
	}

}
