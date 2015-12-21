package leedy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UI2 extends JFrame implements Runnable, KeyListener 
{
	static boolean atkdelay = false;				//static 변수를 사용하여 new를 안하고 개체를 생성할 수 있으며
	static boolean keyspece = false;				//다른 클래스에서도 자유롭게 사용할 수 있는 변수들.
	static boolean check = false;
	static boolean check2[] = new boolean[10];		
	static boolean check3 = false;
	static boolean check4 = false;
	static int life = 30;						//player 체력에 대한 변수 선언
	static int x = 325;							//x좌표에 대한 변수 선언
	static int y = 650;							//y좌표에 대한 변수 선언
	static int bossx = 300;						//boss에 대한 x좌표 변수선언
	static int a = 0;							
	static int bosshp = 1000;					//boss에 대한 체력 변수 선언
	static int type = 0;						//충돌에 대한 변수 선언으로 충돌에 대한 변수에 따라 조건문에 쓰임
	static boolean gameover = false;			//게임의 시작과 끝에 대한 변수선언
	static int level = 1;						//난이도 조절을 위한 level변수 선언
	static JButton cha1; 						// 캐릭터
	
	boolean KeyUp = false; 						// 키보드 입력 처리를 위한 변수
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean stopt = true;
	boolean mif = false;
	boolean mim = false;
	boolean predelay = false;
	boolean bossmovef = false;
	
	String rank[] = new String[8];				//순위시스템을 위한 String type의 배열 변수 선언
	String imsi1, imsi2 , a2, username;			//순위 시스템에 대한 임시저장값을 담는 변수와 이름입력을 위한 변수 선언
	
	int exp = 0;								//경험치에 대한 변수 생성
	int pattontime = 0;							//보스 공격패턴에 대한 변수 생성
	int pattontime2 = 0;						//보스 공격패턴에 대한 변수 생성
	int miy = 700;								//y좌표 변수 생성
	int k2 = 0;									//보스 공격패턴에 대한 변수 생성
	int bossmt = 0;								//보스 이동 패턴에 대한 변수 생성
	int bossmr = 0;								//보스 이동 패턴에 대한 변수 생성
	int delayt = 0;								//내 미사일에 대한 변수 생성
	int i;
	
	FileInputStream fis;						//파일을 읽어올 수 있도록 변수 선언
	BufferedReader br;
	InputStreamReader isr;
	
	boolean startflag;

	JButton startbtn; 							// 스타트 버튼
	
	JLabel timela, bosshpbar, bosshpbarbo, bgimage, bgtime, gameover1;
	JLabel boss, ulife, border, playerhp,expi ,cha;
	
	JLabel atk1[] = new JLabel[11];
	JLabel ranks[] = new JLabel[9];
	JLabel atk2[][] = new JLabel[7][72];
	JLabel uatk1[] = new JLabel[10];
	
	
	//라벨과 버튼에 대한 이미지 아이콘 객체 생성
	ImageIcon icon1 = new ImageIcon("src\\image\\tan1.gif");
	ImageIcon icon2 = new ImageIcon("src\\image\\tan.gif");
	ImageIcon icon3 = new ImageIcon("src\\image\\hpbar.gif");
	ImageIcon icon4 = new ImageIcon("src\\image\\hpbarboader.gif");
	ImageIcon icon5 = new ImageIcon("src\\image\\background.gif");
	ImageIcon icon6 = new ImageIcon("src\\image\\watchsoo.gif");
	ImageIcon icon7 = new ImageIcon("src\\image\\ug.gif");
	ImageIcon icon8 = new ImageIcon("src\\image\\boss.gif");
	ImageIcon icon9 = new ImageIcon("src\\image\\startbtn.gif");
	ImageIcon icon10 = new ImageIcon("src\\image\\border.gif");
	ImageIcon icon11 = new ImageIcon("src\\image\\hpchar.gif");
	ImageIcon icon12 = new ImageIcon("src\\image\\hpingame.gif");
	ImageIcon icon13 = new ImageIcon("src\\image\\expbar.gif");
	ImageIcon icon14 = new ImageIcon("src\\image\\char.gif");
	ImageIcon icon15 = new ImageIcon("src\\image\\char2.gif");

	UI2(String title) 
	{
		super(title);										//처음 frame에 대한 기본값을 선언
		setBounds(0, 0, 1000, 750);							//frame의 좌표와 크기에 대한 선언
		setLayout(null);									//frame의 배치를 내 마음대로 할 수 있도록 선언
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//frame의 닫기 버튼을 누르면 프레임을 끄도록 선언
		getContentPane().setBackground(Color.black);		//프레임의 배경색깔을 검은색으로 지정

		cha1 = new JButton("");							//내 캐릭터를 JButton으로 객체를 생성하고 좌표값과 테두리를 없앰
		cha1.setBackground(Color.yellow);
		cha1.setBorder(null);
		cha1.setBounds(x, y, 10, 10);

		cha = new JLabel("");							//내 캐릭터에 대한 객체를
		cha.setIcon(icon14);							//레벨업시 이미지를 바꾸기위해 하나를 더 생성
		cha.setBounds(x-21,y-27,52,65);
		cha.setIcon(icon14);
		
		startbtn = new JButton("");						//start 를 위한 버튼 객체 생성
		
		timela = new JLabel("00:00.0");					//기록을 위한 타이머에 대한 객체 생성
		boss = new JLabel("보스");						//보스에 대한 객체 생성
		

		for (int i = 0; i < 9; i++) 					//반복문을 통해 시간을 통한 순위를 표시할 수 있는 객체 생성
		{
			if (i == 0 | i == 2 | i == 4) 				//배열의 짝수에는 이름이 들어있기 때문에 제어문을 통해 제어
			{
				ranks[i] = new JLabel("이름 없음");
				ranks[i].setBounds(780, 185 + ((i / 2) * 50), 100, 50);
			} 
			else if (i == 1 | i == 3 | i == 5)			//배열의 홀수에는 기록을 위한 시간이 들어 제어문을 통하여 제어
			{
				ranks[i] = new JLabel("99:99.0");
				ranks[i].setBounds(870, 185 + ((i / 2) * 50), 100, 50);
			} 
			else if (i < 9) 							//나머지는 1~3등 이라는 표시를 label에 표시해주며 객체생성
			{
				ranks[i] = new JLabel(i - 5 + "등");
				ranks[i].setBounds(740, 185 + ((i - 6) * 50), 50, 50);
			}
			ranks[i].setForeground(Color.white);						//표시되는 색깔과 글자체 크기를 지정
			ranks[i].setFont(new Font("Malgun Gothic", Font.BOLD, 20));
			add(ranks[i]);
		}
		
		startbtn.setBounds(747, 555, 189, 120);							//start버튼에 대한 위치와 이미지아이콘 등록
		startbtn.setBorder(null);										//버튼의 테두리 속성 제거
		startbtn.setIcon(icon9);
		
		timela.setBounds(760, 65, 280, 70);								//timer 라벨의 위치와 글자체 지정
		timela.setForeground(Color.white);								//timer 라벨의 color 변경
		timela.setFont(new Font("arial", Font.BOLD, 50));

		boss.setBounds(300, 100, 79, 130);								//보스 라벨의 위치와 이미지 아이콘 등록
		boss.setIcon(icon8);
	
		gameover1 = new JLabel("GAME OVER");							//gameover 라벨에 대한 객체 생성
		gameover1.setBounds(200, 250, 480, 70);							//위치와 색깔 글자체와 크기를 등록
		gameover1.setForeground(Color.red);
		gameover1.setFont(new Font("arial", Font.BOLD, 50));
		gameover1.setVisible(false);									//gameover 라벨을 초기에는 보이지 않게 처리
		
		add(gameover1);													//버튼과 라벨을 frame에 추가
		add(cha1);
		add(startbtn);
		add(timela);
		add(cha);	
		add(boss);
		
		
		playerhp = new JLabel("");								//user에 대한 체력을 표시하기 위해 객체 생성
		playerhp.setBounds(753,438,187,20);						//user체력 라벨의 위치와 크기 지정
		playerhp.setIcon(icon11);								//user체력 라벨에 이미지아이콘을 등록
		add(playerhp);											//라벨을 frame에 추가
		
		expi = new JLabel("");									//경험치에 대한 라벨 객체 생성
		expi.setBounds(757,513,0,20);							//경험치 라벨에 대한 위치와 크기 지정
		expi.setIcon(icon13);									//경험치 라벨에 이미지 아이콘 등록
		add(expi);												//라벨을 frame에 추가			
	
		border = new JLabel("");								//플레이가 안되는 쪽의 이미지를 위한 border객체 생성
		border.setBounds(700,0,285,713);						//border 라벨의 위치와 크기 지정
		border.setIcon(icon10);									//border 라벨의 이미지 아이콘 등록
		add(border);											//border 라벨을 frame에 추가
		
		for (int i = 0; i < 10; i++) 						//반복문을 통해 user의 미사일과 충돌 check에 사용될 객체생성 
		{
			check2[i] = false;
			uatk1[i] = new JLabel();
			uatk1[i].setForeground(Color.red);
			uatk1[i].setBounds(-40, -40, 17, 17);
			uatk1[i].setIcon(icon7);
			add(uatk1[i]);
		}

		for (int j = 0; j < 7; j++) 				//반복문을 통해 boss의 2번째 미사일 패턴에 대한 객체 생성
		{
			for (int i = 0; i < 72; i++) 
			{
				atk2[j][i] = new JLabel("");
				atk2[j][i].setForeground(Color.red);
				atk2[j][i].setFont(new Font("arial", Font.PLAIN, 30));
				atk2[j][i].setBounds(300, 400, 17, 17);
				atk2[j][i].setVisible(false);

				add(atk2[j][i]);
				atk2[j][i].setIcon(icon2);
			}
		}

		for (int i = 0; i < 11; i++) 			//반복문을 통해 boss의 첫번째 미사일 패턴에 대한 객체 생서
		{
			atk1[i] = new JLabel("");
			atk1[i].setForeground(Color.cyan);
			atk1[i].setFont(new Font("arial", Font.PLAIN, 30));
			atk1[i].setBounds(bossx, -300, 30, 30);
			add(atk1[i]);
			atk1[i].setIcon(icon1);
		}
		
		startbtn.addKeyListener(this);				// 스타트 버튼에 키보드 이벤트 실행
		Thread key = new Thread(this);				// 현재 class의 run method를 통한 thread 생성
		key.start(); 								// Thread 실행
		startbtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				stopt = false;						//start버튼을 눌럿을 때 stopt boolean변수를 false값으로 변경
			}
		});
		
		ulife = new JLabel("");						//user의 체력을 플레이 화면에서 볼수 있는 객체 생성
		ulife.setBounds(x - 23, y + 45, 56, 6);		//라벨 객체의 위치를 x축으로 플레이어의 -23과 y축으로 45만큼으로 지정과 크기 지정
		ulife.setIcon(icon12);						//라벨에 이미지 아이콘 등록
		add(ulife);									//라벨을 frame에 추가

		bosshpbar = new JLabel("");					//boss의 체력에 대한 label객체 생성
		bosshpbar.setBounds(120, 50, 500, 40);		//label의 초기 위치와 크기 지정
		bosshpbar.setIcon(icon3);					//label의 이미지 아이콘 등록
		add(bosshpbar);								//label을 frame에 추가

		bosshpbarbo = new JLabel("");				//boss체력의 GUI를 위해 테두리를 라벨 객체로 생성
		bosshpbarbo.setBounds(104, 38, 528, 66);	//라벨의 위치와 크기지정
		bosshpbarbo.setIcon(icon4);					//라벨의 이미지 아이콘 등록
		add(bosshpbarbo);							//라벨을 frame에 추가

		bgtime = new JLabel("");					//게임플레이 안의 GUI를 위한 라벨 객체 생성
		bgtime.setBounds(277, 275, 167, 174);		//라벨의 위치와 크기 지정
		bgtime.setIcon(icon6);						//라벨에 이미지 아이콘 등록
		add(bgtime);								//라벨을 frame에 추가

		bgimage = new JLabel("");					//게임플레이 안의 GUI를 위한 라벨 객체 생성
		bgimage.setBounds(0, 0, 700, 712);			//라벨의 위치와 크기 지정
		bgimage.setIcon(icon5);						//라벨에 이미지 아이콘 등록
		add(bgimage);								//라벨을 frame에 추가
		
		setVisible(true);							//frame을 보이게 설정
		
		Timer t = new Timer(stopt, startbtn, timela);	
		//Timer Class에 boolean(stopt),JButton(startbtn),JLabel(timela)을 매개변수로 하는 값을 받아온다. 
		
		t.run();	//Timer Class의 run method동작을 통하여 timer를 구동
	}

	@Override
	public void run()
	{ // 스레드가 무한 루프될 부분

		output();	//현 클래스의 output method를 실행하여 랭크를 표시해 준다.
		try { // 예외옵션 설정으로 에러 방지
			while (stopt != false) //stopt가 true일때 무한반복 하게 되며 Thread로 실행된다.
			{
				Thread.sleep(20); // 20 millisec 로 스레드 돌리기
			}

			while (gameover != true && stopt == false) 
			{ // while 문으로 무한 루프 시키기
				
				Thread.sleep(20); 						// 20 millisec 로 스레드 돌리기
				KeyProcess(); 							// 키보드 입력처리를 하여 x,y 갱신
				cha1.setLocation(x, y); 				// 갱신된 x,y값으로 이미지 새로 그리기
				cha.setLocation(x-21,y-27); 			// 갱신된 x,y값으로 이미지 새로 그리기
				bosshpbar.setSize(bosshp / 2, 40);		// boss의 체력에 대한 사이즈를 bosshp라는 변수를 통해 크기가 조절
				expi.setBounds(757,513,exp*6+1,20);		// 경험치바가 exp변수를 통해 크기 조절
				ulife.setBounds(x - 23, y + 45, life+((24*life)/30), 5);	//user의 체력을 life변수를 통해 크기조절(ingame부분)
				playerhp.setSize(life * 6 +((7*life)/30), 20);				//user의 체력을 life변수를 통해 크기조절(border부분)
				bossmove();
				
				if (pattontime == 300) //pattiontime변수를 통해 boss의 공격에 대한 데미지에 대한 차이를 준다
				{
					bossatk1();			//bossatk1 method를 실행
					pattontime = 0;		//초기값으로 지정
				}
				
				uakt();					//uakt method를 실행(user공격에 대한 method)
				
				if (life <= 0) 
					gameover = true;	//user의 life변수가 0보다 작을 때 gameover변수를 true로 만들어 thread 무한루프에 대한 제어 실행
				
				if (bosshp <= 0) 		//boss의 bosshp가 0보다 작을 때 gameover변수를 true로 만들고
				{						//user가 이긴 것이기 때문에 input method를 실행하여 랭크에 등록 할 수 있도록 제어문 사용
					gameover = true;
					input();
				}
				
				if (pattontime2 >= ((bosshp / 100) + 2) * 10) 
				{						//다음과 같은 조건문의 조건이 참이 되었을 시 bossatk2 method를 실행하고
					pattontime2 = 0;	//exp변수를 증가
					bossatk2();
					exp++;
					if (k2 == 6) 
						k2 = 0;
					else 
						k2++;
				}
				
				if (level == 1 && exp >= 30) //다음 조건문이 참이 될 시에 level변수를 2로 변경
					level = 2;
				
				if (level ==2)				//level변수가 2로변경 되었을 시에 user의 이미지 아이콘변경을 통해 
					cha.setIcon(icon15);	//level up이 되었다는 것을 눈으로 확인

				pattontime++;				//thread의 무한 루프를 하면서 다음 2개의 변수 증가를 통하여
				pattontime2++;				//경험치와 boss 공격에 대한 패턴 변경

			}
			if (gameover == true) 			//gameover 변수가 true가 되었을 시 gameover1 라벨을 보이게 한다.
				gameover1.setVisible(true);
			
		}
		catch (Exception e) {}
	}

	public void keyPressed(KeyEvent e) 
	{
		// 키보드가 눌러졌을때 이벤트 처리하는 곳
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_UP:
				KeyUp = true;
				break;
			case KeyEvent.VK_DOWN:
				KeyDown = true;
				break;
			case KeyEvent.VK_LEFT:
				KeyLeft = true;
				break;
			case KeyEvent.VK_RIGHT:
				KeyRight = true;
				break;
			case KeyEvent.VK_X:
				keyspece = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{// 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_UP:
				KeyUp = false;
				break;
			case KeyEvent.VK_DOWN:
				KeyDown = false;
				break;
			case KeyEvent.VK_LEFT:
				KeyLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				KeyRight = false;
				break;
			case KeyEvent.VK_X:
				keyspece = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {// 키보드가 타이핑 될때 이벤트 처리하는 곳
	}

	public void KeyProcess() 
	{// 실제로 캐릭터 움직임 실현을 위해
		if (KeyUp == true) // 위에서 받아들인 키값을 바탕으로
			y -= 3;
		if (KeyDown == true) // 키 입력시마다 해당방향으로 5만큼의 이동을 시킨다.
			y += 3;
		if (KeyLeft == true)
			x -= 3;
		if (KeyRight == true)
			x += 3;
	}

	public void bossatk1() 
	{						
		bossattack1 bat1 = new bossattack1(x, bossx, atk1);	//bossattack1 class에 x변수와 bossx, atk1 변수를 통해 bat1 객체생성
		bat1.start();										//bossattack1 class의 thread 실행
	}

	public void bossatk2() 
	{
		bossattack2 bat2 = new bossattack2(atk2, x, y, k2, check); //bossattack2를 매개변수 atk2와 x,y,k2,check 변수를 통해 bat2 객체생성
		bat2.start();												//bossattack2의 thread 실행
	}

	public void uakt() 					//user 미사일에 대한 연속발사를 위한 method
	{
		if (keyspece == true)			//space가 눌릴시 predelay변수가 true로 변경
			predelay = true;
		
		if (predelay == true && keyspece == true) 	//keyspace와 predelay가 true일시 delayt변수가 1증가
			delayt++;
		
		if (delayt >= 5) 							//space가 5번이상 눌릴시 atkdelay가 true로 변경되며
		{											//delayt값이 초기화 되며 short1 class가 a값과 uatk1 매개변수를 통해 객체 생성
			atkdelay = true;
			delayt = 0;
			Short1 st1 = new Short1(a, uatk1);
			st1.start();							//short1 class의 thread 실행
		} 
	}

	public static boolean Crash(int cx1, int cy1, int cx2, int cy2, int cw1, int ch1, int cw2, int ch2, int type) 
	{			//충돌에 대한 method. cx1,cy1,cx2,cy2,cw1,ch1,cw2,ch2,type변수를 매개변수로 가짐
		UI2.type = type;	//매개변수로 들어오는 type 값에 따라서 조건문을 수행하도록 type값을 받음
		if (Math.abs((cx1 + cw1 / 2) - (cx2 + cw2 / 2)) < (cw2 / 2 + cw1 / 2)
				&& Math.abs((cy1 + ch1 / 2) - (cy2 + ch2 / 2)) < (ch2 / 2 + ch1 / 2)) 
		//계산을 통하여 미사일과 캐릭터들의 x값과 y값의 크기에 조건식이 참이 될 때 조건문 실행
		{
			if (type == 0) //type 값에 0의 매개변수가 전달 되었으면 user의 체력이 1감소하며 check변수는 true로 변경
			{
				UI2.life--;// 위 값이 true면 check에 true를 전달합니다.
				UI2.check = true;
			} 
			else if (type == 2) //type 값에 2의 매개변수가 전달 되었으면 boss의 체력을 100감소 시키고 check3변수를 true로 변경
			{
				UI2.bosshp -= 100;
				UI2.check3 = true;
			} 
			else if (type == 3) //type 값에 3의 매개변수가 전달 되었으면 boss의 체력을 300 감소 시키고 check4변수를 true로 변경
			{
				UI2.bosshp -= 300;
				UI2.check4 = true;
			}
			else 				
			{	//나머지 type의 매개변수에 대해서는 레벨에 따라 boss의 체력을 감소시키고 check2의 배열의 값을 true로 변경
				for (int i = 10; i <= type; i++)
					if (type == i) 
					{
						UI2.bosshp -= ((3 * level)-1);
						UI2.check2[type - 10] = true;
					}
			}
		} // check의 값을 메소드에 리턴 시킵니다.
		return check;
	}

	public static void main(String[] args) 
	{
		new UI2("메인윈도우");
	}

	public void bossmove() 
	{		//boss의 움직임에 관한 method
		if (bossmt == 0) 
		{
			Random rnd = new Random();		//Random class의 객체 생성
			bossmr = (rnd.nextInt(11) - 5);	//bossmr변수에 -5<bossmr<6 의 랜덤 값 생성
			bossx += bossmr;				//bossx의 변수에 랜덤한 값을 더한다
			boss.setLocation(bossx, 100);	//boss의  x 좌표 위치를 bossx변수의 값에 맞는 위치로 이동 
			bossmt++;						//bossmt값을 증가
		}
		else if (bossmt == 20) 				//bossmt값이 20이 되면 초기화
			bossmt = 0;
		else 					//bossmt의 값이 0과 20이 아닐시에 다음 조건문 수행으로 boss의 x좌표 움직임
		{
			bossmt++;
			bossx += bossmr;
			boss.setLocation(bossx, 100);
		}
		if (bossx < 40)			//bossx의 x좌표가 40보다 작을시에 bossmt의 값을 1로 바꾸며 bossmr에 5값을 대입하여
		{						//bossx좌표가 frame밖으로 못나가게 하기 위한 제어문
			bossmt = 1;
			bossmr = 5;
		} 
		else if (bossx > 660)  	//bossx의 좌표가 660보다 커질시 bossmt의 값을 1로 바꾸며 bossmr에 -5값을 대입하여
		{						//bossx좌표가 ingame frame밖으로 못나가게 하기 위한 제어문
			bossmt = 1;
			bossmr = -5;
		}
	}

	public void input() //순위를 위한 파일에 플레이한 사용자와 그에따른 시간을 파일에 저장하기 위한 method
	{
		File f = new File("src\\image\\rank.txt");	//file class에 url을 매개변수로 하여 객체 생성
		username = JOptionPane.showInputDialog("이름을 입력하세요 ", "짝짝짝 축하합니다. 이름을 입력하세요");
		//username 변수에 JOptionpane.showInputDialog에 입력한 값을 담는다.
		try 
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			//2byte씩 읽을 수 있는 Reader계열을 사용하여 파일에 있는 내용을 읽어올 수 있게 한다.

			i = 0;	//각 줄의 내용을 배열에 담을 수 있는 변수
			while ((a2 = br.readLine()) != null) 	//while 반복문을 통하여 마지막 줄 까지 수행
			{
				rank[i] = a2;	//한줄당 rank라는 배열에 값을 저장
				i = i + 1;		//i변수 1씩 증가하여 배열에 각 각 담을 수 있게 함
			}

			br.close();				//열었던 파일을 닫게 한다.
			rank[6] = username;		//JOptionpane.showInputDialog에서 입력한 값을 그 뒤 배열에 담는다.
			rank[7] = String.valueOf(Timer.now);	//Timer class의 now라는 변수에 저장된 값을 배열의 7번 index에 담는다.

			//순위 계산을 위한 조건문 수행
			if (Integer.parseInt(rank[7]) <= Integer.parseInt(rank[5])) 
			{	//각 배열의 index값이 홀수 인 부분에 비교를 할 수 있는 double type의 변수가 string type으로 담겨있어
				//플레이한 시간이 짧을 수록(Timer class의 now의 값이 작을 수록 배열의 index번호가 앞으로 가게 한다.  
				imsi1 = rank[4];
				imsi2 = rank[5];
				rank[4] = rank[6];
				rank[5] = rank[7];
				rank[6] = imsi1;
				rank[7] = imsi2;
			}
			//프로그램의 성격상 위에서 아래로 flow하게 되므로 각 각의 순서를 비교하여 배열의 홀수에 담긴 값을 비교하여
			//내림차순 하게 된다.
			if (Integer.parseInt(rank[5]) <= Integer.parseInt(rank[3])) 
			{
				imsi1 = rank[2];
				imsi2 = rank[3];
				rank[2] = rank[4];
				rank[3] = rank[5];
				rank[4] = imsi1;
				rank[5] = imsi2;
			}
			if (Integer.parseInt(rank[3]) <= Integer.parseInt(rank[1])) 
			{
				imsi1 = rank[0];
				imsi2 = rank[1];
				rank[0] = rank[2];
				rank[1] = rank[3];
				rank[2] = imsi1;
				rank[3] = imsi2;
			}

			//타임에 관한 조건문을 수행하여 배열에 생성된 내용을 파일에 쓰기 위하여 2byte씩 쓸수 있는 Writer계열을 사용
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			
			for (int i = 0; i < 6; i++)	//순위를 3위까지만 표시하기 때문에 index 0~5번까지만 파일에 print한다.
				pw.println(rank[i]);
			pw.flush();					//쓴내용을 flush를 통해 밀어내게 한다.
			pw.close();					//열었던 파일을 닫게 한다.
			
			output();					//output method를 사용하여 바뀐 내용을 갱신하게 한다.
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void output() 	//순위 표시를 하기 위한 파일을 가져와 frame의 label에 값을 보여주도록 하기 위한 method
	{
		File f = new File("src\\image\\rank.txt");	//File class에 url매개변수를 통해 객체 생성

		try 
		{
			//파일안의 내용을 2byte씩 읽어올 수 있는 Reader계열 사용하여 파일안의 내용을 읽어옴
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			i = 0;	//배열의 index값을 위한 변수 초기화
			while ((a2 = br.readLine()) != null)	//파일의 마지막줄까지 한 줄씩 a2변수에 대입
			{
				if (i == 0 | i == 2 | i == 4) 		//i값이 0, 2, 4일때에는 플레이한 유저의 이름이므로 그냥 label에 표시
					ranks[i].setText(a2);
				else if (i == 1 | i == 3 | i == 5) 	//index값이 1,3,5일 때에는 플레이한 시간이 그냥 담기게 되므로 
				{									//계산을 통하여 millisecond를 분,초,0.1초 단위로 label에 표시
					long a3 = Integer.parseInt(a2);	//string 값을 int값으로 바꿔주며 long type으로 담아준다.
					long nowm = a3/60000;			//long type변수를 계산을 통하여 분,초 , 0.1초 단위로 계산
					long nows = a3%60000/1000;
					long nowms = a3%1000/100;
					//Timer class의 mymethod라는 method를 이용하여 앞에 0이 붙을껀지를 return해준다.
					String str1 = (Timer.myMethod(nowm)+":"+Timer.myMethod(nows)+"."+ nowms);
					ranks[i].setText(str1);
				}
				i = i + 1;
				if (i == 6) //순위는 3위까지만 표시하기 때문에 배열 index번호가 6번이 되면 while문을 break시킨다.
					break;
			}
			br.close();		//열었던 파일을 닫아준다.
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}