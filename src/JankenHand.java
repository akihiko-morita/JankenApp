
//列挙型クラスとして定義する時はenum
public enum JankenHand {
	Rock,
	Scissors,
	Paper;
	
	
	@Override
	public String toString() {
		switch (this) {
		case Rock: return "✊";
		case Scissors: return "✌";
		case Paper: return "✋";
		}
		//switchには網羅性が無いため、抜ける事はありえないが、抜けた場合の返り値も指定しなければならない
		//こういう場合はlOlegalStateExceptionを投げるのが常套手段
		throw new IllegalStateException();
	}
	
//	ランダムな数値に対して手を返すメソッド staticメソッドなので.fromIntで呼び出せる
	public static JankenHand fromInt (int n) {
		switch (n % 3) {
		case 0: return Rock;
		case 1: return Scissors;
		case 2: return Paper;
		}
		throw new IllegalArgumentException(Integer.toString(n));
	}
	
//	自分の手に対して相手に勝てるかどうかを判定するメソッド
//  JankenHand.Rock.winTo(JankenHand Scissors)という風に呼び出す為、staticは定義しない
//　this = 自分の手　hand = 相手の手　である
	public boolean winTo(JankenHand hand) {
		switch (this) {
		case Rock: return hand == Scissors;
		case Scissors: return hand == Paper;
		case Paper: return hand == Rock;
		}
		throw new IllegalStateException();
	}
	
//	負けを判定するメソッド　あいこではない＆勝てない＝負けで判定する
	public boolean loseTo(JankenHand hand) {
		return this != hand && !winTo(hand);
	}
}
