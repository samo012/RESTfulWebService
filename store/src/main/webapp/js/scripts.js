const app = document.getElementById('root');

//const logo = document.createElement('img');
//logo.src = 'logo.png';

const container = document.createElement('div');
container.setAttribute('class', 'container');

app.appendChild(container);
var productId = 0;

var request = new XMLHttpRequest();
request.open('GET', 'http://localhost:8080/store/items', true);
request.onload = function () {

    //jQuery Search
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#root *").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });


    // Begin accessing JSON data here
    var data = JSON.parse(this.response);
    if (request.status >= 200 && request.status < 400) {
        data.forEach(item => {
            const card = document.createElement('div');
            card.setAttribute('class', 'card');

            const h1 = document.createElement('h1');
            h1.textContent = item.name;

            const h3 = document.createElement('h3');
            h3.textContent = "$"+item.salePrice;

            const p = document.createElement('p');
            p.textContent = item.shortDescription;


            const b = document.createElement('button');
            b.textContent = "Add to Cart";
            b.setAttribute("id", item.itemId);

            b.onclick = function addtocart(){
               checkCookie();
               addItem(b.getAttribute("id"));
            };

            container.appendChild(card);
            card.appendChild(h1);
            card.appendChild(h3);
            card.appendChild(p);
            card.appendChild(b);

        });

    } else {
        const errorMessage = document.createElement('marquee');
        errorMessage.textContent = `Error loading products`;
        app.appendChild(errorMessage);
    }
};

request.send();

function addItem(id) {
    var addItem = new XMLHttpRequest();
    var user = getCookie("user");
    addItem.open('POST', 'http://localhost:8080/store/carts?productId=' + id + '&username=' + user, true);
    addItem.onload = function () {
        var data = JSON.parse(this.response);

        const user = document.createElement('h1');
        user.textContent = data.username;
        app.appendChild(user);
    };
    addItem.send();
}


function listView() {
    var x = document.getElementsByClassName("card");
    for (var i = 0; i < x.length; i++) {
        x[i].style.flex = "100%";
    }

}

function gridView() {
    var x = document.getElementsByClassName("card");
    for (var i = 0; i < x.length; i++)
        x[i].style.flex = "1 1 calc(33% - 2rem)";
}

var bcontainer = document.getElementById("btnContainer");
var btns = bcontainer.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function(){
        var current = bcontainer.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        this.className += " active";
    });
}

var uname = document.getElementById('usr');
function setCookie(user) {
        document.cookie = user + "=" + uname.value + "; path=/";
}

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

function checkCookie() {
    var user=getCookie("user");
    if (user != "") {
        window.location.href = "cart.html";
    } else {
        document.getElementById('login').style.display = "block";
        setCookie("user");

    }
}

