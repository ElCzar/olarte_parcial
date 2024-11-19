package web.parcial.olarte.olarte_parcial.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.parcial.olarte.olarte_parcial.dto.ContratoDTO;
import web.parcial.olarte.olarte_parcial.entities.Contrato;
import web.parcial.olarte.olarte_parcial.exceptions.ContratoException;
import web.parcial.olarte.olarte_parcial.repositories.ContratoRepository;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContratoService(ContratoRepository contratoRepository, ModelMapper modelMapper) {
        this.contratoRepository = contratoRepository;
        this.modelMapper = modelMapper;
    }

    public List<Contrato> getAllContratos() {
        return contratoRepository.findAll();
    }

    public Contrato getContratoById(Long id) {
        Optional<Contrato> optionalContrato = contratoRepository.findById(id);
        if (!optionalContrato.isPresent()) {
            throw new ContratoException("El contrato buscado no existe");
        }
        return optionalContrato.get();
    }

    private Contrato checkCorrectContrato(ContratoDTO contratoDTO) {
        if (contratoDTO.getFechaInicial() == null || contratoDTO.getFechaFinal() == null) {
            throw new ContratoException("No se permiten valores nulos");
        }
        if (contratoDTO.getIdentificador().isEmpty() || contratoDTO.getNombreContratante().isEmpty() || contratoDTO.getDocumentoContratante().isEmpty() || contratoDTO.getNombreContratantista().isEmpty() || contratoDTO.getDocumentoContratantista().isEmpty()) {
            throw new ContratoException("No se permiten valores vacíos");
        }
        if (contratoDTO.getFechaInicial().after(contratoDTO.getFechaFinal())) {
            throw new ContratoException("La fecha inicial no puede ser mayor a la fecha final");
        }
        if (contratoDTO.getValor() < 0) {
            throw new ContratoException("El valor del contrato no puede ser negativo");
        }
        return modelMapper.map(contratoDTO, Contrato.class);
    }

    public void createContrato(ContratoDTO contratoDTO) {
        Contrato contrato = checkCorrectContrato(contratoDTO);
        contratoRepository.save(contrato);
    }

    public void updateContrato(ContratoDTO contratoDTO, Long id) {
        Optional<Contrato> optionalContrato = contratoRepository.findById(id);
        if (!optionalContrato.isPresent()) {
            throw new ContratoException("El contrato por actualizar no existe");
        }
        Contrato contrato = optionalContrato.get();
        Contrato contratoActualizado = checkCorrectContrato(contratoDTO);
        contrato.setIdentificador(contratoActualizado.getIdentificador());
        contrato.setValor(contratoActualizado.getValor());
        contrato.setNombreContratante(contratoActualizado.getNombreContratante());
        contrato.setDocumentoContratante(contratoActualizado.getDocumentoContratante());
        contrato.setNombreContratantista(contratoActualizado.getNombreContratantista());
        contrato.setDocumentoContratantista(contratoActualizado.getDocumentoContratantista());
        contrato.setFechaInicial(contratoActualizado.getFechaInicial());
        contrato.setFechaFinal(contratoActualizado.getFechaFinal());
        contratoRepository.save(contratoActualizado);
    }

    public void deleteContrato(Long id) {
        Optional<Contrato> optionalContrato = contratoRepository.findById(id);
        if (!optionalContrato.isPresent()) {
            throw new ContratoException("El contrato por eliminar no existe");
        }
        contratoRepository.deleteById(id);
    }
}