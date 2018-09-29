package com.framework.quiz;

import java.io.File;
import java.util.Scanner;

import com.quiz.Quiz;
import com.quiz.QuizMaster;
import com.quiz.SampleQuizMaster;
import com.quiz.util.QuizMasterFinder;

/**
 * クイズフレームワーク
 *
 * @author t.yoshida
 */
public class QuizFramework
{
	/**
	 * クイズを出題し、正解/不正解の判定をする。
	 */
	public static void main(String[] args)
	{
		int correctCount = 0;    // 正解数
		int questionCount = 0;   // 問題数

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		// クイズ出題クラスの取得
		QuizMaster master = QuizMasterFinder.find(new File("./client"));
		if(master == null)
		{
			// デフォルト サンプルクイズを設定
			master = new SampleQuizMaster();
		}

		// クイズ出題のタイトル表示
		System.out.println(master.getTitle());

		// 改行コード取得
		String retCode = System.getProperty("line.separator");

		/*
		 * クイズ出題クラスからクイズを取り出し出題する。
		 * ユーザの回答から正解/不正解を判定し、最終的に正解率を表示する。
		 */
		Quiz quiz;
		while((quiz = master.poll()) != null)
		{
			questionCount++;

			System.out.print("Q. " + quiz.getQuestion() + retCode + " ⇒ ");
			String reply = scanner.nextLine();
			if(quiz.isCorrect(reply.trim()))
			{
				System.out.println("○ 正解！");
				correctCount++;
			}
			else
			{
				System.out.println("Ｘ 残念…  A. " + quiz.getAnswer());
			}
			System.out.println();
		}

		float ratio = correctCount / (float)questionCount;
		System.out.println("☘ 正解率: " + String.format("%.2f", ratio * 100) + "%");
	}
}
