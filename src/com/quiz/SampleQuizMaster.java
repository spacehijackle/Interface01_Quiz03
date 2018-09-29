package com.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.quiz.type.ChoiceItem;
import com.quiz.type.FreeAnswerQuiz;
import com.quiz.type.MultiChoiceQuiz;
import com.quiz.type.SingleChoiceQuiz;

/**
 * {@link QuizMaster} のサンプル実装
 *
 * @author t.yoshida
 */
public class SampleQuizMaster implements QuizMaster
{
	// クイズリスト
	private List<Quiz> _listQuiz;

	/**
	 * SampleQuizMaster を生成する。
	 */
	public SampleQuizMaster()
	{
		_listQuiz = new ArrayList<>();

		// クイズの作成
		buildQuizList();

		// クイズのシャッフル
		Collections.shuffle(_listQuiz);
	}

	@Override
	public String getTitle()
	{
		String retCode = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder(100);
		sb.append("──────────────────────────────").append(retCode);
		sb.append("                     ♠ Sample Quiz ♥                      ").append(retCode);
		sb.append("──────────────────────────────").append(retCode);

		return sb.toString();
	}

	@Override
	public Quiz poll()
	{
		if(_listQuiz.size() == 0)
		{
			return null;
		}

		Quiz quiz = _listQuiz.remove(0);
		return quiz;
	}

	/**
	 * クイズを作成する。
	 */
	private void buildQuizList()
	{
		_listQuiz.add
		(
			new FreeAnswerQuiz
			(
				"切っても切っても切れないものはな～んだ？", "硬いもの"
			)
		);
		_listQuiz.add
		(
			new FreeAnswerQuiz
			(
				"揉めば揉むほど取れるものはな～んだ？", "マッサージ師の資格"
			)
		);
		_listQuiz.add
		(
			new FreeAnswerQuiz
			(
				"チチはチチでも過激なチチと言えば？", "ウルトラの父"
			)
			{
				@Override
				public String getAnswer()
				{
					return super.getAnswer() + "（ウルトラ＝過激な）";
				}
			}
		);
		_listQuiz.add
		(
			new MultiChoiceQuiz
			(
				"日本三大ヒデキと言えば？",
				new ChoiceItem("湯川秀樹", true),
				new ChoiceItem("松井秀喜", true),
				new ChoiceItem("ドーハの悲劇", false),
				new ChoiceItem("西城秀樹", true),
				new ChoiceItem("お尻のオデキ", false)
			)
		);
		_listQuiz.add
		(
			new MultiChoiceQuiz
			(
				"山形が産んだ三大スーパースターと言えば？",
				new ChoiceItem("さくらんぼ", false),
				new ChoiceItem("ビートきよし", false),
				new ChoiceItem("ウド鈴木", true),
				new ChoiceItem("あき竹城", true),
				new ChoiceItem("米沢牛", true)
			)
		);
		_listQuiz.add
		(
			new SingleChoiceQuiz
			(
				"世界三大美女に該当する人物をひとり選べ",
				new ChoiceItem("おのののか", false),
				new ChoiceItem("クレオパトラ", true),
				new ChoiceItem("余貴美子", false)
			)
		);
		_listQuiz.add
		(
			new SingleChoiceQuiz
			(
				"一番面白いボクサーは？",
				new ChoiceItem("渡嘉敷勝男", false),
				new ChoiceItem("具志堅用高", false),
				new ChoiceItem("ガッツ石松", false),
				new ChoiceItem("ロッキー・バルボア", true),
				new ChoiceItem("ミッキー・ローク", false)
			)
		);
		_listQuiz.add
		(
			new FreeAnswerQuiz("【ボーナスクイズ！】あなたは誰ですか？", "")
			{
				// 回答
				String reply;

				/** {@inheritDoc} <p>どんな回答でも正解とする。</p>*/
				@Override
				public boolean isCorrect(String reply)
				{
					this.reply = reply; return true;
				}

				/** {@inheritDoc} <p>回答を解答としてそのまま返す。</p>*/
				@Override
				public String getAnswer() { return reply; }
			}
		);
	}
}
