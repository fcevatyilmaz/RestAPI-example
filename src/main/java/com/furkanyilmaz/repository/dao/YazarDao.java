package com.furkanyilmaz.repository.dao;

import java.util.List;

import com.furkanyilmaz.model.pojo.entity.Yazar;

public interface YazarDao {

	public long createYazar(Yazar yazar);

	public void createYazarToplu(Yazar yazar);

	public void deleteYazar(long yazarId);

	public Yazar updateYazar(Yazar yazar);

	public List<Yazar> findAllYazar();

	public Yazar findYazar(long yazarId);

	public List<Yazar> findYazarlar(String yazarAdi);

	public List<Yazar> getListAllYazarOrderByDESC();


}
