$(function () {
    // add company event
    $("#EDUCATIONadd_company_button").click(function () {
        $(".EDUCATIONnew_div").removeClass("d-none");
        $("#EDUCATIONadd_company_button").addClass("disabled")

    });
    $("#EXPERIENCEadd_company_button").click(function () {
        $(".EXPERIENCEnew_div").removeClass("d-none");
        $("#EXPERIENCEadd_company_button").addClass("disabled")

    });

    // delete EXPERIENCE company event
    $("button[class~='EXPERIENCEdelete_company_button']").click(function () {
        var id = $(this).attr("id");
        var button = $(this);
        var divId = $(this).attr("data-company-div");

        $("#" + divId).remove();

        var list = $("div[id^='EXPERIENCEcompanyDiv']");

        list.each(function (index1) {
            var oldDiv = $(this);
            var idCompany = $(this).attr('id');
            var str = 'div#' + idCompany + ' input, ' + 'div#' + idCompany + ' textarea' ;
            var list1 = $(str);
            list1.each(function (index2) {
                var name = $(this).attr('name');
                var prefix = "";
                switch (true) {
                    case name.indexOf("startDate") > 0:
                        prefix = name.substr(0, name.indexOf("startDate"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "startDate");
                        $(this).attr("id", prefix + "startDate")
                        break;
                    case name.indexOf("endDate") > 0:
                        prefix = name.substr(0, name.indexOf("endDate"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "endDate");
                        $(this).attr("id", prefix + "endDate");
                        break;
                    case name.indexOf("text") > 0:
                        prefix = name.substr(0, name.indexOf("text"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "text");
                        $(this).attr("id", prefix + "text");
                        break;
                    case name.indexOf("description") > 0:
                        prefix = name.substr(0, name.indexOf("description"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "description");
                        $(this).attr("id", prefix + "description");
                        break;
                }

            });

            // New id for company div
            var newDivId = "EXPERIENCEcompanyDiv" + index1;
            $(oldDiv).attr("id", newDivId);

            var buttonDeleteId = $(oldDiv).find('button.btn-danger');
            $(buttonDeleteId).attr("data-company-div", "EXPERIENCEcompanyDiv" + index1);
            $(buttonDeleteId).attr("id", "EXPERIENCEdelete_company_button" + index1);


        });

        console.log($('div.container-fluid').html());

    });

    // delete EDUCATION company event
    $("button[class~='EDUCATIONdelete_company_button']").click(function () {
        var id = $(this).attr("id");
        var button = $(this);
        var divId = $(this).attr("data-company-div");

        $("#" + divId).remove();

        var list = $("div[id^='EDUCATIONcompanyDiv']");

        list.each(function (index1) {
            var oldDiv = $(this);
            var idCompany = $(this).attr('id');
            var str = 'div#' + idCompany + ' input, ' + 'div#' + idCompany + ' textarea' ;
            var list1 = $(str);
            list1.each(function (index2) {
                var name = $(this).attr('name');
                var prefix = "";
                switch (true) {
                    case name.indexOf("startDate") > 0:
                        prefix = name.substr(0, name.indexOf("startDate"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "startDate");
                        $(this).attr("id", prefix + "startDate")
                        break;
                    case name.indexOf("endDate") > 0:
                        prefix = name.substr(0, name.indexOf("endDate"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "endDate");
                        $(this).attr("id", prefix + "endDate");
                        break;
                    case name.indexOf("text") > 0:
                        prefix = name.substr(0, name.indexOf("text"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "text");
                        $(this).attr("id", prefix + "text");
                        break;
                    case name.indexOf("description") > 0:
                        prefix = name.substr(0, name.indexOf("description"));
                        prefix = prefix.replace(/\d/g, '') + index1;
                        $(this).attr("name", prefix + "description");
                        $(this).attr("id", prefix + "description");
                        break;
                }

            });

            // New id for company div
            var newDivId = "EDUCATIONcompanyDiv" + index1;
            $(oldDiv).attr("id", newDivId);

            var buttonDeleteId = $(oldDiv).find('button.btn-danger');
            $(buttonDeleteId).attr("data-company-div", "EDUCATIONcompanyDiv" + index1);
            $(buttonDeleteId).attr("id", "EDUCATIONdelete_company_button" + index1);


        });

        console.log($('div.container-fluid').html());

    });


});