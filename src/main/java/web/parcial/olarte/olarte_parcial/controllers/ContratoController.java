package web.parcial.olarte.olarte_parcial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.parcial.olarte.olarte_parcial.dto.ContratoDTO;
import web.parcial.olarte.olarte_parcial.entities.Contrato;
import web.parcial.olarte.olarte_parcial.services.ContratoService;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    private final ContratoService contratoService;

    @Autowired
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> getAllContratos() {
        List<Contrato> contratos = contratoService.getAllContratos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getContratoById(@PathVariable Long id) {
        Contrato contrato = contratoService.getContratoById(id);
        return ResponseEntity.ok(contrato);
    }

    @PostMapping
    public ResponseEntity<Void> createContrato(@RequestBody ContratoDTO contratoDTO) {
        contratoService.createContrato(contratoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContrato(@RequestBody ContratoDTO contratoDTO, @PathVariable Long id) {
        contratoService.updateContrato(contratoDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable Long id) {
        contratoService.deleteContrato(id);
        return ResponseEntity.ok().build();
    }
}