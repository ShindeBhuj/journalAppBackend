package net.bhuj.journalApp.controller;

import net.bhuj.journalApp.entity.JournalEntry;
import net.bhuj.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("{id}")
    public JournalEntry getJournalById(@PathVariable ObjectId id) {
        return journalEntryService.getById(id).orElse(null);
    }

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createJournal(@RequestBody JournalEntry journalEntry) {
        journalEntryService.saveEntry(journalEntry);
        return true;
    }

    @PutMapping("{id}")
    public JournalEntry updateById(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        return journalEntryService.updateById(id, journalEntry);
    }

    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable ObjectId id) {
        return journalEntryService.deleteEntry(id);
    }

    @DeleteMapping
    public boolean deleteAll() {
        return journalEntryService.deleteAll();
    }
}
