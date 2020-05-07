/**
 * 시작 - 종료 표시 달력의 기본 설정 정의
 */
$(document).ready(function() {
    $("#start_date").datepicker({
        dateFormat: "yy-mm-dd",
        showMonthAfterYear: true,
        changeMonth: true,
        changeYear: true
    });
    $("#end_date").datepicker({
        dateFormat: "yy-mm-dd",
        showMonthAfterYear: true,
        changeMonth: true,
        changeYear: true
    });
    
    // Mobile에서 Key Pad 나타남 방지를 위해 Read Only로 설정.
    $("#start_date").attr("readonly", "true");
    $("#end_date").attr("readonly", "true");

    // 날자 선택 구간 설정. (종료일 이후 보다 선택일이 늦을 수 없다.)
    $("#start_date").datepicker("option", "minDate", "2015-01-01");
    $("#start_date").datepicker("option", "maxDate", $("#end_date").val());
    $("#start_date").datepicker("option", "onClose", function( selectedDate ) {
        $("#end_date").datepicker("option", "minDate", selectedDate);
    });
    $("#end_date").datepicker("option", "maxDate", "<%=end_date%>");
    $("#end_date").datepicker("option", "minDate", $("#start_date").val());
    $("#end_date").datepicker("option", "onClose", function( selectedDate ) {
        $("#start_date").datepicker("option", "maxDate", selectedDate);
    });
});
