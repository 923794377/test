package zhajinhua;

/**
 * 扎金花的游戏规则
 * 
 * 根据级别比出谁的牌大, 谁的牌小
 * 
 * level == 1:	一般牌
 * level == 2:	对子
 * level == 3:	一般顺子
 * level == 4:	一般同花
 * level == 5:	同花顺
 * level == 6:	炸弹
 */
public class Rule {

	/**
	 * 手牌的等级
	 */
	private int level;

	/**
	 * 手牌中的最大一张牌
	 */
	private int max;
	private int min;
	private int mid;

	/**
	 * 空参构造
	 */
	public Rule() {

	}

	/**
	 * 获得手牌中的最大一张牌
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
	 * 获得手牌中的最小的一张牌
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
	 * 获得手牌中的第二大一张牌
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
	 * 判断手牌是不是炸弹
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
	 * 判断是不是同花
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
	 * 判断是不是顺子
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
	 * 判断是不是对子
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
	 * 判断是否是同花顺
	 */
	private boolean level5(Card card1, Card card2, Card card3){
		if(level3(card1, card2, card3)&&level4(card1, card2, card3)){
			return true;
		}
		return false;
	}

	/**
	 * 获得手牌的等级
	 * 
	 * @return
	 */
	public int getLevel(Card card1, Card card2, Card card3) {
		// 炸弹
		if (level6(card1, card2, card3)) {
			level = 6;
		}
		// 同花顺
		else if (level4(card1, card2, card3) && level3(card1, card2, card3)) {
			level = 5;
		}
		// 一般同花
		else if (level4(card1, card2, card3) && !level3(card1, card2, card3)) {
			level = 4;
		}
		// 顺子
		else if (!level4(card1, card2, card3) && level3(card1, card2, card3)) {
			level = 3;
		}
		// 对子
		else if (level2(card1, card2, card3)) {
			level = 2;
		}
		// 散牌
		else {
			level = 1;
		}
		return level;
	}

}
