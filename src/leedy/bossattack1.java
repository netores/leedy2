package leedy;

import javax.swing.JButton;
import javax.swing.JLabel;

public class bossattack1 extends Thread	//bossattack1 class에 thread 상속
{
	JButton boss;						//boss에 대한 JButton
	JLabel atk1[] = new JLabel[11];		//미사일에 대한 배열 라벨
	int x = 325;
	int y = 700;
	int bossx = 300;
	int atk1x[] = new int[11];
	int atk1y[] = new int[11];
	int atk1xx[] = new int[11];
	int atk1yy[] = new int[11];
	int atk1yyy[] = new int[11];
	
	bossattack1(int x, int bossx, JLabel[] atk1)
	{
		this.x=x;
		this.bossx=bossx;
		this.atk1=atk1;
	}

	@Override
	public void run() 				//thread의 run method
	{
		int bossy = 100;
		for (int i = 1; i < 100; i++) 
		{
			try 
			{
				Thread.sleep(70);
				if (i < 30) 	//변수 i를 통하여 미사일의 속도 제어
				{
					for (int j = 0; j < 11; j++) 
					{
						atk1x[j] += (j - 5)*2;		//미사일에 x좌표값을 각 배열의 미사일 제어
						atk1[j].setLocation((bossx + 50) + atk1x[j], bossy + (atk1y[j] -= 3));	
						//10개의 미사일들이 각 index마다의 위치를 표시
					}
				}
				else if(i <31)
				{
					for (int j = 0; j < 11; j++) 
					{
						atk1x[j] = (atk1x[j] + 50 + bossx);
						atk1y[j] = (atk1y[j] + bossy);
						atk1xx[j] = (x-atk1x[j])/10;
						atk1yy[j] = (y-atk1y[j])/55;
								
					}
				}
				else 
				{
					for(int k = 1 ; k < 12 ; k++)
					{					
						for (int j = 0; j < k; j++) 	//i가 30보다 클때의 미사일의 속도와 충돌에 대한 반복문
						{
							atk1yyy[j]++;
							atk1[j].setLocation(atk1x[j]+=atk1xx[j],atk1y[j]+=atk1yy[j]*(atk1yyy[j]));
							UI2.Crash(UI2.cha1.getX(), UI2.cha1.getY(), atk1x[j], atk1y[j], 10, 10, 7, 70, 0);
							if(UI2.check==true)
							{
								UI2.check=false;
								atk1[j].setLocation(350, bossy);
								break;
							}
						}
					}
				}
			} 
			catch (Exception e) {	}
		}

	}
}
