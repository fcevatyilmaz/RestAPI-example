package com.furkanyilmaz.controller;

import com.furkanyilmaz.model.pojo.entity.Yazar;
import com.furkanyilmaz.service.YazarService;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/yazarApi")
@Controller
public class YazarController {

	@Autowired 
	private YazarService yazarService;

	private static final Logger logger = Logger.getLogger(YazarController.class);

	public YazarController() {
		System.out.println("YazarController()");
	}

	@RequestMapping(value = "newYazar", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Yazar newYazar(@RequestBody Yazar yazar) {
		long id = yazarService.createYazar(yazar);
		logger.info("Yazar ekleniyor. id : " + id);
		return yazar;
	}

	@RequestMapping(value = "newYazarToplu", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody List<Yazar> newYazarToplu(@RequestBody List<Yazar> yazarList) {
		logger.info("Yazarlar toplu olarak ekleniyor.");

		for (Yazar y : yazarList) {
			yazarService.createYazarToplu(y);
		}

		return yazarList;
	}

	@RequestMapping(value = "editYazar/{yazarId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody Yazar updateYazar(@PathVariable(value = "yazarId") long id, @RequestBody Yazar yazar) {
		logger.info("Yazar duzenleniyor.");
		Yazar yazar3 = new Yazar();
		yazar3.setYazarId(yazar.getYazarId());
		yazar3.setYazarAdi(yazar.getYazarAdi());
		yazar3.setYazarAciklama(yazar.getYazarAciklama());
		logger.info("Yazar guncelleniyor. : " + yazar3);
		return yazarService.updateYazar(yazar3);

	}

	@RequestMapping(value = "removeYazar/{yazarId}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void deleteYazar(@PathVariable(value = "yazarId") long id) {
		logger.info("Yazar siliniyor. Id : " + id);
		yazarService.deleteYazar(id);
	}

	@RequestMapping(value = { "getListAllYazar", //Eğer pom.xml dosyasında jackson-dataformat-xml jar'ını eklemezsek bu şekilde xml dönüş'ü alamayız.
			"/listAllYazar" }, method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public List<Yazar> findAllYazar() {
		logger.info("Butun yazarlar.");
		return  yazarService.findAllYazar();
		
	}

	@RequestMapping(value = "searchYazarAdi", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Yazar> searchYazar(@RequestParam("searchAdi") String searchAdi) {
		logger.info("Arama Yazar. Yazar isimleri: " + searchAdi);
		return yazarService.findYazarlar(searchAdi);
		
	}

	@RequestMapping(value = "searchYazarId", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Yazar searchYazarTamİsim(@RequestParam("searchId") long yazarId) {
		logger.info("Arama Yazar tam isimle. Yazar isimleri: " + yazarId);
		return yazarService.findYazar(yazarId);

	}

	@RequestMapping(value = "getListAllYazarOrderByDESC", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Yazar> getListAllYazarOrderByDESC() {
		logger.info("Butun yazarlar.DESC sirali");
		return yazarService.getListAllYazarOrderByDESC();
	}

}