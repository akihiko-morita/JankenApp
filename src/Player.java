import java.util.Random;

public class Player {

	private String name;
//	ランダムに手を出す為の乱数を生成する変数
	private Random random = new Random();
//  インスタンスが持つジャンケンの戦略を格納する変数
	private JankenStrategy strategy = new RandomStrategy();

//	コンストラクタの作成
	public Player (String name) {
		this.name = name;
		System.out.println(name + "が参加しました。");
	}
	
	public String getName() {
		return this.name;
	}
	
//	保有する戦略を取得
	public JankenStrategy getStrategy() {
		return this.strategy;
	}
//　戦略を設定する
	public void setStrategy(JankenStrategy strategy) {
		this.strategy = strategy;
	}
//	CKSの為に前の手を取得するメソッド
	public void prevHands(JankenHand myHand, JankenHand opponentHand) {
		strategy.prevHands(myHand, opponentHand);
	}
//　戦略ごとのnextHand()メソッドを呼び出す
	public JankenHand nextHand() {
		return strategy.nextHand();
	}
}
