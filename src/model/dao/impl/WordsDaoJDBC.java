package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.WordsDao;
import model.entities.Words;

public class WordsDaoJDBC implements WordsDao {
	
	private Connection conn;

	public WordsDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Words obj) {
		PreparedStatement st = null;
		try
		{
			st = conn.prepareStatement(
					"INSERT INTO words "
					+ "(Level,Lesson,HWordQuestion,HWord1,HWord2,HWord3,HWord4,HWordAnswer,KWordQuestion,KWord1,KWord2,"
					+ "KWord3,KWord4,KWordAnswer,EnglishTrad,PortugueseTrad) "
					+ "VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getLevel());
			st.setInt(2, obj.getLesson());
			st.setString(3, obj.gethWordQuestion());
			st.setString(4, obj.gethWord1());
			st.setString(5, obj.gethWord2());
			st.setString(6, obj.gethWord3());
			st.setString(7, obj.gethWord4());
			st.setInt(8, obj.gethWordAnswer());
			st.setString(9, obj.getkWordQuestion());
			st.setString(10, obj.getkWord1());
			st.setString(11, obj.getkWord2());
			st.setString(12, obj.getkWord3());
			st.setString(13, obj.getkWord4());
			st.setInt(14, obj.getkWordAnswer());
			st.setString(15, obj.getEnglishTrad());
			st.setString(16, obj.getPortugueseTrad());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0)
			{
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next())
				{
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else
			{
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Words obj) {
		PreparedStatement st = null;
		try
		{
			st = conn.prepareStatement(
					"UPDATE words "
					+ "SET PortugueseTrad = ? "
					+ "WHERE Id = ?");
			st.setString(1, obj.getPortugueseTrad());
			st.setInt(2, obj.getId());
			st.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try
		{
			st = conn.prepareStatement(
					"DELETE FROM words "
					+ "WHERE Id = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			if(rows < 1)
			{
				System.out.println("Id not exists!");
			}
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
		}
	}

	@Override
	public Words findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			st = conn.prepareStatement(
					"SELECT words.* "
					+ "FROM words "
					+ "WHERE words.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next())
			{
				Words words = instantiateWords(rs);
				return words;
			}
			return null;
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	@Override
	public List<Words> findByLevelAndLesson(Integer level, Integer lesson) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			st = conn.prepareStatement(
					"SELECT words.* "
					+ "FROM words "
					+ "WHERE words.Level = ? AND words.Lesson = ?");
			st.setInt(1, level);
			st.setInt(2, lesson);
			rs = st.executeQuery();
			List<Words> list = new ArrayList<>();
			Words words;
			while(rs.next())
			{
				words = instantiateWords(rs);
				list.add(words);
			}
			return list;
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Words> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			st = conn.prepareStatement(
					"SELECT words.* "
					+ "FROM words "
					+ "ORDER BY Level");
			rs = st.executeQuery();
			List<Words> list = new ArrayList<>();
			while(rs.next())
			{
				Words words = instantiateWords(rs);
				list.add(words);
			}
			return list;
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	private Words instantiateWords(ResultSet rs) throws SQLException {
		Words words = new Words();
		words.setId(rs.getInt("Id"));
		words.setLevel(rs.getInt("Level"));
		words.setLesson(rs.getInt("Lesson"));
		words.sethWordQuestion(rs.getString("HWordQuestion"));
		words.sethWord1(rs.getString("HWord1"));
		words.sethWord2(rs.getString("HWord2"));
		words.sethWord3(rs.getString("HWord3"));
		words.sethWord4(rs.getString("HWord4"));
		words.sethWordAnswer(rs.getInt("HWordAnswer"));
		words.setkWordQuestion(rs.getString("KWordQuestion"));
		words.setkWord1(rs.getString("KWord1"));
		words.setkWord2(rs.getString("KWord2"));
		words.setkWord3(rs.getString("KWord3"));
		words.setkWord4(rs.getString("KWord4"));
		words.setkWordAnswer(rs.getInt("KWordAnswer"));
		words.setEnglishTrad(rs.getString("EnglishTrad"));
		words.setPortugueseTrad(rs.getString("PortugueseTrad"));
		return words;
	}

}
