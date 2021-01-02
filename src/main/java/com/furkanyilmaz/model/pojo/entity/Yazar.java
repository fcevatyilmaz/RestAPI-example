package com.furkanyilmaz.model.pojo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Yazar")
public class Yazar implements Serializable {

	private static final long serialVersionUID = -1465840286802545221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long yazarId; //Veritabanında kolon adı yazarId

	private String yazarAdi; //Veritabanında kolon adı yazarAdi

	@Column(name = "aciklama")
	private String yazarAciklama; //Veritabanında kolon adı yazarAciklama


	public long getYazarId() {
		return yazarId;
	}

	public void setYazarId(long yazarId) {
		this.yazarId = yazarId;
	}

	public String getYazarAdi() {
		return yazarAdi;
	}

	public void setYazarAdi(String yazarAdi) {
		this.yazarAdi = yazarAdi;
	}

	public String getYazarAciklama() {
		return yazarAciklama;
	}

	public void setYazarAciklama(String yazarAciklama) {
		this.yazarAciklama = yazarAciklama;
	}

	

	@Override
	public String toString() {
		return "Yazar [yazarId=" + yazarId + ", yazarAdi=" + yazarAdi + ", yazarAciklama=" + yazarAciklama + "]";
	}

	// ----------------------------
	public Yazar() {
		System.out.println("Yazar Entity");
	}

}