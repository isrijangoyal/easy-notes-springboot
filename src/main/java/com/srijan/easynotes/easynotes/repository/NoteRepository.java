package com.srijan.easynotes.easynotes.repository;

import com.srijan.easynotes.easynotes.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {

}