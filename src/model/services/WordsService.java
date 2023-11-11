package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.WordsDao;
import model.entities.Words;

public class WordsService {

	private WordsDao dao = DaoFactory.createWordsDao();
	
	public List<Words> findAll()
	{
		return dao.findAll();
	}
	
	public List<Words> findByLevelAndLesson(Integer level, Integer lesson)
	{
		return dao.findByLevelAndLesson(level, lesson);
	}
	
	public void saveOrUpdate(Words obj)
	{
		if(obj.getId() == null)
		{
			dao.insert(obj);
		}
		else
		{
			dao.update(obj);
		}
	}
	
	public void remove(Words obj)
	{
		dao.deleteById(obj.getId());
	}
}
