/**
 * Copyright 2014 David L. Whitehurst
 * 
 * Licensed under the Apache License, Version 2.0 
 * (the "License"); You may not use this file except 
 * in compliance with the License. You may obtain a 
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the 
 * License.
 * 
 */

package org.musicrecital.webapp.services.impl;

import org.apache.tapestry5.ioc.Messages;
import org.musicrecital.model.User;
import org.musicrecital.service.MailEngine;
import org.musicrecital.webapp.services.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Serge Eby
 */
public class EmailServiceImpl implements EmailService {
    final private MailEngine mailEngine;
    final private SimpleMailMessage simpleMailMessage;
    final private Messages messages;

    public EmailServiceImpl(SimpleMailMessage simpleMailMessage, MailEngine mailEngine, Messages messages) {
        this.simpleMailMessage = simpleMailMessage;
        this.mailEngine = mailEngine;
        this.messages = messages;
    }


    public void send(User user, String subject, String message, String url, boolean hint)
            throws UsernameNotFoundException, MailException {

        // Message Template
        StringBuilder msg = new StringBuilder(message);
        // Skip credentials in Password Hint
        if (!hint) {
            msg.append("\n\n").append(messages.get("user.username"));
            msg.append(": ").append(user.getUsername()).append("\n");
            msg.append(messages.get("user.password")).append(": ");
            msg.append(user.getPassword());
        }
        msg.append("\n\nLogin at: ").append(url);

        simpleMailMessage.setTo(user.getFullName() + "<" + user.getEmail() + ">");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg.toString());

        mailEngine.send(simpleMailMessage);
    }
}
