package com.furkanyilmaz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.furkanyilmaz.model.pojo.entity.Yazar;
import com.furkanyilmaz.repository.dao.YazarDao;
import com.furkanyilmaz.service.YazarService;

@Service
@Transactional
public class YazarServiceImpl implements YazarService {

	@Autowired
	private YazarDao yazarDao;

	public YazarServiceImpl() {
		System.out.println("YazarServiceImpl");
	}

	@Override
	public long createYazar(Yazar yazar) {
		return yazarDao.createYazar(yazar);
	}

	@Override
	public void createYazarToplu(Yazar yazar) {
		yazarDao.createYazarToplu(yazar);

	}

	@Override
	public Yazar updateYazar(Yazar yazar) {
		return yazarDao.updateYazar(yazar);
	}

	@Override
	public void deleteYazar(long yazarId) {
		yazarDao.deleteYazar(yazarId);
	}

	@Override
	public List<Yazar> findAllYazar() {
		return yazarDao.findAllYazar();
	}

	@Override
	public Yazar findYazar(long yazarId) {
		return yazarDao.findYazar(yazarId);
	}

	@Override
	public List<Yazar> findYazarlar(String yazarAdi) {
		return yazarDao.findYazarlar(yazarAdi);
	}

	@Override
	public List<Yazar> getListAllYazarOrderByDESC() {
		return yazarDao.getListAllYazarOrderByDESC();
	}

}
