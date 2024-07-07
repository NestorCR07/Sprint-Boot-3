package med.voll.api.consulta;

import med.voll.api.controller.PacienteController;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.errores.ValidacionDeIntegridad;
import org.hibernate.boot.beanvalidation.BeanValidationIntegrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendar(DatosAgendarConsulta datos){

        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
        }
        if(datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("este id para el medico no fue encontrado");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var medico = seleccionarMedico(datos);

        var consulta = new Consulta(null,medico,paciente,datos.fecha());

        consultaRepository.save(consulta);

    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {

        if(datos.idMedico()!= null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null)
        {
            throw new ValidacionDeIntegridad("debe seleccionarse una especilidad para el medico");
        }

    return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(
            datos.especialidad(),datos.fecha()
    );
    }

}
