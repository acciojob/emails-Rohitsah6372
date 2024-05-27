package com.driver;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Gmail extends Email {

    private int inboxCapacity; //maximum number of mails inbox can store
    private Queue<Mail> inbox; // Stores mails
    private List<Mail> trash; // Stores mails

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new LinkedList<>();
        this.trash = new ArrayList<>();
    }

    private class Mail {
        Date date;
        String sender;
        String message;

        Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }

    public void receiveMail(Date date, String sender, String message){
        if (inbox.size() == inboxCapacity) {
            // Move the oldest mail to trash
            trash.add(inbox.poll());
        }
        // Add the new mail to the inbox
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message){
        for (Mail mail : inbox) {
            if (mail.message.equals(message)) {
                inbox.remove(mail);
                trash.add(mail);
                break;
            }
        }
    }

    public String findLatestMessage(){
        if (inbox.isEmpty()) {
            return null;
        }
        // Return the message of the latest mail
        return ((LinkedList<Mail>) inbox).getLast().message;
    }

    public String findOldestMessage(){
        if (inbox.isEmpty()) {
            return null;
        }
        // Return the message of the oldest mail
        return ((LinkedList<Mail>) inbox).getFirst().message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        int count = 0;
        for (Mail mail : inbox) {
            if (!mail.date.before(start) && !mail.date.after(end)) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        return inbox.size();
    }

    public int getTrashSize(){
        return trash.size();
    }

    public void emptyTrash(){
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }
}
