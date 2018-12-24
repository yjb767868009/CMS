var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#klassSeminar").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/klassSeminar/1', function (KlassSeminarRun) {
            var KlassSeminarRun = JSON.parse(KlassSeminarRun.body);
            showKlassSeminar(KlassSeminarRun.questions, KlassSeminarRun.attendances, KlassSeminarRun.selectQuestion);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/1/hello", {}, JSON.stringify({'name': $("#name").val(), 'say': $("#say").val()}));
}

function askQuestion() {
    stompClient.send("/app/1/question", {}, JSON.stringify({
        'student': {"studentId": 1},
        'attendance': {"attendanceId": 1},
        'klassSeminar': {"klassSeminarId": 1}
    }));
}

function getQuestion() {
    stompClient.send("/app/1/getQuestion", {}, JSON.stringify({
        'klassSeminarId': 1,
        'selectAttendance': {"attendanceId": 1}
    }));
}

function showKlassSeminar(questions, attendances, selectQuestion) {
    console.log(questions);
    console.log(attendances);
    console.log(selectQuestion);
    //$("#klassSeminar").append("<tr><td>" + message + "</td></tr>");
    if (selectQuestion == null) {
        $("#klassSeminar").html("<tr><td>" + questions[0].name + ":" + attendances[1].team.teamName + "</td></tr>");
    } else {
        $("#klassSeminar").html("<tr><td>" + questions[0].name + ":" + attendances[1].team.teamName + ":" + selectQuestion.name + "</td></tr>");
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
    $("#question").click(function () {
        askQuestion();
    });
    $("#getQuestion").click(function () {
        getQuestion();
    })
});