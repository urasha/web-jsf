let svgPointData = {
    x: NaN,
    y: NaN
};

function handleSvg(event) {
    const r = parseInt(document.querySelector(".btn-small.active").value);

    const rValues = [1, 2, 3, 4, 5];
    if (isNaN(r) || !rValues.includes(r)) {
        showNotification("Задано некорректное R!")
        return;
    }

    const svg = event.currentTarget;
    const rect = svg.getBoundingClientRect();
    const svgWidth = rect.width;
    const svgHeight = rect.height;

    const x = event.clientX - rect.left - svgWidth / 2;
    const y = svgHeight / 2 - (event.clientY - rect.top);

    svgPointData['x'] = x;
    svgPointData['y'] = y;

    const scaledX = ((x / (svgWidth / 2)) * r * 1.5);
    const scaledY = ((y / (svgHeight / 2)) * r * 1.5);

    document.querySelector(".x-container input").value = scaledX.toFixed(2);
    document.querySelector('.input').value = scaledY.toFixed(2);

    document.querySelector('.btn-submit').click();

    svgPointData['x'] = NaN;
    svgPointData['y'] = NaN;
}

function showNotification(message) {
    const notification = document.getElementById('r-notification');
    notification.textContent = message;
    notification.classList.remove('hidden');
    notification.classList.add('visible');

    setTimeout(() => {
        notification.classList.remove('visible');
        notification.classList.add('hidden');
    }, 3000);
}

function handleFormSubmit() {
    const x = parseFloat(document.querySelector(".x-container input").value);
    const y = parseFloat(document.querySelector('.input').value);
    const r = parseInt(document.querySelector(".btn-small.active").value);

    const messages = document.querySelector('.error-messages');

    if (messages) {
        messages.innerHTML = '';
    }

    let isValid = true;
    let errorMessages = [];

    if (isNaN(x) || x < -2 || x > 2) {
        isValid = false;
        errorMessages.push('X должно быть выбрано из списка от -2 до 2!');
    }

    if (isNaN(y) || y < -5 || y > 5) {
        isValid = false;
        errorMessages.push('Y должно быть числом в диапазоне от -5 до 5!');
    }

    if (isNaN(r) || ![1, 2, 3, 4, 5].includes(r)) {
        isValid = false;
        errorMessages.push('Задано некорретное R!')
    }

    if (!isValid) {
        errorMessages.forEach(message => {
            const errorElement = document.createElement('p');
            errorElement.textContent = message;
            errorElement.style.color = 'red';
            messages.appendChild(errorElement);
        });
        return;
    }

    if (!isNaN(svgPointData['x']) && !isNaN(svgPointData['y'])) {
        drawPointOnSvg();
    }
}

function changeX() {
    document.querySelector(".x-container input").value = document.querySelector('.menu').value;
}

function drawPointOnSvg() {
    const svg = document.querySelector('svg');
    const point = document.createElementNS("http://www.w3.org/2000/svg", "circle");

    point.setAttribute("cx", svg.getBoundingClientRect().width / 2 + svgPointData['x']);
    point.setAttribute("cy", svg.getBoundingClientRect().height / 2 - svgPointData['y']);
    point.setAttribute("r", 3);
    point.setAttribute("fill", "red");

    svg.appendChild(point);
}