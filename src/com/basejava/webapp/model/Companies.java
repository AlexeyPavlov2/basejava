package com.basejava.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Companies extends AbstractObjectListSection<Company> {

    public Companies(List<Company> items) {
        super(items);
    }

    @Override
    public void print() {
        items.stream()
                .sorted((x1, x2) -> -x1.getStart().compareTo(x2.getStart()))
                .collect(Collectors.toList())
                .forEach(x -> {
                    System.out.println(x.getLink().getTitle() + "    " + (x.getLink() == null ? "" : x.getLink().getLink()));
                    System.out.println(x.getStart().format(DateTimeFormatter.ofPattern("MM'/'yyyy")) + " - " +
                            (x.getEnd() == null ? "Сейчас" : x.getEnd().format(DateTimeFormatter.ofPattern("MM'/'yyyy"))));
                    System.out.println(x.getText());
                    if (!x.getDescription().isEmpty()) {
                        System.out.println(x.getDescription());
                    }
                }
        );
    }

}
