package com.reign.lofty.LoftyEconomy.resources;

import com.reign.lofty.LoftyEconomy.entities.User;
import com.reign.lofty.LoftyEconomy.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.reign.lofty.LoftyEconomy.entities.Record;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/records")
public class RecordResources {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public ResponseEntity<User> findRecords(@RequestBody User user) {
        try {
            User userResponse = firebaseService.findRecords(user);
            return ResponseEntity.ok().body(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new User());
        }
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Record> findById(@PathVariable Long id) {
//        Record obj = service.findById(id);
//        return ResponseEntity.ok().body(obj);
//    }

    @GetMapping(value = "/month/{month}")
    public ResponseEntity<List<Record>> findMonthRecords(@PathVariable int month, @RequestBody User user) {
        try {
            List<Record> list = firebaseService.findMonthRecords(month, user);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Record> insert(@RequestBody User user) {
        Record rec = new Record();
        try {
            rec = firebaseService.saveRecordsDetails(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(rec);
    }

    @PostMapping(value = "/previousAmount")
    public ResponseEntity<Record> updateAmountMonth(@RequestBody User user) {
        Record rec = new Record();
        try {
            firebaseService.updateAmountMonth(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(rec);
        }
        return ResponseEntity.ok().body(rec);
    }

//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Record> delete(@PathVariable Long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Record> update(@PathVariable Long id, @RequestBody Record record) {
//        record = service.update(id, record);
//        return ResponseEntity.ok().body(record);
//    }
}