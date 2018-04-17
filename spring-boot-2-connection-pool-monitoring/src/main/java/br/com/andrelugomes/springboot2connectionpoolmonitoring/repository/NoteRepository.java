package br.com.andrelugomes.springboot2connectionpoolmonitoring.repository;

import br.com.andrelugomes.springboot2connectionpoolmonitoring.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long>{

}
