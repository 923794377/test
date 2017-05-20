package zhajinhua;

import java.util.Random;
import java.util.Scanner;

public class Game {

	/**
	 * ��ҵĳ���
	 */
	private int moneyP = 100;

	/**
	 * ���Եĳ���
	 */
	private int moneyC = 100;

	/**
	 * �����ϵĳ���
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
	 * ��ÿ����Ϸ��ҷ�������
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
	 * �ж���Ӯ
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
			System.out.print("��Ӯ��");
		}else{
			System.out.println("����Ӯ��");
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

		System.out.println("��Ҹ�������һ��������? \n 1--->��ս \n �����--->����");

		int flag = input.nextInt();
		if (flag != 1) {
			System.out.println("good-bye");
			System.exit(0);
		}

		System.out.println("***��ս��ʼ***");

		while (true) {

			while (true) {
				if (moneyC == 0) {
					System.out.println("�����Ѿ������, ����������.");
					flag = 0;
					break;
				}

				if (moneyP == 0) {
					System.out.println("��Ǯû��, ����ʲô��.");
					flag = 0;
					break;
				}

				System.out.println("********************");
				System.out.println("��ʼ����---------->");

				deal();

				System.out.println("�����Ϊ: " + cardP1 + ", " + cardP2 + ", " + cardP3);
				System.out.println("��ĳ���Ϊ: " + moneyP + ", \n���Գ���Ϊ: " + moneyC + ",\n�������Ϊ: " + moneyT);
				System.out.println("�����: 1--->��ׯ\n�����--->����");

				int flag2 = input.nextInt();
				if (flag2 == 1) {
					genP();
					System.out.println("��ѡ���ׯ");

					int r = random.nextInt(5) + 1;
					if (r == 1) {
						System.out.println("����Ҫ����");
						System.out.println("���Ե���Ϊ: " + cardC1 + ", " + cardC2 + ", " + cardC3);

						open();

						System.out.println("�����������");
						input.nextLine();
						input.nextLine();
						break;
					} else {
						System.out.println("����ѡ�����");
						genC();
					}
				} else {
					System.out.println("��ѡ����");
					open();
					System.out.println("���Ե���Ϊ: " + cardC1 + ", " + cardC2 + ", " + cardC3);

					System.out.println("�����������");
					input.nextLine();
					input.nextLine();
					break;
				}
			}
			input.close();
		}
	}

}
