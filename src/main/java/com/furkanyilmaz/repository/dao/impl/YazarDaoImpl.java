package com.furkanyilmaz.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.furkanyilmaz.model.pojo.entity.Yazar;
import com.furkanyilmaz.repository.dao.YazarDao;

@SuppressWarnings("deprecation")
@Repository


//SessionFactory.openSession(),her zaman, işlemleri tamamladığınızda kapatmanız gereken yeni bir session(oturum) açar. 
//SessionFactory.getCurrentSession(), bir context'e bağlı bir session döndürür - bunu kapatmanıza gerek yoktur.
//Web uygulaması başına asla bir oturum kullanmamalısınız.(one session per web app)
//Her zaman "istek başına bir oturum"("one session per request") veya "işlem başına bir oturum"("one session per transaction") kullanmalısınız.
//Sonuç olarak sessionFactory.openSession(); kullanmak daha iyidir.


public class YazarDaoImpl implements YazarDao {

	private static final Logger logger = Logger.getLogger(YazarDaoImpl.class);

	Query sorgu = null;
	Transaction transaction = null;

	@Autowired // @Inject
	private SessionFactory sessionFactory;

	// ---------------------------------------------
	public YazarDaoImpl() {
		System.out.println("YazarDaoImpl");
	}

	// ---------------------------------------------
	@Override
	public long createYazar(Yazar yazar) {
		long l = 0;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			l = (long) session.save(yazar);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return l;
	}

	@Override
	public void createYazarToplu(Yazar yazar) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(yazar);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	@Override
	public Yazar updateYazar(Yazar yazar) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(yazar); // Varsa günceller yoksa ekler.
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return yazar;

	}

	@Override
	public void deleteYazar(long yazarId) {
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			String hql = "delete Yazar where yazarId = :id";
			Query q = session.createQuery(hql).setParameter("id", yazarId);
			q.executeUpdate(); // Update veya delete kodlarını çalıştırır.
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public List<Yazar> findAllYazar() {
		List<Yazar> yazarListe = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			yazarListe = session.createQuery("FROM Yazar").list();
			for (Yazar m : yazarListe) {
				logger.info("Yazar List:" + m);
			}
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return yazarListe;

	}

	@Override
	public Yazar findYazar(long yazarId) {
		Yazar y = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			y = session.get(Yazar.class, yazarId); //Sessiondan değer okuyoruz.
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return y;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Yazar> findYazarlar(String arananYazarAdi) {
		List<Yazar> yazarlar = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			String sqlSorgusu = "SELECT y.* FROM Yazar y WHERE y.yazarAdi LIKE '" + arananYazarAdi + "%'";
			List<Object[]> yazarObjects = session.createSQLQuery(sqlSorgusu).list();

			yazarlar = new ArrayList<Yazar>();

			for (Object[] yazarObject : yazarObjects) {
				Yazar yazar = new Yazar();

				String stringYazarId = String.valueOf(yazarObject[0]);
				Long longYazarId = Long.parseLong(stringYazarId);

				long yazarId = longYazarId;
				String yazarAciklama = (String) yazarObject[1];
				String yazarAdi = (String) yazarObject[2];

				yazar.setYazarId(yazarId);
				yazar.setYazarAdi(yazarAdi);
				yazar.setYazarAciklama(yazarAciklama);

				yazarlar.add(yazar);
				transaction.commit();
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		System.out.println(yazarlar);
		return yazarlar;
	}

	@Override
	public List<Yazar> getListAllYazarOrderByDESC() {
		List<Yazar> yazarlar = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			String hql = "FROM Yazar y ORDER BY yazarAdi DESC";
			Query q = session.createQuery(hql);
			yazarlar = q.list();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return yazarlar;
	}
}