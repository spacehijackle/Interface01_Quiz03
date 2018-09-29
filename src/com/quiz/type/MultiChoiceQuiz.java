package com.quiz.type;

/**
 * 複数回答方式クイズ
 *
 * @author t.yoshida
 */
public class MultiChoiceQuiz extends SingleChoiceQuiz
{
	// 正解項目数
	protected int _correctItemCount;

	/**
	 * 複数回答方式クイズを生成する。
	 *
	 * @param question 問題文
	 * @param items 複数選択肢
	 */
	public MultiChoiceQuiz(String question, ChoiceItem... items)
	{
		super(question, items);

		for(int i=0; i<items.length; i++)
		{
			// 正解項目数の算出
			if(items[i].isCorrectChoice())
			{
				_correctItemCount++;
			}
		}
	}

	@Override
	public String getQuestion()
	{
		String retCode = System.getProperty("line.separator");

		StringBuilder sb = new StringBuilder(50);
		sb.append(_question).append(" ※空白で区切って番号入力").append(retCode);

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
		// 回答が空白で区切られているので分割
		String[] splits = reply.split(" ");

		// 入力項目数と正解項目数の比較
		if(splits.length != _correctItemCount)
		{
			return false;
		}

		// 間違った選択肢を１つでも選択したら不正解
		for(String itemNo : splits)
		{
			if(!super.isCorrect(itemNo))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public String getAnswer()
	{
		/*
		 * 解答をカンマ区切りで返す
		 */
		StringBuilder sb = new StringBuilder(50);
		for(ChoiceItem item : _mapChoiceItem.values())
		{
			if(item.isCorrectChoice())
			{
				if(sb.length() != 0)
				{
					sb.append(", ");
				}
				sb.append(item.getChoice());
			}
		}

		return sb.toString();
	}

}
