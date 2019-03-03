/**
 * 
 */
package dtp.PGData.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import dtp.PGData.entity.Conto;

/**
 * @author valer
 *
 */
public interface ContoPgRepository extends JpaRepository<Conto, BigInteger> {
}
