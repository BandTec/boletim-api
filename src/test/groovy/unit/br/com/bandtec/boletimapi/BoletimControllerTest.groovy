package unit.br.com.bandtec.boletimapi

import br.com.bandtec.boletimapi.controllers.BoletimController
import br.com.bandtec.boletimapi.entity.Boletim
import br.com.bandtec.boletimapi.repository.BoletimRepository
import spock.lang.Specification

/**
 *
 * @author José Yoshiriro
 */
class BoletimControllerTest extends Specification {

    def 'deveria trazer um Boletim ou 404 no getUm()'() {
        given:
        BoletimController controller = new BoletimController();

        BoletimRepository repositoryMock = Mock(BoletimRepository)
        repositoryMock.findByIdAndToken(1, _) >> new Boletim()
        repositoryMock.findByIdAndToken(0, _) >> null

        controller.repository = repositoryMock

        when:
        def encontrado = controller.getUm(1, 'ache')

        then:
        encontrado.statusCodeValue == 200
        encontrado.body instanceof Boletim

        when:
        def naoEncontrado = controller.getUm(0, 'não ache')

        then:
        naoEncontrado.statusCodeValue == 404

    }
}
