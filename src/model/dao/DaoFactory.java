package model.dao;

import db.DB;
import model.dao.impl.WordsDaoJDBC;

public class DaoFactory {
	
	public static WordsDao createWordsDao()
	{
		return new WordsDaoJDBC(DB.getConnection());
	}
}
