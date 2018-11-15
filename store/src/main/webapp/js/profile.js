

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

const fn = document.getElementById('fn');
var fname = document.createElement('input');
fname.setAttribute("class","form-control");
fname.setAttribute("type","text");
fn.appendChild(fname);


const ln = document.getElementById('ln');
var lname = document.createElement('input');
lname.setAttribute("class","form-control");
lname.setAttribute("type","text");
ln.appendChild(lname);


const us = document.getElementById('usr');
var usr = document.createElement('input');
usr.setAttribute("class","form-control");
usr.setAttribute("type","text");
us.appendChild(usr);


const em = document.getElementById('email');
var email = document.createElement('input');
email.setAttribute("class","form-control");
email.setAttribute("type","text");
em.appendChild(email);


    var user = getCookie("user");

    var getUser = new XMLHttpRequest();
    getUser.open('GET', 'http://localhost:8080/store/customers/'+user, true);

    getUser.onload = function () {

        var data = JSON.parse(this.response);


        fname.value = data.fname;


        lname.value = data.lname;


        usr.value = data.username;


        email.value = data.email;


    };
    getUser.send();


function updateUser() {
    var fn = fname.value;
    var ln = lname.value;
    var usr = usr.value;
    var email = email.value;

    var updateUsr = new XMLHttpRequest();
    updateUsr.open('PUT', 'http://localhost:8080/store/customers/?fname='+fn+'&lname='+ln+'&username='+usr+'&email=' + email, true);
    updateUsr.send();
}