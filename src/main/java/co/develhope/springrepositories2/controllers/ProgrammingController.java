package co.develhope.springrepositories2.controllers;

import co.develhope.springrepositories2.entities.ProgrammingLanguage;
import co.develhope.springrepositories2.repositories.ProgrammingLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/repo-prog-languages")
public class ProgrammingController {

    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @PostMapping
    public ProgrammingLanguage newPage(@RequestBody ProgrammingLanguage programmingLanguage) {
        programmingLanguage.setId(null);
        return programmingLanguageRepository.saveAndFlush(programmingLanguage);
    }

    @GetMapping
    public Page<ProgrammingLanguage> getPage(
            @RequestParam Optional<Integer> program, @RequestParam Optional<Integer> area) {
        if (program.isPresent() && area.isPresent()) {
            Pageable pageable = PageRequest.of(program.get(), area.get());
            return programmingLanguageRepository.findAll(pageable);
        } else {
            return Page.empty();
        }
    }

}
