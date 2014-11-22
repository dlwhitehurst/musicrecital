package org.musicrecital.webapp.services;

import org.musicrecital.model.User;

/**
 *
 * Sends email notification
 *
 * @author Serge Eby
 */
public interface EmailService {
    void send(User user, String subject, String message, String url, boolean hint);
}
