package demo.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import demo.bean.Film;
import demo.dao.IFilmDao;

@Repository("filmDao")
public class FilmDaoImpl extends BaseDaoImpl<Film> implements IFilmDao {

	@Override
	public Film findByInventoryId(int inventoryId) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "select f from film f inner join inventory i on f.filmId = i.filmId where i.inventoryId = ?1";
		Query query = session.createQuery(hql);
		query.setParameter(1, inventoryId);
		return (Film) query.getSingleResult();
	}

}