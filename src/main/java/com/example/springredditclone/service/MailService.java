package com.example.springredditclone.service;

import com.example.springredditclone.model.NotificationEmail;

public interface MailService {
    void sendEmail(NotificationEmail notificationEmail);
}
