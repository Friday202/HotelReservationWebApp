// app.js
document.addEventListener("DOMContentLoaded", function () {
    const app = document.getElementById("app");
    const hotelList = document.getElementById("hotelList");
    const hotelDetails = document.getElementById("hotelDetails");

    function showHotelList() {
        hotelList.style.display = "block";
        hotelDetails.style.display = "none";
    }

    function showHotelDetails(hotel) {
        hotelList.style.display = "none";
        hotelDetails.style.display = "block";
        hotelDetails.innerHTML = `
            <h2>${hotel.name}</h2>
            <p>Location: ${hotel.location}</p>
            <!-- Add more details as needed -->
            <button onclick="showHotelList()">Back to List</button>
        `;
    }

    fetch("http://localhost:8080/api/v1/catalog")
        .then(response => response.json())
        .then(data => {
            data.forEach(hotel => {
                const listItem = document.createElement("li");
                listItem.classList.add("hotelListItem");
                listItem.textContent = `${hotel.name} is at location ${hotel.location}.`;

                const button = document.createElement("button");
                button.textContent = "Book Now";
                button.addEventListener("click", function () {
                    showHotelDetails(hotel);
                });

                listItem.appendChild(button);
                hotelList.appendChild(listItem);
            });
        })
        .catch(error => console.error("Error fetching hotels:", error));
});
