package hackathon.ru.controller.candidate;

import hackathon.ru.data.dto.candidate.CandidateDto;
import hackathon.ru.data.dto.candidate.custom.CandidateCardDto;
import hackathon.ru.data.dto.candidate.custom.CandidateForListDto;
import hackathon.ru.data.model.candidate.Candidate;
import hackathon.ru.service.candidate.iservice.CandidateService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Candidate controller")
@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + CandidateController.CANDIDATE_CONTROLLER_PATH)
public class CandidateController {

    public static final String CANDIDATE_CONTROLLER_PATH = "/candidates";
    public static final String ID = "/{id}";
    public static final String HR = "/hr";
    private final CandidateService candidateService;


    // GET /api/candidates/{id} - получение кандидата по идентификатору
    @GetMapping(ID)
    public Candidate getCandidateById(@PathVariable("id") final Long id) {
        return candidateService.getCandidateById(id);
    }

    @GetMapping()
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    // POST /api/candidates - создание нового кандидата

    @PostMapping()
    @ResponseStatus(CREATED)
    public Candidate createCandidate(
        @Parameter(schema = @Schema(implementation = CandidateDto.class))
        @RequestBody @Valid CandidateDto candidateDto) {
        return candidateService.createNewCandidate(candidateDto);
    }

    // GET /api/candidates - получение списка кандидатов


    // PUT /api/candidates/{id} - обновление кандидата
    @PutMapping(ID)
    public Candidate updateCandidate(@PathVariable("id") final Long id,
                                     @RequestBody @Valid final CandidateDto candidateDto) {
        return candidateService.updateCandidate(id, candidateDto);
    }


    // DELETE /api/candidates/{id} - удаление кандидата
    @DeleteMapping(ID)
    public void deleteCandidate(@PathVariable("id") final Long id) {
        candidateService.deleteCandidate(id);
    }



    //-----------DTO-----------//


    // GET /api/candidates/hr - получение списка кандидатов
    @GetMapping(HR)
    public List<CandidateForListDto> getAllCandidatesList() {
        return candidateService.getListOfCandidates();
    }


    // GET /api/candidates/hr/{id} - получение кандидата по идентификатору
    @GetMapping(HR + ID)
    public CandidateCardDto getCandidateCardById(@PathVariable("id") final Long id) {
        return candidateService.getCandidateCardById(id);
    }

}
