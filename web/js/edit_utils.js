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
            var str = 'div#' + idCompany + ' input, ' + 'div#' + idCompany + ' textarea';
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
            var str = 'div#' + idCompany + ' input, ' + 'div#' + idCompany + ' textarea';
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


    // Processing add period button
    $("button[class~='EXPERIENCEadd_period_button']").click(function () {
        var prefix = 'EXPERIENCE';
        var id = $(this).attr('id');
        var companyIndex = id.replace(prefix + "add_period_button", "");
        var parent = $(this).parent().parent();
        var divId = $(this).attr("data-company-div");

        // Add period block
        $(parent).after(getNewPeriod(prefix, companyIndex, '0'));

        var selectorPeriods = 'div[id^=' + "'" + prefix + "_periodDiv" + "'" + ']';
        var list1 = $('#' + divId).children(selectorPeriods);
        list1.each(function (index1) {
            var childId = $(this).attr('id');
            var childName = $(this).attr('name');

            var str = 'div#' + childId + ' input';
            var list2 = $(str);
            list2.each(function (index2) {
                var inputName = $(this).attr('name');
                if ($(this).attr("type") == "input") {
                    $(this).attr("name", prefix + index1 + getInputName(inputName));
                    $(this).attr("id", prefix + index1 + getInputName(inputName));
                }
                if ($(this).attr("type") == "label") {
                    $(this).attr("for", prefix + index1 + getInputName(inputName));
                }

            });
            $(this).attr('id', prefix + "_periodDiv" + index1);
        });
        console.log($('div.container-fluid').html());
    });

    $("button[class~='EDUCATIONadd_period_button']").click(function () {
        var prefix = 'EDUCATION';
        var id = $(this).attr('id');
        var companyIndex = id.replace(prefix + "add_period_button", "");
        var parent = $(this).parent().parent();
        var divId = $(this).attr("data-company-div");

        // Add period block
        $(parent).after(getNewPeriod(prefix, companyIndex, '0'));

        var selectorPeriods = 'div[id^=' + "'" + prefix + "_periodDiv" + "'" + ']';
        var list1 = $('#' + divId).children(selectorPeriods);
        list1.each(function (index1) {
            var childId = $(this).attr('id');
            var childName = $(this).attr('name');

            var str = 'div#' + childId + ' input';
            var list2 = $(str);
            list2.each(function (index2) {
                var inputName = $(this).attr('name');
                if ($(this).attr("type") == "input") {
                    $(this).attr("name", prefix + index1 + getInputName(inputName));
                    $(this).attr("id", prefix + index1 + getInputName(inputName));
                }
                if ($(this).attr("type") == "label") {
                    $(this).attr("for", prefix + index1 + getInputName(inputName));
                }

            });
            $(this).attr('id', prefix + "_periodDiv" + index1);
        });
        console.log($('div.container-fluid').html());
    });


    // HTML block for new period
    function getNewPeriod(sectionType, indexCompany, indexPeriod) {
        var block =
            "\n" + '<div id="' + sectionType + '_periodDiv' + indexPeriod + '">' + "\n" +
            '<div class="form-group row">' + "\n" +
            '<div class="col-md-2">' + "\n" +
            '<label for="' + sectionType + indexCompany + 'startDate" ' + "\n" +
            'class="col-form-label">Дата начала:</label>' + "\n" +
            '<input type="text" name="' + sectionType + indexCompany + 'startDate" ' + "\n" +
            'id="' + sectionType + indexCompany + 'startDate" ' + "\n" +
            'class="form-control" ' + "\n" +
            'value=""' + 'required> ' + "\n" +
            '</div>' + "\n" +
            '<div class="col-md-2">' + "\n" +
            '<label for="' + sectionType + indexCompany + 'endDate" ' + "\n" +
            'class="col-form-label">Дата окончания:</label>' + "\n" +
            '<input type="text" name="' + sectionType + indexCompany + 'endDate" ' + "\n" +
            'id="' + sectionType + indexCompany + 'endDate" ' + "\n" +
            'class="form-control" ' + "\n" +
            'value="Сейчас"' + ' required>' + "\n" +
            '</div>' + "\n" +
            '<div class="col-md-4">' + "\n" +
            '<label for="' + sectionType + indexCompany + 'text" ' + "\n" +
            'class="col-form-label">Позиция:</label>' + "\n" +
            '<input type="text" name="' + sectionType + indexCompany + 'text" ' + "\n" +
            'id="' + sectionType + indexCompany + 'text" ' + "\n" +
            'class="form-control" ' + "\n" +
            'value="" required>' + "\n" +
            '</div>' + "\n";

        if (sectionType === 'EXPERIENCE') {
            block = block +
                '<div class="col-md-6">' + "\n" +
                '<label for="' + sectionType + indexCompany + 'description"' + "\n" +
                'class="col-form-label">Описание:</label> ' + "\n" +
                '<textarea name="' + sectionType + indexCompany + 'description" ' + "\n" +
                'id="' + sectionType + indexCompany + 'description" ' + "\n" +
                'class="form-control" rows="8"></textarea></div>' + "\n" +
                '</div>' + "\n";
        }
        block += '</div>' + "\n";
        return block;
    }

    function getInputName(str) {
        switch (true) {
            case str.indexOf("startDate") > 0:
                return "startDate";
            case str.indexOf("endDate") > 0:
                return "endDate";
            case str.indexOf("text") > 0:
                return "text";
            case str.indexOf("description") > 0:
                return "description";
        }
    }
});