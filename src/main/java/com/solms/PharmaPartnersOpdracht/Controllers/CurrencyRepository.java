package com.solms.PharmaPartnersOpdracht.Controllers;
/*

        Author: Johan Solms - johan.solms@gmail.com

 */
import com.solms.PharmaPartnersOpdracht.Models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}