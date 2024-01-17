var hotelId = new URLSearchParams(window.location.search).get('hotelId');

// build UI

var hotelName = document.createElement("h2");


// Now fetch to reservation service for our hotel if we click button



function acquireButtonClicked(){

    // from the form take dates only

    const fromDate = document.getElementById('fromDate').value;
    const toDate = document.getElementById('toDate').value;

    var url = "http://192.168.64.102:8082/api/v1/reservation/" + hotelId + "/" + fromDate + "/" + toDate;
    fetch(url)
    .then(response => {
        if (!response.ok)
        {
            throw new Error("Error trying to send API call to server.")
        }
        return response.json();
    })
    .then(data => {
        displayData(data);
    })
    .catch(error => {
        console.error("Fetch error:", error);
    });

}

function displayData(roomData){
    const roomContainer = document.getElementById('roomContainer');

    roomData.forEach(room => {
      const roomDiv = document.createElement('div');
      roomDiv.classList.add('room');

      const roomNumber = document.createElement('p');
      roomNumber.textContent = `Room Number: ${room.roomNumber}`;
      roomDiv.appendChild(roomNumber);

      const reserveButton = document.createElement('button');
      reserveButton.textContent = 'Reserve';
      reserveButton.addEventListener('click', () => reserveRoom(room.id));
      roomDiv.appendChild(reserveButton);

      roomContainer.appendChild(roomDiv);
    });



}

function reserveRoom(roomId){
    const url = 'http://192.168.64.102:8082/api/v1/reservation';
    const data = {
    hotelId: hotelId,
    startDate: document.getElementById('fromDate').value,
    endDate: document.getElementById('toDate').value,
    name: document.getElementById('name').value,
    surname: document.getElementById('surname').value,
    email: document.getElementById('email').value,
    roomId : roomId
    };

    fetch(url, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json', // Set the content type to JSON
        // Add any other headers as needed
    },
    body: JSON.stringify(data), // Convert the data to JSON string
    })
    .then(response => response.json()) // Parse the response as JSON
    .then(result => {
        // Handle the result
        console.log(result);
    })
    .catch(error => {
        // Handle errors
        console.error('Error:', error);
    });

    handleReserveComplete();

}

function handleReserveComplete(){

    document.getElementById('form').style.display = 'none';
    document.getElementById('roomContainer').style.display = 'none';
    document.getElementById('title').style.display = 'none';

    var newTitle = document.createElement("h1");
    newTitle.textContent = "Success!"

    var text = document.createElement("p");
    text.textContent = "Your reservation has been successfully placed!\nYou can now close this page.";

    var closeDiv = document.getElementById('close');
    closeDiv.append(newTitle);
    closeDiv.append(text);

}