package com.basejava.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.basejava.webapp.model.CompanyPersonalInfo.FUTURE_DATE;

public class CompanySection extends ListSection<Company> {
    private static final long serialVersionUID = 2920665298325462887L;

    public CompanySection(List<Company> items) {
        super(items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanySection)) return false;
        CompanySection cs = (CompanySection) o;
        ArrayList<Company> listA = new ArrayList<>(cs.items);
        ArrayList<Company> listB = new ArrayList<>(this.items);
        return listA.containsAll(listB) && listB.containsAll(listA);
    }



    @Override
    public void print() {
        items.stream().forEach(el -> {
                    el.getLink().print();
                    for (CompanyPersonalInfo v : el.getCompanyPersonalInfoList()) {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yyyy");
                        System.out.println(v.getStart().format(format) + " - " + (v.getEnd().equals(FUTURE_DATE) ? "Сейчас" : v.getEnd().format(format)));
                        System.out.println(v.getText());
                        if (!v.getDescription().isEmpty())
                            System.out.println(v.getDescription());
                    }
                }
        );
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "items=" + items +
                '}';
    }
}
