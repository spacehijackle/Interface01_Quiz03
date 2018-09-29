package com.quiz.type;

import com.quiz.Quiz;

/**
 * 自由回答方式クイズ
 *
 * @author t.yoshida
 */
public class FreeAnswerQuiz implements Quiz
{
	// 問題文
	private String _question;

	// 解答
	private String _answer;

	/**
	 * 自由回答方式クイズを生成する。
	 *
	 * @param question 問題文
	 * @param answer 解答
	 */
	public FreeAnswerQuiz(String question, String answer)
	{
		_question = question;
		_answer = answer;
	}

	@Override
	public String getQuestion()
	{
		return _question;
	}

	@Override
	public boolean isCorrect(String reply)
	{
		return _answer.equals(reply);
	}

	@Override
	public String getAnswer()
	{
		return _answer;
	}
}
