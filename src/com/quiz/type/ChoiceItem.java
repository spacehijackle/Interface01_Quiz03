package com.quiz.type;

/**
 * 選択肢クラス
 * <p>
 * 回答方式が選択肢を持つクイズで利用する。
 * </p>
 *
 * @author t.yoshida
 */
public class ChoiceItem
{
	// 選択肢文字列
	private String _choice;

	// 正解フラグ
	private boolean _isCorrect;

	/**
	 * 選択肢を生成する。
	 *
	 * @param choice 選択肢文字列
	 * @param isCorrect 正解の選択肢か否かのフラグ（true: 正解の選択肢）
	 */
	public ChoiceItem(String choice, boolean isCorrect)
	{
		_choice = choice;
		_isCorrect = isCorrect;
	}

	/**
	 * 選択肢文字列を返す。
	 *
	 * @return 選択肢文字列
	 */
	public String getChoice()
	{
		return _choice;
	}

	/**
	 * 正解の選択肢か否かを返す。
	 *
	 * @return true: 正解の選択肢
	 */
	public boolean isCorrectChoice()
	{
		return _isCorrect;
	}
}
