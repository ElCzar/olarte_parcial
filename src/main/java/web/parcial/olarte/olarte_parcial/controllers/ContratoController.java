package web.parcial.olarte.olarte_parcial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.parcial.olarte.olarte_parcial.dto.ContratoDTO;
import web.parcial.olarte.olarte_parcial.services.ContratoService;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    private final ContratoService contratoService;

    @Autowired
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping
    public ResponseEntity<List<ContratoDTO>> getAllContratos() {
        List<ContratoDTO> contratos = contratoService.getAllContratos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> getContratoById(@PathVariable Long id) {
        ContratoDTO contrato = contratoService.getContratoById(id);
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