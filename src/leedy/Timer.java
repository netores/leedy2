package leedy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Timer extends Thread 				//Thread를 상속받는 Timer class를 생성
{
	JButton startbtn;						
	JLabel timela;
	boolean stopt =true;
	static long now;							//now 변수를 static 변수로 설정
		 
	public Timer(boolean stopt,JButton startbtn,JLabel timela)	//Timer class를 다음과 같은 매개변수를 가지는 생성자
	{
		this.stopt=stopt;
		this.startbtn =startbtn;
		this.timela = timela;
	}
		
	public void timer() 									//아무 매개변수를 가지지 않는 기본 생성자
	{
		long timestart =0;
		long nowm;
		long nows;
		long nowms;
		while (stopt==true)									//stopt변수가 true일 동안 startbtn에 대한 액션리스너 감지
		{
			startbtn.addActionListener(new ActionListener() 
			{ 	// 스타트버튼을 누른뒤에는 비활성화 되도록
				public void actionPerformed(ActionEvent e) 
				{ 	// 스타트버튼 안보이게 하는 엑션 생성
					stopt = false;							//startbtn이 눌리는 것 감지시 stopt변수 false로 변경
				}
			});
		}
		
		while (stopt==false&&UI2.gameover==false)//stopt변수가 false이고 UI2 class의 gameover 변수가 false일 경우
		{										 //무한 루프 수행
			try 
			{
				if (timestart==0) 				//timestart변수가 0일시 현재시간을 millisecond단위로 변수에 담는다
					timestart = System.currentTimeMillis();

				now = (System.currentTimeMillis()-timestart); //계속 바뀌는 현재 시간에 timestart변수와의 차이를 통해 timer구현
				nowm = now/60000;
				nows = now%60000/1000;
				nowms = now%1000/100;

				timela.setText(myMethod(nowm)+":"+myMethod(nows)+"."+ nowms); //JLabel의 timela에 다음과 같은 값을 표시해준다.
				Thread.sleep(100);
			} 
			catch (Exception e) {}
		}
	}

	public static String myMethod(long num)	//timer의 00 : 00 : 00과 같이 표시를 하기 위한 method
	{ 
		return ((num+"").length() == 1)? "0"+num : num+"";  //받는 num값이 한자리 숫자이면 앞에 0을 붙여주어 return해준다
	} 
	@Override
	public void run()	//thread override method
	{
			timer();	//timer method를 실행
	}
}
