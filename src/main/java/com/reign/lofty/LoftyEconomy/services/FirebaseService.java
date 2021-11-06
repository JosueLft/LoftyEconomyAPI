package com.reign.lofty.LoftyEconomy.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.reign.lofty.LoftyEconomy.entities.Record;
import com.reign.lofty.LoftyEconomy.entities.User;
import com.reign.lofty.LoftyEconomy.enums.RecordCategory;
import com.reign.lofty.LoftyEconomy.utils.SplitDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.reign.lofty.LoftyEconomy.constants.Constants.USER_RESPONSE;

@Service
public class FirebaseService {

    public User findRecords(User user) {
        try {
            if(USER_RESPONSE.getRecords().size() > 0) {
                USER_RESPONSE.getRecords().clear();
            }
            Iterable<DocumentReference> documentReference = firebaseReference().listDocuments();
            for (DocumentReference reference : documentReference) {
                User userDocument = getDocumentSnapshot(reference).toObject(User.class);
                if(userDocument.getEmail().equals(user.getEmail())) {
                    USER_RESPONSE = userDocument;
                    CollectionReference collectionReference = reference.collection("records")
                            .document(LocalDate.now().getYear() + "")
                            .collection(Month.of(10) + "");
                    for (DocumentReference listDocument : collectionReference.listDocuments()) {
                        USER_RESPONSE.getRecords().add(getDocumentSnapshot(listDocument).toObject(Record.class));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return USER_RESPONSE;
    }

    public List<Record> findMonthRecords(int month, User user) {
        try {
            if(USER_RESPONSE.getRecords().size() > 0) {
                USER_RESPONSE.getRecords().clear();
            }
            Iterable<DocumentReference> documentReference = firebaseReference().listDocuments();
            for (DocumentReference reference : documentReference) {
                User userDocument = getDocumentSnapshot(reference).toObject(User.class);
                if(userDocument.getUid().equals(user.getUid())) {
                    CollectionReference collectionReference = reference.collection("records")
                            .document(LocalDate.now().getYear() + "")
                            .collection(Month.of(month) + "");
                    for (DocumentReference listDocument : collectionReference.listDocuments()) {
                        USER_RESPONSE.getRecords().add(getDocumentSnapshot(listDocument).toObject(Record.class));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return USER_RESPONSE.getRecords();
    }

    public Record saveRecordsDetails(User record) {
        record.setUid(UUID.randomUUID().toString());
        record.getRecords().get(0).setUid(UUID.randomUUID().toString());
        LocalDate date = SplitDate.separateDayMonthYear(record.getRecords().get(0).getDate());
        User user = new User(record.getUid(), record.getProfilephoto(), record.getName(), record.getEmail(), record.getCurrentMonthAmount(), record.getPreviousMonthAmount());
        firebaseReference()
                .document(user.getEmail())
                .set(user);

        firebaseReference()
                .document(user.getEmail())
                .collection("records")
                .document(date.getYear() + "")
                .collection(date.getMonth() + "")
                .document(record.getRecords().get(0).getUid())
                .set(record.getRecords().get(0));
        return record.getRecords().get(0);
    }

    public void updateAmountMonth(User user) {
        try {
            if(USER_RESPONSE.getRecords().size() > 0) {
                USER_RESPONSE.getRecords().clear();
            }
            Iterable<DocumentReference> documentReference = firebaseReference().listDocuments();
            for (DocumentReference reference : documentReference) {
                User userDocument = getDocumentSnapshot(reference).toObject(User.class);
                if(userDocument.getEmail().equals(user.getEmail())) {
                    user.getRecords().forEach(record -> {
                        if (record.getRecordCategory().equals(null)) {
                            record.setRecordCategory(RecordCategory.SPENDING);
                        }
                    });
                    reference.set(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(User user) {
        try {
            if(USER_RESPONSE.getRecords().size() > 0) {
                USER_RESPONSE.getRecords().clear();
            }
            Iterable<DocumentReference> documentReference = firebaseReference().listDocuments();
            for (DocumentReference reference : documentReference) {
                User userDocument = getDocumentSnapshot(reference).toObject(User.class);
                if(userDocument.getEmail().equals(user.getEmail())) {
                    user.getRecords().forEach(record -> {
                        if (record.getRecordCategory().equals(null)) {
                            record.setRecordCategory(RecordCategory.SPENDING);
                        }
                    });
                    reference.set(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CollectionReference firebaseReference() {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection("users");
    }

    private DocumentSnapshot getDocumentSnapshot(DocumentReference reference) throws InterruptedException, ExecutionException {
        ApiFuture<DocumentSnapshot> apiFuture = reference.get();
        DocumentSnapshot document = apiFuture.get();
        return document;
    }
}