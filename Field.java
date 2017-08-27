package mines;

import java.util.concurrent.ThreadLocalRandom;

public class Field {
	/*public enum Cell {
		Empty,1,2,3,4,5,6,7,8,Mine
	}*/

	private int[][] field;
	private int sizeX = 20, sizeY = 20, percentage = 20;

	public Field() {
		this.sizeX      = 20;
		this.sizeY      = 20;
		this.percentage = 20;
	}

	public Field(int sizeX, int sizeY, int percentage) {
		this.sizeX      = sizeX;
		this.sizeY      = sizeY;
		this.percentage = percentage;
	}

	public int GetSizeX()      { return this.sizeX;      }
	public int GetSizeY()      { return this.sizeY;      }
	public int GetPercentage() { return this.percentage; }

	public void SetSizeX(int sizeX)           { this.sizeX = sizeX;           }
	public void SetSizeY(int sizeY)           { this.sizeY = sizeY;           }
	public void SetPercentage(int percentage) { this.percentage = percentage; }

	public void Build() {
		int mines = (int) Math.round( (sizeX*sizeY)/(percentage*0.01) );

		boolean complete;
		for (int i = 0; i < mines; i++) {
			complete = false;
			while (!complete) {
				int x = ThreadLocalRandom.current().nextInt(0, this.sizeX + 1);
				int y = ThreadLocalRandom.current().nextInt(0, this.sizeY + 1);

				if (this.field[x][y] != 9) {
					this.field[x][y] = 9;
				}
			}
		}

		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				int found = 0;

				if (x > 0) {

					if (y > 0) {
						if (this.field[x-1][y-1] == 9)
							found++;
					}
					if (this.field[x-1][y] == 9)
						found++;
					if (y < sizeY-1) {
						if (this.field[x-1][y+1] == 9)
							found++;
					}

				}

				if (y > 0) {
					if (this.field[x][y-1] == 9)
						found++;
				}
				if (y < sizeY-1) {
					if (this.field[x][y+1] == 9)
						found++;
				}

				if (x < sizeX - 1) {
					if (y > 0) {
						if (this.field[x+1][y+1] == 9)
							found++;
					}
					if (this.field[x+1][y] == 9)
						found++;
					if (y < sizeY-1) {
						if (this.field[x+1][y+1] == 9)
							found++;
					}
				}


				this.field[x][y] = found;
			}
		}
	}

	public char[][] ToString() {
		char[][] field = new char[sizeX][sizeY];

		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				if (this.field[x][y] == 0) {
					field[x][y] = ' ';
				} else if (this.field[x][y] == 9) {
					field[x][y] = '*';
				} else if (this.field[x][y] > 0 && this.field[x][y] < 9) {
					field[x][y] = (char) (this.field[x][y] + 48);
				}
					
			}
		}

		return field;
	}

	public void Print() {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				System.out.printf(String.valueOf(this.field[x][y]));
			}
			System.out.println();
		}
	}
}
