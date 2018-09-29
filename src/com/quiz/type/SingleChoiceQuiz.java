package com.quiz.type;

import java.util.LinkedHashMap;
import java.util.Map;

import com.quiz.Quiz;

/**
 * 単一回答方式クイズ
 *
 * @author t.yoshida
 */
public class SingleChoiceQuiz implements Quiz
{
	// 問題文
	protected String _question;

	// 選択肢マップ
	protected Map<String, ChoiceItem> _mapChoiceItem;

	/**
	 * 単一回答方式クイズを生成する。
	 *
	 * @param question 問題文
	 * @param items 複数選択肢
	 */
	public SingleChoiceQuiz(String question, ChoiceItem... items)
	{
		_question = question;

		_mapChoiceItem = new LinkedHashMap<>();
		for(int i=0; i<items.length; i++)
		{
			String no = String.valueOf(i + 1);
			_mapChoiceItem.put(no, items[i]);
		}
	}

	@Override
	public String getQuestion()
	{
		String retCode = System.getProperty("line.separator");

		StringBuilder sb = new StringBuilder(50);
		sb.append(_question).append(" ※番号入力").append(retCode);

		for(String no : _mapChoiceItem.keySet())
		{
			ChoiceItem item = _mapChoiceItem.get(no);
			String choice = item.getChoice();
			sb.append("\t").append(no).append(". ").append(choice).append(retCode);
		}

		// 最後の改行コード除去
		sb.delete(sb.lastIndexOf(retCode), sb.length());

		return sb.toString();
	}

	@Override
	public boolean isCorrect(String reply)
	{
		ChoiceItem item = _mapChoiceItem.get(reply);
		if(item == null || !item.isCorrectChoice())
		{
			return false;
		}

		return true;
	}

	@Override
	public String getAnswer()
	{
		for(ChoiceItem item : _mapChoiceItem.values())
		{
			if(item.isCorrectChoice())
			{
				return item.getChoice();
			}
		}

		return "";
	}
}
