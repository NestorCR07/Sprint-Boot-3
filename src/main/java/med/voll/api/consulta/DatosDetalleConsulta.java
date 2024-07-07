package med.voll.api.consulta;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(Long id,
                                   Long id_Paciente,
                                   Long id_Medico,
                                   LocalDateTime fecha) {
}
