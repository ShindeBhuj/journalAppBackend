package net.bhuj.journalApp.service;


import net.bhuj.journalApp.entity.JournalEntry;
import net.bhuj.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDateTime(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public boolean deleteEntry(ObjectId id) {
        journalEntryRepository.deleteById(id);
        return true;
    }

    public boolean deleteAll() {
        journalEntryRepository.deleteAll();
        return true;
    }

    public JournalEntry updateById(ObjectId id, JournalEntry newEntry) {
        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
        }
        return journalEntryRepository.save(old);
    }
}
