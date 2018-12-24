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
            showKlassSeminar(KlassSeminarRun.questions, KlassSeminarRun.attendances, KlassSeminarRun.nowAttendance, KlassSeminarRun.newQuestion, KlassSeminarRun.selectQuestion, KlassSeminarRun.message);
        });
    });
}

function join() {

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/1/join", {}, {});
}

function askQuestion() {
    stompClient.send("/app/1/question", {}, JSON.stringify({
        'student': {"studentId": 1},
        'attendance': {"attendanceId": 1},
        'klassSeminar': {"klassSeminarId": 1}
    }));
}

function getQuestion() {
    stompClient.send("/app/1/getQuestion", {}, {});
}

function showKlassSeminar(questions, attendances, nowAttendance, newQuestion, selectQuestion, message) {
    console.log(questions);
    console.log(attendances);
    console.log(nowAttendance);
    console.log(newQuestion);
    console.log(selectQuestion);
    console.log(message);
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