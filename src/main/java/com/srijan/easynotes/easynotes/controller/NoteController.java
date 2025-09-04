package com.srijan.easynotes.easynotes.controller;

import com.srijan.easynotes.easynotes.exception.ResourceNotFoundException;
import com.srijan.easynotes.easynotes.model.Notes;
import com.srijan.easynotes.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<Notes> getAllNotes() {
        return noteRepository.findAll();
    }

    // Create a single Note
    @PostMapping({"/notes"})
    public Notes createNote(@Valid @RequestBody Notes note) {
        return noteRepository.save(note);
    }

    // Create bulk Notes
    @PostMapping({"/notes/bulk"})
    public List<Notes> createNotes(@Valid @RequestBody List<Notes> notes) {
        return noteRepository.saveAll(notes);
    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public Notes getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public Notes updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Notes noteDetails) {

        Notes note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Notes updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Notes note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}