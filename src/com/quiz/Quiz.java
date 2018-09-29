package com.quiz;

/**
 * クイズのインターフェース定義
 *
 * @author t.yoshida
 */
public interface Quiz
{
	/**
	 * 問題文を返す。
	 *
	 * @return 問題文
	 */
	String getQuestion();

	/**
	 * 正解か不正解かを判定する。
	 *
	 * @param reply 回答
	 * @return true: 正解
	 */
	boolean isCorrect(String reply);

	/**
	 * 解答を返す。
	 *
	 * @return 解答
	 */
	String getAnswer();
}
