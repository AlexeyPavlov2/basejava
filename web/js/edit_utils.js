$(function () {
    // add company event
    $(".EXPERIENCEadd_company_button, .EDUCATIONadd_company_button").click(function () {
        $(this).parent().parent().after(getNewCompanyDiv());
        var first = $("#companyDivTemplate").first();
        first.removeClass("template");
        if ($(this).hasClass("EDUCATIONadd_company_button")) {
            first.find(".description_row").addClass("d-none");
            first.attr("id", "EDUCATIONcompanyDiv777")
            recalcCompanyDiv("EDUCATION");
        } else {
            first.attr("id", "EXPERIENCEcompanyDiv777")
            recalcCompanyDiv("EXPERIENCE");
        }
    });

    // delete company event
    $(".EXPERIENCEdelete_company_button, .EDUCATIONdelete_company_button").click(function () {
        $("#" + $(this).attr("data-company-div")).remove();
        recalcCompanyDiv('EXPERIENCE');
        recalcCompanyDiv('EDUCATION');
    });

    // add period event
    $(".EXPERIENCEadd_period_button, .EDUCATIONadd_period_button").click(function () {
        var prefix = $(this).hasClass("EXPERIENCEadd_period_button") ? "EXPERIENCE" : "EDUCATION";
        $(this).parent().parent().after(getNewPeriodDiv());
        var first = $("#periodDivTemplate").first();
        first.removeClass("template");
        if (prefix === "EDUCATION") {
            first.find(".description_row").remove();
        }
        first.attr("id", prefix + "_periodDiv2222");
        recalcCompanyDiv(prefix);
    });

    // delete period event
    $('body').on('click', ".EXPERIENCEdelete_period_button, .EDUCATIONdelete_period_button", function (e) {
        var company = ("#" + $(this).attr("data-company-div"));
        $(company).find("#" + $(this).attr("data-company-period")).remove();
        recalcCompanyDiv('EXPERIENCE');
        recalcCompanyDiv('EDUCATION');
    });

    // Re-calc EXPERIENCE or EDUCATION sections
    function recalcCompanyDiv(type) {
        var companyType = type;
        var selectorCompanies = "div[id^=" + "'" + companyType + "companyDiv" + "']";
        var companyDivList = $(selectorCompanies);
        companyDivList.each(function (companyIndex) {
            var company = $(this);
            var companyId = companyType + "companyDiv" + companyIndex;
            company.attr("id", companyId);

            // Re-cal company title row
            var titleRow = company.children().first();
            var elementTitleRowList = titleRow.find("*");
            elementTitleRowList.each(function (elementIndex0) {
                var el = $(this);
                if (el.attr("name") == "for_company_title") {
                    el.attr("for", companyType);
                }
                if (el.attr("data-company-title-input-type") == "title") {
                    el.attr("name", companyType);
                    el.attr("id", companyType);
                }
                if (el.attr("name") == "for_company_url") {
                    el.attr("for", companyType + "companyURL" + companyIndex);
                }
                if (el.attr("data-company-title-input-type") == "url") {
                    el.attr("name", companyType + "companyURL");
                    el.attr("id", companyType + "companyURL" + companyIndex);
                }
                if (el.attr("data-company-button-type") == "delete") {
                    el.attr("class", companyType + "delete_company_button btn btn-danger vbottom");
                    el.attr("id", companyType + "delete_company_button" + companyIndex);
                    el.attr("data-company-div", companyType + "companyDiv" + companyIndex);
                }
            }); //elementTitleRowList

            // Re-calc company periods
            //add_period_button if exists
            var elementPeriodButtonRow = company.find(".add_period_button");
            var button = elementPeriodButtonRow.find("button");
            if (button.length > 0) {
                button.attr("class", companyType + "add_period_button btn btn-primary");
                button.attr("id", companyType + "add_period_button" + companyIndex);
                button.attr("data-company-div", companyType + "companyDiv" + companyIndex);
                button.attr("data-company-period", companyType + "_periodDiv");
            }

            // Get periods for the company id
            var periodsList = company.find(".period_div");

            var totalPeriods = 0;
            periodsList.each(function () {
                totalPeriods++;
            });

            periodsList.each(function (periodIndex) {
                var period = $(this);
                period.attr("id", companyType + "_periodDiv" + periodIndex);
                var periodRow = period.children().first();
                var elementPeriodRowList = periodRow.find("*");
                elementPeriodRowList.each(function (elementIndex1) {
                    var el1 = $(this);
                    if (el1.attr("name") == "for_start_date") {
                        el1.attr("for", companyType + companyIndex + "startDate");
                    }
                    if (el1.attr("data-period-input-type") == "startDate") {
                        el1.attr("name", companyType + companyIndex + "startDate");
                        el1.attr("id", companyType + companyIndex + "startDate");
                    }
                    if (el1.attr("name") == "for_end_date") {
                        el1.attr("for", companyType + companyIndex + "endDate");
                    }
                    if (el1.attr("data-period-input-type") == "endDate") {
                        el1.attr("name", companyType + companyIndex + "endDate");
                        el1.attr("id", companyType + companyIndex + "endDate");
                    }
                    if (el1.attr("name") == "for_text") {
                        el1.attr("for", companyType + companyIndex + "text");
                    }
                    if (el1.attr("data-period-input-type") == "text") {
                        el1.attr("name", companyType + companyIndex + "text");
                        el1.attr("id", companyType + companyIndex + "text");
                    }
                    if (el1.attr("data-period-button-type") == "delete") {
                        if (totalPeriods == 1 && periodIndex == 0) {
                            el1.attr("title", "Нельзя удалить единственный период");
                            el1.prop("disabled", true);

                        } else {
                            el1.attr("title", "Удалить период");
                            el1.prop("disabled", false);

                        }
                        el1.attr("class", companyType + "delete_period_button vbottom btn btn-link");
                        el1.attr("id", companyType + "delete_period_button");
                        el1.attr("data-company-div", companyType + "companyDiv" + companyIndex);
                        el1.attr("data-company-period", companyType + "_periodDiv" + periodIndex);
                    }


                }); //elementPeriodRowList

                // Processing description
                var descriptionRow = period.find(".description_row"); // Find description div
                if (descriptionRow.length > 0) {
                    var elementDescriptionRow = descriptionRow.find("*");
                    elementDescriptionRow.each(function (elementIndex2) {
                        var el2 = $(this);
                        if (el2.attr("name") == "for_description") {
                            el2.attr("for", companyType + companyIndex + "description");
                        }
                        if (el2.attr("data-period-input-type") == "description") {
                            el2.attr("name", companyType + companyIndex + "description");
                            el2.attr("id", companyType + companyIndex + "description");
                        }
                    });
                }
            });  // periodList
        }); //companyDivList
    }


    function getNewCompanyDiv() {
        return $(".company_template").html();
    }

    function getNewPeriodDiv() {
        return $(".period_template").html();
    }

    function printContent() {
        console.log($('div.container-fluid').html());
    }



});