import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import acm.util.MediaTools;
import acm.util.RandomGenerator;

public class FlappyBird extends GraphicsProgram {
	//	private RandomGenerator rgen = RandomGenerator.getInstance();
	public Color cool = new Color(240, 206, 44);
	public Color start = new Color(230, 230, 220);
	public Font flappy = new Font("04b_19", 0, 80);
	public Background background;
	public Background background2;
	public UpTube uptube;
	public UpTube uptube2;
	//	public UpTube uptube3;
	public DownTube downtube;
	public DownTube downtube2;
	//	public DownTube downtube3;
	public Bird bird;
	//	private GImage bird;
	public Ground ground;
	public Ground ground2;
	private boolean ison = false;
	//	private int space = 0;
	//	private boolean isFlapping = true;
	public static final int APPLICATION_WIDTH = 863;
	public static final int APPLICATION_HEIGHT = 772+48;
	GLabel scorelabel;
	GLabel startlabel;
	GLabel endlabel;
	AudioClip flap = MediaTools.loadAudioClip("flap.au");
	AudioClip crash = MediaTools.loadAudioClip("crash.au");
	AudioClip point = MediaTools.loadAudioClip("coin.au");
	int score = 0;
	private boolean ismoving = false;
	private boolean gameOver = false;
	private boolean hasScored = false;
	private boolean hasScored2 = false;
	public boolean started = false;
//	public boolean show = false;
	//	private boolean flapped = false;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	public void run() {
		setup();
		//		double bird_RADIUS = bird.getWidth()/2;
		//		ground.sendToFront();
		//		ground2.sendToFront();
		//		bird.sendToFront();
		background.sendToBack();
		uptube.sendToFront();
		uptube2.sendToFront();
		downtube.sendToFront();
		downtube2.sendToFront();
		ground.sendToFront();
		ground2.sendToFront();
		scorelabel.sendToFront();
		bird.sendToFront();
		while(gameOver == false){
//			System.out.println(uptube.getHeight());
			//			int randomNumber = rgen.nextInt(10);
			//			randomNumber++;
			//			System.out.println(randomNumber);
			//			uptube3.move(-2.5, 0);
			//			downtube3.move(-2.5, 0);
			//			background.move(-2.5, 0);
			if(started == true){
				bird.move(0, bird.getYchange());
				bird.addGravity();
				//			if(bird.getY() >= APPLICATION_HEIGHT){
				//				bird.setLocation(200, 772/2);
				//			}
				movePipes();
			}
			if(ismoving == true){
				//				ground2.move(-2.5, 0);
			}
			ground2.move(-2.5, 0);
			if(ground.getX()+ground.getWidth() <= 0){
				ground.setLocation(APPLICATION_WIDTH, 772);
				//				ground.sendToFront();
				ismoving = true;
			}
			if(ground2.getX()+ground2.getWidth() <= 0){
				ground2.setLocation(APPLICATION_WIDTH, 772);
				//				ground2.sendToFront();
			}
			//			if(ground2.getX()+ground2.getWidth() <= APPLICATION_WIDTH){
			//				ground.setLocation(APPLICATION_WIDTH, 772);
			//			}
			//			System.out.println(ground.getX()+ground.getWidth()/*, ground2.getX()+ground2.getWidth()*/);
			ground.move(-2.5, 0);
			if(uptube.getX() <= 441-((199-52)/2) && ison == false){
				int randnum = rgen.nextInt(1, 10);
				randnum++;

				//				uptube2.sendToFront();
				int x = randnum*50;
				int y = 772 - x;
				uptube2.setLocation(878, y);
				downtube2.setLocation(878, y-171-1664);
				hasScored2=false;
				ison=true;
				//				if(randnum == 1){
				//					uptube2.setLocation(878, 772-321);
				//					//				downtube2.sendToFront();
				//					downtube2.setLocation(878, -1355);
				//					hasScored2=false;
				//					//				bird.sendToFront();
				//					ison = true;
				//				}else if(randnum == 2){
				//					
				//				}else if(randnum == 10){
				//					uptube2.setLocation(878, 772 - 21);
				//					downtube2.setLocation(878, -1055);
				//					hasScored2 = false;
				//					ison = true;
				//				}
			}
			if(uptube.getX() <= -(199-52)){
				int randnum = rgen.nextInt(1, 10);
				randnum++;

				//				uptube2.sendToFront();
				int x = randnum*50;
				int y = 772 - x;
				uptube.setLocation(878, y);
				downtube.setLocation(878, y-171-1664);
				hasScored=false;
				ison=false;
			}
			//			if(isFlapping == false){
			//				bird.move(0, -5);
			//			}
			//			if(bird.getY() <= 287){
			//				bird.setLocation(200, 772/2);
			//			}
			//			if(/*bird.getY() <= 0 - (196-148) || */bird.getY() >= APPLICATION_HEIGHT - (48+bird.getHeight())){
			//				bird.setLocation(200, 200);
			//			}
			//			GObject collidingObject = getCollidingObject();//apple.awt.OSXImage@61e090ee, apple.awt.OSXImage@595780d9
			//			if(collidingObject != null){
			//				if(collidingObject == uptube || collidingObject == downtube || collidingObject == downtube2 || collidingObject == uptube2){
			//					crash.play();
			//					bird.setImage("downbird.png");
			//					gameOver = true;
			//				}
			//			}
			GObject collidingObject = getCollidingObject();
			//			if(collidingObject!=null){
			if(collidingObject == uptube || collidingObject == downtube || collidingObject == downtube2 || collidingObject == uptube2){
				System.out.println("crash!");
				crash.play();
				bird.setImage("downbird.png");
				gameOver = true;
				hasScored = false;
			}
			if(bird.getY() >= APPLICATION_HEIGHT-ground.getHeight()){
				crash.play();
				gameOver = true;
			}
			//			}
			//			else{
			//
			//			}
			else if((uptube.getX()+uptube.getWidth()/2 <= bird.getX()+bird.getWidth()) && hasScored == false){

				score++;
				scorelabel.setLabel(""+score);
				point.play();
				hasScored = true;
			}

			else if((uptube2.getX()+uptube2.getWidth()/2 <= bird.getX()+bird.getWidth()) && hasScored2 == false){

				score++;
				scorelabel.setLabel(""+score);
				point.play();
				hasScored2 = true;
			}

			pause(7.5);
		}
		//			}

