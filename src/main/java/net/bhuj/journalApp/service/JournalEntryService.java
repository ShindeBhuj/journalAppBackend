package net.bhuj.journalApp.service;


import net.bhuj.journalApp.entity.JournalEntry;
import net.bhuj.journalApp.entity.User;
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
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        journalEntry.setDateTime(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        User user = userService.getByUserName(userName);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAllJournalByUser(String userName) {
        User user = userService.getByUserName(userName);
        return user.getJournalEntries();
//        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public boolean deleteEntry(ObjectId id, String userName) {
        journalEntryRepository.deleteById(id);
        User user = userService.getByUserName(userName);
        user.getJournalEntries().removeIf(jo -> jo.getId().equals(id));
        userService.saveEntry(user);
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
