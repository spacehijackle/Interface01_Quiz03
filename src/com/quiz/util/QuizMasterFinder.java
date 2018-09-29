package com.quiz.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.quiz.QuizMaster;

/**
 * {@link QuizMaster} 検索クラス
 *
 * @author t.yoshida
 */
public class QuizMasterFinder
{
	/**
	 * 対象フォルダにあるJARファイルから {@link QuizMaster} を
	 * 実装したクラスを１つ取得し、インスタンス化して返す。
	 *
	 * @param searchDir 対象フォルダ
	 * @return {@link QuizMaster} 実装クラスのインスタンス（存在しない場合、null）
	 */
	public static QuizMaster find(File searchDir)
	{
		if(!searchDir.exists())
		{
			throw new IllegalArgumentException("[" + searchDir.getName() + "] not found.");
		}

		QuizMaster quizMaster = null;

		// 対象フォルダにあるJARファイルの抜き出し
		File[] listJar = searchDir.listFiles
		(
			(file) -> { return (file.isFile() && file.getName().endsWith(".jar")); }
		);

		for(int i=0; i<listJar.length; i++)
		{
			/*
			 * 抜き出したJARファイルからクラスローダーの取得
			 */
			URLClassLoader classLoader = null;
			try
			{
				URL jarUrl = listJar[i].toURI().toURL();
				classLoader = URLClassLoader.newInstance(new URL[] { jarUrl });
			}
			catch(MalformedURLException ex) { }

			/*
			 * JARファイル内にあるファイルをひとつずつ抜き出す
			 */
			try
			(
				JarFile jarFile = new JarFile(listJar[i]);
			)
			{
				Enumeration<JarEntry> e = jarFile.entries();
				while(e.hasMoreElements())
				{
					JarEntry entry = e.nextElement();
					String entryName = entry.getName();
					if(!entryName.endsWith(".class"))
					{
						// クラスファイル以外は無視
						continue;
					}

					/*
					 * クラスローダーを利用してクラスのロード
					 */
					final Class<?> clazz;
					try
					{
						clazz = classLoader.loadClass
						(
							// パッケージの区切りが'/'で表されるため、'.'に変換
							entryName.substring(0, entryName.length() - 6).replace('/', '.')
						);
					}
					catch(ClassNotFoundException ex)
					{
						continue;
					}

					/*
					 * ロードしたクラスから QuizMaster を実装したクラスのみインスタンス化
					 */
					try
					{
						Class<? extends QuizMaster> targetClass = clazz.asSubclass(QuizMaster.class);
						Constructor<? extends QuizMaster> constructor = targetClass.getConstructor();
						quizMaster = constructor.newInstance();
						break;
					}
					catch(Exception ex)
					{
						continue;
					}
				}
			}
			catch(IOException ex) { }
		}

		return quizMaster;
	}
}
