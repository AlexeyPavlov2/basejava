package com.basejava.webapp.util;

import com.basejava.webapp.model.*;

import java.util.ArrayList;
import java.util.UUID;

public class ResumeUtil {
    private ResumeUtil() {
    }

    public static Resume getNewResume() {
        Resume resume = new Resume(UUID.randomUUID().toString(), "");
        addAllEmptySection(resume);
        return resume;
    }

    public static void addAllEmptySection(Resume resume) {
        for (SectionType type : SectionType.values()) {
            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    if (resume.getSection(type) == null) {
                        resume.putSection(type, new TextSection(""));
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    if (resume.getSection(type) == null) {
                        resume.putSection(type, new ListSection<String>(new ArrayList<>()));
                    }
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    if (resume.getSection(type) == null) {
                        CompanySection companySection = new CompanySection();
                        //TODO здесь закончил
                        companySection.setItems(new ArrayList<Company>());
                        resume.putSection(type, companySection);
                    }
                    break;
            }
        }
    }
}
