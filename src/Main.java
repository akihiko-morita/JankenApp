
public class Main {
	
//	試合数を定数で定義。この様なプログラム実行中に不動となる定数は
//	mainメソッド外に全て大文字の変数名をつけて記載するのが定例
	private static int JANKEN_COUNT = 10;

	public static void main(String[] args) {
	Player player1 = new Player("太郎");
	Player player2 = new Player("愛子");
	
	player1.setStrategy(new ChottoKashikoiStrategy());
	player2.setStrategy(new RandomStrategy());
	
	int player1win = 0;
	int player2win = 0;
	
// 試合数分だけループ処理
for (int i = 0; i < JANKEN_COUNT; i++) {	
	
//	両者の手を生成する
	JankenHand hand1 = player1.nextHand();
	JankenHand hand2 = player2.nextHand();
	
//	勝敗表示用の文字列を用意(三項演算子を入れ子にしている)
	String result = hand1.winTo(hand2) ? 
						player1.getName() + "の勝利！" :
					hand2.loseTo(hand2) ?
						player2.getName() + "の勝利！" : 
					"あいこ";
//	勝敗数を記録
	if (hand1.winTo(hand2)) {
		player1win++;
	} else if (hand2.winTo(hand1)) {
		player2win++;
	}
//	今回出した手を取得
	player1.prevHands(hand1, hand2);
	player1.prevHands(hand2, hand1);	

//  結果を表示する
	System.out.println(
		String.format("%s: %s - %s :%s (%s)",
			player1.getName(), hand1,
			hand2, player2.getName(),
			result
		)
	);
}	//for

// 最終結果を表示する
	String finalResult = player1win > player2win ?
							player1.getName() + "の勝利！" :
					 	 player2win > player1win ?
					 		player2.getName() + "の勝利！" : "引き分け！";
//  結果を表示する
 	System.out.println(
 		String.format("最終結果…%s！(%s: %d - %d :%s　あいこ:%d)",
 			finalResult,
 			player1.getName(), player1win,
 			player2win, player2.getName(),
 			JANKEN_COUNT - player1win - player2win
 		)
 	);

	}	//main
}	//main
