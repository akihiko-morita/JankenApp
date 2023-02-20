import java.util.Random;

// じゃんけんの手を決める戦略インターフェースを用意
// インターフェースではそれを実装するクラスが持たないといけないメソッドを規定する
// このJankenStrategyクラスでは以下の「名前」と「引数」を持つメソッドを必ず持つ事となる
public interface JankenStrategy {
	
//	デフォルト実装メソッド(処理内容が決まっているメソッド)を用意する
	public default void prevHands (JankenHand myHand, JankenHand opponentHand) {}
	
	public JankenHand nextHand();
}

// インターフェースの実装はimplementsで行う
class RandomStrategy implements JankenStrategy {
	private Random random = new Random();
	
	public JankenHand nextHand() {
		int n = random.nextInt(3);
		return JankenHand.fromInt(n);
	}
}

class FixedHandStrategy implements JankenStrategy {
	private JankenHand hand;
	
//	コンストラクタの生成
	public FixedHandStrategy (JankenHand hand) {
		this.hand = hand;
	}
	
	public JankenHand nextHand() {
		return this.hand;
	}
}

class ChottoKashikoiStrategy implements JankenStrategy {
	private JankenHand prevMyHand;
	private JankenHand prevOpponentHand;
	
//	デフォルト実装のprevHands()を上書き
	@Override
	public void prevHands(JankenHand myHand, JankenHand opponentHand) {
		prevMyHand = myHand;
		prevOpponentHand = opponentHand;
	}
	
	public JankenHand nextHand() {
//		初回はランダムストラテジーを流用
		if (prevMyHand == null ||  prevOpponentHand == null) {
			return new RandomStrategy().nextHand();
		}
		
		if (prevMyHand.winTo(prevOpponentHand)) {
//			自分が勝った場合は相手が手を変えると読んでローテート
//			ordinal()＋1 で自分の手の序数を変えてローテート
			return JankenHand.fromInt(prevMyHand.ordinal() + 1);
		} else if (prevMyHand.loseTo(prevOpponentHand)) {
//			負けた場合は相手の手をパクる
			return prevOpponentHand;
		} else {
//			あいこの場合は同じ手を出す
			return prevMyHand;
		}
	}
}