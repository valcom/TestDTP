package dtp.MainTestDTP;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dtp.PGData.entity.Conto;
import dtp.PGData.repository.ContoPgRepository;
import dtp.mysqlDATA.repository.ContoMysqlRepository;

/**
 * Unit test for simple App.
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml" })
@Transactional
public class AppTest {
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);
	@Autowired
	private ContoPgRepository contoPgRepository;
	@Autowired
	private ContoMysqlRepository contoMysqlRepository;

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void testApp() {
		log.info("Inizio test");
		BigDecimal amount = new BigDecimal("100.43");
		BigInteger idContoPg = BigInteger.valueOf(1);
		BigInteger idContoMysql = BigInteger.valueOf(1);
		Conto contoPg = contoPgRepository.getOne(idContoPg);
		dtp.mysqlDATA.entity.Conto contoMysql = contoMysqlRepository.getOne(idContoMysql);
		BigDecimal importoTotale = contoPg.getImporto().add(contoMysql.getImporto());
		log.info("importo contoMysql=" + contoMysql.getImporto());
		log.info("importo contoPg=" + contoPg.getImporto());
		log.info("importoTotale=" + importoTotale);
		contoPg.setImporto(contoPg.getImporto().subtract(amount));
		contoMysql.setImporto(contoMysql.getImporto().add(amount));
		contoPgRepository.save(contoPg);
		contoMysqlRepository.save(contoMysql);
		Conto contoPgAtt = contoPgRepository.getOne(idContoPg);
		dtp.mysqlDATA.entity.Conto contoMysqlAtt = contoMysqlRepository.getOne(idContoMysql);
		BigDecimal importoTotaleAtt = contoPgAtt.getImporto().add(contoMysqlAtt.getImporto());
		log.info("importo contoMysqlAtt=" + contoMysqlAtt.getImporto());
		log.info("importo contoPgAtt=" + contoPgAtt.getImporto());
		log.info("importoTotaleAtt=" + importoTotaleAtt);
		assertEquals(importoTotale, importoTotaleAtt);
	}
}
