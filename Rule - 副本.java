package zhajinhua;

/**
 * ���𻨵���Ϸ����
 * 
 * ���ݼ���ȳ�˭���ƴ�, ˭����С
 * 
 * level == 1:	һ����
 * level == 2:	����
 * level == 3:	һ��˳��
 * level == 4:	һ��ͬ��
 * level == 5:	ͬ��˳
 * level == 6:	ը��
 */
public class Rule {

	/**
	 * ���Ƶĵȼ�
	 */
	private int level;

	/**
	 * �����е����һ����
	 */
	private int max;
	private int min;
	private int mid;

	/**
	 * �ղι���
	 */
	public Rule() {

	}

	/**
	 * ��������е����һ����
	 * 
	 * @return
	 */
	public int getMax(Card card1, Card card2, Card card3) {

		int a = Integer.parseInt(card1.getNumber());
		int b = Integer.parseInt(card2.getNumber());
		int c = Integer.parseInt(card3.getNumber());

		if (a > b) {
			max = a;
		} else {
			max = b;
		}

		if (max < c) {
			max = c;
		}

		return max;
	}
	/**
	 * ��������е���С��һ����
	 * 
	 * @return
	 */
	public int getMin(Card card1, Card card2, Card card3) {

		int a = Integer.parseInt(card1.getNumber());
		int b = Integer.parseInt(card2.getNumber());
		int c = Integer.parseInt(card3.getNumber());

		if (a > b) {
			min = b;
		} else {
			min = a;
		}

		if (min>c) {
			min = c;
		}

		return min;
	}
	
	/**
	 * ��������еĵڶ���һ����
	 * 
	 * @return
	 */
	public int getMid(Card card1, Card card2, Card card3){

		int a = Integer.parseInt(card1.getNumber());
		int b = Integer.parseInt(card2.getNumber());
		int c = Integer.parseInt(card3.getNumber());

		if (a > b&&b>c) {
			mid= b;
		} 
		if(c>b&&b>a){
			mid=b;
		}
		if(b>a&&a>c){
			mid=a;
		}
		if(c>a&&a>b){
			mid=a;
		}
		if(a>c&&c>b){
			mid=c;
		}
		if(b>c&&c>a){
			mid=c;
		}
		return mid;
	}

	/**
	 * �ж������ǲ���ը��
	 * 
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	private boolean level6(Card card1, Card card2, Card card3) {

		if (card1.getNumber().equals(card2.getNumber()) && card2.getNumber().equals(card3.getNumber())) {
			return true;
		}

		return false;
	}

	/**
	 * �ж��ǲ���ͬ��
	 * 
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	private boolean level4(Card card1, Card card2, Card card3) {

		if (card1.getColor().equals(card2.getColor()) && card2.getColor().equals(card3.getColor())) {
			return true;
		}

		return false;
	}

	/**
	 * �ж��ǲ���˳��
	 * 
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	private boolean level3(Card card1, Card card2, Card card3) {

		int max = getMax(card1, card2, card3);

		int a = Integer.parseInt(card1.getNumber());
		int b = Integer.parseInt(card2.getNumber());
		int c = Integer.parseInt(card3.getNumber());

		if (a == max) {

			if (a - b == 1 && a - c == 2) {
				return true;
			}

			if (a - c == 1 && a - b == 2) {
				return true;
			}
		}

		if ((b == max) && (b - a == 1 && b - c == 2) || (b - c == 1 && b - a == 2)) {
			return true;
		}

		if ((c == max) && (c - a == 1 && c - b == 2) || (c - b == 1 && c - a == 2)) {
			return true;
		}

		return false;
	}

	/**
	 * �ж��ǲ��Ƕ���
	 * 
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return
	 */
	private boolean level2(Card card1, Card card2, Card card3) {

		if (!level6(card1, card2, card3)) {
			if (card1.getNumber().equals(card2.getNumber())) {
				return true;
			}
			if (card2.getNumber().equals(card3.getNumber())) {
				return true;
			}
			if (card1.getNumber().equals(card3.getNumber())) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * �ж��Ƿ���ͬ��˳
	 */
	private boolean level5(Card card1, Card card2, Card card3){
		if(level3(card1, card2, card3)&&level4(card1, card2, card3)){
			return true;
		}
		return false;
	}

	/**
	 * ������Ƶĵȼ�
	 * 
	 * @return
	 */
	public int getLevel(Card card1, Card card2, Card card3) {
		// ը��
		if (level6(card1, card2, card3)) {
			level = 6;
		}
		// ͬ��˳
		else if (level4(card1, card2, card3) && level3(card1, card2, card3)) {
			level = 5;
		}
		// һ��ͬ��
		else if (level4(card1, card2, card3) && !level3(card1, card2, card3)) {
			level = 4;
		}
		// ˳��
		else if (!level4(card1, card2, card3) && level3(card1, card2, card3)) {
			level = 3;
		}
		// ����
		else if (level2(card1, card2, card3)) {
			level = 2;
		}
		// ɢ��
		else {
			level = 1;
		}
		return level;
	}

}
