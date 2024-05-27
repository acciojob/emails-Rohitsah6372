package com.driver;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail {

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE); // The inboxCapacity is equal to the maximum value an integer can store.
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        if (calendar.isEmpty()) {
            return 0;
        }

        // Sort meetings by their end times
        Collections.sort(calendar, Comparator.comparing(Meeting::getEndTime));

        int maxMeetings = 1; // At least one meeting can be attended
        Meeting lastAttendedMeeting = calendar.get(0);

        for (int i = 1; i < calendar.size(); i++) {
            if (calendar.get(i).getStartTime().isAfter(lastAttendedMeeting.getEndTime())) {
                maxMeetings++;
                lastAttendedMeeting = calendar.get(i);
            }
        }

        return maxMeetings;
    }
}

