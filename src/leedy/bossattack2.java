package leedy;

import javax.swing.JLabel;

public class bossattack2 extends Thread {

	JLabel atk2[][] = new JLabel[7][72];
	int x = 325;
	int y = 700;
	int k = 0;
	int life =0;
	int x1[] = new int[72];
	int y1[] = new int[72];
	int x2[][] = new int[7][72];
	int y2[][] = new int[7][72];
	int bosx = 0;
	boolean key = false;
	boolean font = false;
	static boolean check = false;


	bossattack2(JLabel atk2[][], int x, int y,int k, boolean check) {
		this.atk2 = atk2;
		this.x = x;
		this.y = y;
		this.check=check;
		
		this.k=k;
		for (int i = 0; i < 18; i++) {

			x1[i] = (0 + i);
			y1[i] = (18 - i);
			x1[i + 18] = (18 - i);
			y1[i + 18] = (0 - i);
			x1[i + 36] = (-17 + i);
			y1[i + 36] = (0 - i);
			x1[i + 54] = (0 - i);
			y1[i + 54] = (18 - i);

		}

	}

	public void akt2(int k) {
		 bosx=UI2.bossx;
		for (int j = 0; j < 61; j++) {

			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}

			for (int i = 0; i < 72; i++) {
				x2[k][i] += x1[i];
				y2[k][i] += y1[i];
				atk2[k][i].setVisible(true);
				atk2[k][i].setLocation(bosx + x2[k][i], 150 + y2[k][i]);
				UI2.Crash(x, y, x2[k][i]+350+3, y2[k][i]+150+3, 10, 10, 10, 10,0);		
				if(UI2.check==true){
				}
				if (j == 60) {
					x2[k][i] = 0;
					y2[k][i] = 0;
					atk2[k][i].setVisible(false);

				}
			}
		}

	}

	public static boolean Crash(int cx1, int cy1, int cx2, int cy2, int cw1, int ch1, int cw2, int ch2) {
		if (Math.abs((cx1 + cw1 / 2) - (cx2 + cw2 / 2)) < (cw2 / 2 + cw1 / 2)
			&& Math.abs((cy1 + ch1 / 2) - (cy2 + ch2 / 2)) < (ch2 / 2 + ch1 / 2)) {
			
			check = true;// ??媛믪씠 true硫?check??true瑜??꾨떖?⑸땲??
		} else {
			check = false;
			}
		return check; // check??媛믪쓣 硫붿냼?쒖뿉 由ы꽩 ?쒗궢?덈떎.
	}
	
	public void run() {
		// TODO Auto-generated method stub
		akt2(k);

	}

}
