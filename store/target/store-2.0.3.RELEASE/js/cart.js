const app = document.getElementById('root');
const list = document.getElementById('list');
var cartId = 0;
var prices = 0;

function getUser(cname) {
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

var username = getUser("user");


    var getCart = new XMLHttpRequest();
    getCart.open('GET', 'http://localhost:8080/store/carts?username='+username, true);
    getCart.onload = function () {
    var data = JSON.parse(this.response);
    if (getCart.status >= 200 && getCart.status < 400) {
        cartId = data.cartId;
        data.items.forEach(item => {
            const product = document.createElement('tr');
            product.setAttribute('id', item.productId);

            const name = document.createElement('td');
            name.textContent = item.productName;

            const price = document.createElement('td');
            price.textContent = item.salePrice;

            list.appendChild(product);
            product.appendChild(name);
            product.appendChild(price);

            prices+=item.salePrice;

        });

    } else {
        location.reload();
    }

        var subtotal = document.createElement('h3');
        var total = document.createElement('h3');

        subtotal.textContent = "subtotal: $"+ prices.toFixed(2);
        var tax = prices + prices*0.08;
        total.textContent = "total: $"+tax.toFixed(2);
        app.appendChild(subtotal);
        app.appendChild(total);
};
getCart.send();

function goBack() {
    window.location.href = "index.html";

}
function checkout() {
    var purchase = new XMLHttpRequest();
    purchase.open('PUT', 'http://localhost:8080/store/carts/purchase/'+cartId, true);
    purchase.send();
    purchase.onload = function () {
        alert("Items successfully purchased");
        window.location.href = "index.html";

    };

}


