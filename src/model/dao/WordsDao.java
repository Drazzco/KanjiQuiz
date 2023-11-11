package model.dao;

import java.util.List;

import model.entities.Words;

public interface WordsDao {

	void insert(Words obj);
	void update(Words obj);
	void deleteById(Integer id);
	Words findById(Integer id);
	List<Words> findByLevelAndLesson(Integer level, Integer lesson);
	List<Words> findAll();
}
