/**
 * 
 */
package dtp.mysqlDATA.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import dtp.mysqlDATA.entity.Conto;

/**
 * @author valer
 *
 */
public interface ContoMysqlRepository extends JpaRepository<Conto, BigInteger> {
}
