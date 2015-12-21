package leedy;

import javax.swing.JLabel;

public class Short1 extends Thread 
{
	JLabel uatk1[] = new JLabel[10];	//user의 미사일을 표시하기 위한 JLabel객체 생성
	int uatk1y[] = new int[10];			//이 미사일에 대한 y값에 대한 배열 변수 선언
	boolean[] fl = new boolean[10];		//미사일의 boolean 배열 변수 선언
	int xx[] = new int[10];				//미사일의 x값에 대한 배열 변수 선언

	Short1(int a, JLabel uatk1[]) 		//Short1 Class의 int 값과 JLabel의 매개변수를 가지는 생성자
	{
		this.uatk1 = uatk1;
		for (int i = 0; i < 10; i++) 
			fl[i] = false;
	}

	@Override
	public void run() 					//Short1 class의 Thread를 실행한 run method override 부분
	{
		while (UI2.atkdelay == true) //UI2 Class의 atkdelay가 true일시에 무한 반복을 한다.
		{
			if (UI2.a > 8) 			//UI2의 a변수가 8보다 클시 초기화 시키는 조건문
				UI2.a = 0;
			else 					//a변수가 8보다 작거나 같을시에 a값을 증가
				UI2.a++;

			mi(UI2.a);				//mi method에 UI2 class의 a값을 매개변수로 보냄
			UI2.atkdelay = false;	//UI2 class의 atkdelay를 false로 변경
		}

	}

	public void mi(int a)			//미사일에 대한 method
	{
		if (fl[a] == false) 		//fl배열에 index값에 대한 boolean값이 false일때 조건문 실행
		{
			fl[a] = true;			//그 index값을 true로 갱신
			for (int j = 0; j < 70; j++) 
			{
				try 
				{
					Thread.sleep(10);
				}
				catch (Exception e) {}
				
				xx[a] = UI2.x;			//충돌을 위하여 UI2 class의 x좌표의 값을 xx배열의 index값에 대입
				uatk1[a].setLocation(UI2.x-3, (UI2.y-10)+(uatk1y[a] -= 10));	//user 미사일의 위치를 바뀌는 것을 표시
				UI2.Crash(xx[a], UI2.y+uatk1y[a], UI2.bossx, 100, 30, 30, 100, 20,a+10);	//충돌에 대한 매개변수를 통하여 넘겨준다.
				if(UI2.check2[a]==true)		//미사일 배열의 index번호가 true가 될시 다음과 같은 조건문 실행
				{
					uatk1[a].setLocation(-30, -30);	//조건문의 index번호의 미사일 위치를 바꿈
					uatk1y[a] = 0;					//y축의 배열값도 0으로 바꿈
					UI2.check2[a]=false;			//index번호가 맞는 boolean 값을 false 초기
					break;
				}
			}
		} 
	}
}
