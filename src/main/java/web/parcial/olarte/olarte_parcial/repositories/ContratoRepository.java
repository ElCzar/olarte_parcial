package web.parcial.olarte.olarte_parcial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import web.parcial.olarte.olarte_parcial.entities.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    Contrato findById(long id);
}