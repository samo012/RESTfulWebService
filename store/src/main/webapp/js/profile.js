

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

    var fn = document.getElementById('fn');
    var ln = document.getElementById('ln');
    var usr = document.getElementById('usr');
    var email = document.getElementById('email');
    var user = getCookie("user");

    var getUser = new XMLHttpRequest();
    getUser.open('GET', 'http://localhost:8080/store/customers/'+user, true);

    getUser.onload = function () {

        var data = JSON.parse(this.response);

        fn.value = data.fname;
        ln.value  = data.lname;
        usr.value = data.username;
        email.value  = data.email;

    };
    getUser.send();


function updateUser() {
    var fn = document.getElementById('fn').value;
    var ln = document.getElementById('ln').value;
    var usr = document.getElementById('usr').value;
    var email = document.getElementById('email').value;

    var updateUsr = new XMLHttpRequest();
    updateUsr.open('PUT', 'http://localhost:8080/store/customers/?fname='+fn+'&lname='+ln+'&username='+usr+'&email=' + email, true);
    updateUsr.send();
}