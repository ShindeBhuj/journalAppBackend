package net.bhuj.journalApp.controller;

import net.bhuj.journalApp.entity.JournalEntry;
import net.bhuj.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id) {
        return journalEntryService.getById(id)
                .map(journalEntry -> new ResponseEntity<>(journalEntry, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{userName}")
    public List<JournalEntry> getAllJournalByUser(@PathVariable String userName) {
        return journalEntryService.getAllJournalByUser(userName);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(journalEntry, userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        try {
            JournalEntry jo = journalEntryService.updateById(id, journalEntry);
            return new ResponseEntity<>(jo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id, @PathVariable String userName) {
        try {
            journalEntryService.deleteEntry(id, userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        try {
            journalEntryService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