		gamover();
	}

	//			if(uptube2.getX() <= bird.getX()+bird.getWidth() && hasScored2 == false){
	//				if(collidingObject != null){
	//					if(collidingObject == uptube || collidingObject == downtube || collidingObject == downtube2 || collidingObject == uptube2){
	//						crash.play();
	//						bird.setImage("downbird.png");
	//						gameOver = true;
	//					}
	//				}else{
	//					score++;
	//					scorelabel.setLabel(""+score);
	////					hasScored2 = true;
	//				}
	//				//			System.out.println(bird.getImage());
	//				//			if(bird == bird){
	//				//				System.out.println("hello");
	//				//			}
	//			ground.sendToFront();
	//			ground2.sendToFront();



	private void movePipes(){
		uptube.move(-2.5, 0);
		downtube.move(-2.5, 0);
		uptube2.move(-2.5, 0);
		downtube2.move(-2.5, 0);
	}
	private void setup() {
		addKeyListeners();
		background = new Background();
		// HEHE :D
		//		add(background); HI THIS IS AJ!!!!!!!!!!!!!!!!!!!!!!
		background2 = new Background();
		add(background2);
		add(background);
		uptube = new UpTube();
		add(uptube);
		downtube = new DownTube();
		add(downtube);
		uptube2 = new UpTube();
		add(uptube2);
		//		uptube3 = new UpTube();
		//		add(uptube3);
		downtube2 = new DownTube();
		add(downtube2);
		//		downtube3 = new DownTube();
		//		add(downtube3);
		uptube2.sendToBack();
		downtube2.sendToBack();
		//		uptube3.sendToBack();
		//		downtube3.sendToBack();
		bird = new Bird();
		add(bird);
		ground = new Ground();
		add(ground);
		ground2 = new Ground();
		add(ground2);
		ground2.sendToBack();
		ground2.setLocation(863, 772);
		scorelabel = new GLabel(""+score, 380, 350);
		scorelabel.setLabel(""+score);
		scorelabel.setFont(flappy);
		scorelabel.setColor(cool);
		scorelabel.sendToFront();
		add(scorelabel);
		startlabel = new GLabel("PRESS THE SPACEBAR", 50, 175);
//		startlabel.setLabel("PRESS THE SPACEBAR" +" TO START");
		startlabel.setFont(flappy);
		startlabel.setColor(start);
		startlabel.sendToFront();
		endlabel = new GLabel("TO START", 225, 275);
		endlabel.setFont(flappy);
		endlabel.setColor(start);
		endlabel.sendToFront();
		add(startlabel);
		add(endlabel);
	}
	public GObject getCollidingObject() {
		double bird_wid = bird.getWidth();
		double bird_hei = bird.getHeight();
		if( getElementAt(bird.getX(),bird.getY() ) != null){
			return getElementAt(bird.getX()+bird_wid,bird.getY());
		}

		else if( getElementAt(bird.getX(),bird.getY()+bird_hei) != null){
			return getElementAt(bird.getX(),bird.getY()+bird_hei);
		}

		else if( getElementAt(bird.getX()+bird_wid,bird.getY()) != null){
			return getElementAt(bird.getX()+bird_wid,bird.getY());
		}

		else if( getElementAt(bird.getX()+(bird_wid),bird.getY()+bird_hei) != null){
			return getElementAt(bird.getX()+(bird_wid),bird.getY()+bird_hei);

		}else{
			return null;
		}
	}
	public void gamover(){
		while(bird.getY() <= APPLICATION_HEIGHT - (48+bird.getHeight())){
			bird.move(0, 5);
			pause(7.5);
		}
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			//			double birdx = bird.getX();
			//			double birdy = bird.getY();
			if(started == true){
				if(bird.getY() >= -bird.getHeight()){
					bird.setYchange(-4);
					flap.play();
					//				pause(100);
				}
			}else{
				startlabel.sendToBack();
				endlabel.sendToBack();
				started = true;
//				show = true;
			}
			//			else{

			//			}
			//			System.out.println("played");
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			double birdx = bird.getX();
			double birdy = bird.getY();
			bird.setLocation(birdx, birdy - 5);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			double birdx = bird.getX();
			double birdy = bird.getY();
			bird.setLocation(birdx + 15, birdy);
			if (bird.getX() >= 675) {
				//				score++;
				//				scorelabel.setLabel(""+score);
				//				add(scorelabel);
				////				bird.setLocation(160, 244);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			double birdx = bird.getX();
			double birdy = bird.getY();
			bird.setLocation(birdx - 5, birdy);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			double birdx = bird.getX();
			double birdy = bird.getY();
			bird.setLocation(birdx, birdy + 5);
		}

	}
}
