const getProducts = () => {
    return fetch('/api/products')
        .then(response => response.json())
        .catch(error => console.log(error));
}

const createHtmlElementFromString = (htmlElementAsString) => {
    let myElement = document.createElement('div');
    myElement.innerHTML = htmlElementAsString.trim();
    return myElement.firstChild;
}

const createProductHtmlComponent = (product) => {
    const template = `
        <li class="product">
            <h3>${product.title}</h3>
            <div class="product__image-container">
                <img src="${product.mediaPath}"/>
            </div>
            <span class="product__price">${product.price}</span>
            <button
                class="product__add-to-basket"
                data-product-id="${product.id}"
            >
                Add to basket
            </button>
        </li>
    `;

    return createHtmlElementFromString(template);
}

const appendProductsToList = (listEl, productsAsHtml) => {
    productsAsHtml.forEach(el => listEl.appendChild(el));

    return productsAsHtml;
}

const refreshCurrentOffer = () => {
    const basketEl = document.querySelector('.basket');
    fetch('/api/current-offer')
        .then(r => r.json())
        .then(offer => {
            basketEl.querySelector('.basket__total').innerText = `${offer.total} PLN`;
            basketEl.querySelector('.basket__items-count').innerText = `${offer.linesCount} Items`;
        })
}

const initializeAddToBasketHandler = (productHtmlEl) => {
    const addToBasketBtn = productHtmlEl.querySelector('.product__add-to-basket');

    addToBasketBtn.addEventListener('click', (event) => {
        const productId = event.target.getAttribute('data-product-id');
        const url = `/api/add-product/${productId}`;

        fetch(url, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
        })
            .then(() => refreshCurrentOffer())
            .catch(err => console.log(err));
    })
}

const initializeAddToBasketHandling = (productsAsHtmlEl) => {
    productsAsHtmlEl.forEach(el => initializeAddToBasketHandler(el))
    return productsAsHtmlEl;
}

const handleCheckout = () => {
    console.log('lets do some checkout steps');
}

const showCheckout = () => {
   const checkoutLayer = document.querySelector('.checkout__layer');
   checkoutLayer.style.display = 'block';
}

const closeCheckout = () => {
    console.log('close');
   const checkoutLayer = document.querySelector('.checkout__layer');
   checkoutLayer.style.display = 'none';
}

(() => {
    console.log("It works :):)");
    const productsList = document.getElementById('products');
    getProducts()
        .then(productsAsObjects => productsAsObjects.map(createProductHtmlComponent))
        .then(productsAsHtmlEl => initializeAddToBasketHandling(productsAsHtmlEl))
        .then(productsAsHtmlEl => appendProductsToList(productsList, productsAsHtmlEl))
        .then(products => console.log(products))
    ;

    refreshCurrentOffer();

    const checkoutBtn = document.querySelector('.basket__checkout');
    checkoutBtn.addEventListener('click', showCheckout);

    const closeCheckoutBtn = document.querySelector('.checkout__close');
    closeCheckoutBtn.addEventListener('click', closeCheckout);

    const checkoutForm = document.querySelector('.checkout__form');

    checkoutForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = new FormData(checkoutForm);
        const data = {};

        formData.forEach((value, key) => (data[key] = value));

        fetch(checkoutForm.getAttribute('action'), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(reservationDetails => {
                checkoutForm.reset();
                window.location.href = reservationDetails.paymentUrl;
            });

    });
})();
