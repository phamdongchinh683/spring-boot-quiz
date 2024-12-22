$(document).ready(function() {
    $("#submit").click(function() {
        debugger;
        var selectedOption = $("input[name='question1']:checked").val();
        var questionId = $("#question_id").val();
        if (!selectedOption) {
            alert("Please select an option.");
            return;
        }
        var xhr = new XMLHttpRequest();
        var url = "/checkans?question1=" + encodeURIComponent(selectedOption) + "&question_id=" + encodeURIComponent(questionId);
        xhr.open("GET", url, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    window.location.href = xhr.responseText;
                } else {
                    console.error("AJAX error: " + xhr.status + " - " + xhr.statusText);
                }
            }
        };
        xhr.send();
    });
});