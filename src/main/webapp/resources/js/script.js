let svgPointData = {
    x: NaN,
    y: NaN,
    r: NaN,
    inside: false
};

const svg = document.querySelector('svg');
const rect = svg.getBoundingClientRect();
const svgWidth = rect.width;
const svgHeight = rect.height;

function handleSvg(event) {
    const r = parseInt(document.querySelector(".btn-small.active").value);

    if (!checkR(r)) {
        showNotification("Задано некорректное R!")
        return;
    }

    const x = event.clientX - rect.left - svgWidth / 2;
    const y = svgHeight / 2 - (event.clientY - rect.top);

    setSvgPointData(x, y, r);

    const scaledX = getScaledX(x, r);
    const scaledY = getScaledY(y, r);

    if (!checkX(scaledX) || !checkY(scaledY)) {
        showNotification("Некорретные координаты для выбранного R!");
        return;
    }

    document.querySelector(".x-container input").value = scaledX.toFixed(2);
    document.querySelector('.input').value = scaledY.toFixed(2);

    document.querySelector('.btn-submit').click();

    clearSvgPointData();
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

function setSvgPointData(x, y, r) {
    svgPointData['x'] = x;
    svgPointData['y'] = y;
    svgPointData['r'] = r;
}

function clearSvgPointData() {
    svgPointData['x'] = NaN;
    svgPointData['y'] = NaN;
    svgPointData['r'] = NaN;
    svgPointData['inside'] = false;
}

function getScaledX(x, r) {
    return ((x / (svgWidth / 2)) * r * 1.5);
}

function getScaledY(y, r) {
    return ((y / (svgHeight / 2)) * r * 1.5);
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

    if (!checkX(x)) {
        isValid = false;
        errorMessages.push('X должен быть от -2 до 2!');
    }

    if (!checkY(y)) {
        isValid = false;
        errorMessages.push('Y должно быть от -5 до 5!');
    }

    if (isNaN(r) || ![1, 2, 3, 4, 5].includes(r)) {
        isValid = false;
        errorMessages.push('R должно быть одним из представленных вариантов!')
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

function checkX(x) {
    return !(isNaN(x) || x < -2 || x > 2);
}

function checkY(y) {
    return !(isNaN(y) || y < -5 || y > 5);
}

function checkR(r) {
    const rValues = [1, 2, 3, 4, 5];
    return !(isNaN(r) || !rValues.includes(r));
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
    point.setAttribute("fill", isInsideFigures() ? "green" : "red");

    svg.appendChild(point);
}

function isInsideFigures() {
    const x = getScaledX(svgPointData['x'], svgPointData['r']);
    const y = getScaledY(svgPointData['y'], svgPointData['r']);
    const r = svgPointData['r'];

    const isInRect = (x >= -r / 2 && x <= 0) && (y >= -r && y <= 0);

    const isInCircle = ((x * x + y * y) <= (r / 2 * r / 2)) && (x >= 0 && y >= 0);

    const withinXBounds = (0 <= x) && (x <= r);
    const withinYBounds = (y >= -r / 2) && (y <= 0);
    const aboveHypotenuse = (y >= 0.5 * x - r / 2);
    const isInTriangle = withinXBounds && withinYBounds && aboveHypotenuse

    return isInRect || isInCircle || isInTriangle;
}