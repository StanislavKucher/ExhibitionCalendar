package com.exhibitionCalendar.service.interfaces;

import com.exhibitionCalendar.model.entities.User;

import java.util.Map;

public interface UserService {
    User createNewUser (Map<String, String> reqParams);
}
