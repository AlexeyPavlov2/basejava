package com.basejava.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable {
    private static final long serialVersionUID = 4271811637027252063L;
    private HyperLink link;
    private List<CompanyPersonalInfo> companyPersonalInfoList;

    public Company(HyperLink link, List<CompanyPersonalInfo> companyPersonalInfoList) {
        this.link = link;
        this.companyPersonalInfoList = companyPersonalInfoList;
    }

    public Company(String title, String link, CompanyPersonalInfo... companyPersonalInfos) {
        this(new HyperLink(title, link), Arrays.asList(companyPersonalInfos));
    }

    public HyperLink getLink() {
        return link;
    }

    public void setLink(HyperLink link) {
        this.link = link;
    }

    public List<CompanyPersonalInfo> getCompanyPersonalInfoList() {
        return companyPersonalInfoList;
    }

    public void setCompanyPersonalInfoList(List<CompanyPersonalInfo> companyPersonalInfoList) {
        this.companyPersonalInfoList = companyPersonalInfoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(link, company.link) &&
                Objects.equals(companyPersonalInfoList, company.companyPersonalInfoList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(link, companyPersonalInfoList);
    }

    @Override
    public String toString() {
        return "Company{" +
                "link=" + link +
                ", companyPersonalInfoList=" + companyPersonalInfoList +
                '}';
    }
}
