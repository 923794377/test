package zhajinhua;

import java.util.Random;
import java.util.Scanner;

public class Game {

	/**
	 * 玩家的筹码
	 */
	private int moneyP = 100;

	/**
	 * 电脑的筹码
	 */
	private int moneyC = 100;

	/**
	 * 桌面上的筹码
	 */
	private int moneyT = 0;

	private Cards cards = Cards.getInstance();

	private Card cardP1 = null;
	private Card cardP2 = null;
	private Card cardP3 = null;
	private Card cardC1 = null;
	private Card cardC2 = null;
	private Card cardC3 = null;

	/**
	 * 给每个游戏玩家发三张牌
	 */
	public void deal() {

		cardP1 = cards.distuibeCard();
		cardP2 = cards.distuibeCard();
		cardP3 = cards.distuibeCard();
		cardC1 = cards.distuibeCard();
		cardC2 = cards.distuibeCard();
		cardC3 = cards.distuibeCard();

		moneyP -= 5;
		moneyC -= 5;
		moneyT += 10;

	}

	/**
	 * 判断输赢
	 * 
	 * @return
	 */
	private boolean winLos() {

		Rule rule = new Rule();

		if (rule.getLevel(cardP1, cardP2, cardP3) > rule.getLevel(cardC1, cardC2, cardC3)) {
			return true;
		}
		

		if (rule.getLevel(cardP1, cardP2, cardP3) == rule.getLevel(cardC1, cardC2, cardC3)
				&& rule.getMax(cardP1, cardP2, cardP3) > rule.getMax(cardC1, cardC2, cardC3)) {
			return true;
		}
		
		if (rule.getLevel(cardP1, cardP2, cardP3) == rule.getLevel(cardC1, cardC2, cardC3)
				&& rule.getMax(cardP1, cardP2, cardP3) ==rule.getMax(cardC1, cardC2, cardC3)
				&&rule.getMid(cardP1, cardP2, cardP3) > rule.getMid(cardC1, cardC2, cardC3)
				) {
			return true;
		}
		
		
		if (rule.getLevel(cardP1, cardP2, cardP3) == rule.getLevel(cardC1, cardC2, cardC3)
				&& rule.getMax(cardP1, cardP2, cardP3) ==rule.getMax(cardC1, cardC2, cardC3)
				&& rule.getMid(cardP1, cardP2, cardP3) == rule.getMid(cardC1, cardC2, cardC3)
				&& rule.getMin(cardP1, cardP2, cardP3) > rule.getMin(cardC1, cardC2, cardC3)) {
			return true;
		}	
		
		return false;		
		
	}
	public void CP(){
		
		if(winLos()==true){
			System.out.print("你赢了");
		}else{
			System.out.println("电脑赢了");
		}
	}
	

	private void genP() {
		moneyP -= 5;
		moneyT += 5;
	}

	private void genC() {
		moneyC -= 5;
		moneyT += 5;
	}

	public void open() {
		if (winLos()) {
			moneyP = moneyP + moneyT;
			moneyT = 0;
		} else {
			moneyC = moneyC + moneyT;
			moneyT = 0;
		}
	}

	public void start() {
		Scanner input = new Scanner(System.in);
		Random random = new Random();

		System.out.println("你敢跟电脑来一局扎金花吗? \n 1--->挑战 \n 任意键--->不敢");

		int flag = input.nextInt();
		if (flag != 1) {
			System.out.println("good-bye");
			System.exit(0);
		}

		System.out.println("***对战开始***");

		while (true) {

			while (true) {
				if (moneyC == 0) {
					System.out.println("电脑已经输光了, 不给你玩了.");
					flag = 0;
					break;
				}

				if (moneyP == 0) {
					System.out.println("分钱没得, 你拿什么赌.");
					flag = 0;
					break;
				}

				System.out.println("********************");
				System.out.println("开始发牌---------->");

				deal();

				System.out.println("你的牌为: " + cardP1 + ", " + cardP2 + ", " + cardP3);
				System.out.println("你的筹码为: " + moneyP + ", \n电脑筹码为: " + moneyC + ",\n桌面筹码为: " + moneyT);
				System.out.println("你可以: 1--->跟庄\n任意键--->开牌");

				int flag2 = input.nextInt();
				if (flag2 == 1) {
					genP();
					System.out.println("你选择跟庄");

					int r = random.nextInt(5) + 1;
					if (r == 1) {
						System.out.println("电脑要开牌");
						System.out.println("电脑的牌为: " + cardC1 + ", " + cardC2 + ", " + cardC3);

						open();

						System.out.println("按任意键继续");
						input.nextLine();
						input.nextLine();
						break;
					} else {
						System.out.println("电脑选择跟牌");
						genC();
					}
				} else {
					System.out.println("你选择开牌");
					open();
					System.out.println("电脑的牌为: " + cardC1 + ", " + cardC2 + ", " + cardC3);

					System.out.println("按任意键继续");
					input.nextLine();
					input.nextLine();
					break;
				}
			}
			input.close();
		}
	}

}
