package com.example.demo;

import com.example.demo.data.BICDirectoryEntry;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<BICDirectoryEntry, String> {
}
