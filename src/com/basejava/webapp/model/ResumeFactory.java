package com.basejava.webapp.model;

import java.util.UUID;

public class ResumeFactory {
    public static Resume getNewResume() {
        Resume resume = new Resume(UUID.randomUUID().toString(), "");
        resume.addAllEmptySection();
        return resume;
    }


}
