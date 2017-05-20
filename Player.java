package zhajinhua;

public class Player {


	
	
	/**
	 * @Card[] 存储玩家手里的牌
	 */
	private Card[] cards;
	
	public Card[] getCards() {
		return cards;
	}
	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	private String id;
	
	/**
	 * 空参构造
	 */
	public Player(){




	}
	
	/**
	 * 
	 * @param id
	 */
	public Player(String id){
		this.id = id;
	}
	
	public String toString(){
		return cards[0].getColor()+cards[0].getNumber()+cards[1].getColor()+cards[1].getNumber()+cards[2].getColor()+cards[2].getNumber();
	}
	
	
}
