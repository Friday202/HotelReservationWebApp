function reservationButtonClicked(hotelId)
{
    var url = 'reservation.html?hotelId=' + hotelId;

    // Open the new window or tab with the URL
    window.open(url, '_blank');
}

function createNewHotelExtended(id, name, location, rating, description, coordinates, amentities,
     maxTemp, minTemp, conditions, dates)
{
    var oneHotel = document.getElementById("hotelView");
    if (oneHotel)
    {
        // Clear the div if from before is not empty
        while (oneHotel.firstChild)
        {
            oneHotel.removeChild(oneHotel.firstChild);
        }

        // Create a title
        var hotelName = document.createElement("h2");
        hotelName.textContent = name;

        // Create a paragraph for location
        var hotelLocation = document.createElement("p");
        hotelLocation.textContent = "Location: " + location;

        // Create a paragraph for user rating
        var hotelUserRating = document.createElement("p");
        hotelUserRating.textContent = "User rating: " + rating;

        // Create a paragraph for descripton
        var hotelDescription = document.createElement("p");
        hotelDescription.textContent = description;

        // Create a button for details
        var bookButton = document.createElement("button");
        bookButton.textContent = "Book";

        bookButton.addEventListener('click', function(){
            reservationButtonClicked(id);
        });

        var returnButton = document.createElement("button");
        returnButton.textContent = "Return";

        returnButton.addEventListener('click', function() {
            buttonDetailsClicked(-1, true);
        });

        //Append all to div
        oneHotel.append(hotelName);
        oneHotel.append(hotelLocation);
        oneHotel.append(hotelUserRating);
        oneHotel.append(hotelDescription);

        oneHotel.append(document.createElement("p").textContent = "Offers: ");

        // Add amentities
        amentities.forEach(amentity => {
            var amentityList = document.createElement("li");
            amentityList.textContent = amentity;
            oneHotel.append(amentityList);
        });

        // add Weather data
        var canvas = document.createElement("canvas");
        canvas.width = 600;
        canvas.height = 300;
        var ctx = canvas.getContext('2d');

        // Creating the chart

        const weatherChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: dates,
                datasets: [
                    {
                        label: 'Max Temp (°C)',
                        data: maxTemp.map(temp => parseFloat(temp)),
                        borderColor: 'rgba(255, 99, 132, 1)',
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    },
                    {
                        label: 'Min Temp (°C)',
                        data: minTemp.map(temp => parseFloat(temp)),
                        borderColor: 'rgba(54, 162, 235, 1)',
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    },
                    {
                        label: 'Weather Conditions',
                        data: conditions.map(condition => condition),
                        yAxisID: 'weatherAxis',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    }
                ]
            },
            options: {
                scales: {
                    tempAxis: {
                        type: 'linear',
                        position: 'left',
                        beginAtZero: false
                    },
                },
                plugins: {
                    annotation: {
                        drawTime: 'afterDatasetsDraw',
                        annotations: dates.map((date, index) => ({
                            type: 'line',
                            mode: 'vertical',
                            scaleID: 'x',
                            value: index,
                            borderColor: 'rgba(0, 0, 0, 0)',
                            label: {
                                content: conditions[index],
                                enabled: true,
                                position: 'bottom'
                            }
                        }))
                    }
                }
            }
        });

        oneHotel.append(canvas);
        oneHotel.append(bookButton);
        oneHotel.append(returnButton);

    }
}

function displayOneHotelExtended(hotel)
{
    // hotel has all json data
    createNewHotelExtended(hotel.id, hotel.name, hotel.location, hotel.userRating,
        hotel.description, hotel.coordinates, hotel.amenities, hotel.maxTemp,
        hotel.minTemp, hotel.weatherConditions, hotel.dates);
}

function getOneHotelData(hotelId)
{
    var url = "http://localhost:8080/api/v1/catalog/" + String(hotelId); 
    fetch(url)
    .then(response => {
        if (!response.ok)
        {
            throw new Error("Error trying to send API call to server.")
        }
        return response.json(); 
    })
    .then(data => {
        displayOneHotelExtended(data); 
    })
    .catch(error => {
        console.error("Fetch error:", error);
    });
}

function buttonDetailsClicked(hotelId, showCatalog)
{         
    if (showCatalog)
    {
        // Show catalog
        var hotelsContainer = document.getElementById("hotelsContainer"); 
        hotelsContainer.style.display = 'block'; 

        // Hide one hotel
        var oneHotel = document.getElementById("hotelView"); 
        oneHotel.style.display = 'none'; 
    }
    else
    {
        // Hide catalog
        var hotelsContainer = document.getElementById("hotelsContainer"); 
        hotelsContainer.style.display = 'none'; 

        var oneHotel = document.getElementById("hotelView"); 
        oneHotel.style.display = 'block'; 

        getOneHotelData(hotelId); 
    }
    
    
}

function createNewHotel(name, location, rating, id)
{
    // Get reference to the list of hotels 
    const hotelList = document.getElementById("hotelList");
    if (hotelList)
    {
        // Create new list element and div 
        var newHotelListElement = document.createElement("li");     
        var newHotelDiv = document.createElement("div"); 

        // Create a title 
        var hotelName = document.createElement("h3");
        hotelName.textContent = name; 
    
        // Create a paragraph for location 
        var hotelLocation = document.createElement("p");
        hotelLocation.textContent = "Location: " + location; 

        // Create a paragraph for user rating
        var hotelUserRating = document.createElement("p");
        hotelUserRating.textContent = "User rating: " + rating; 

        // Create a button for details 
        var hotelButton = document.createElement("button");
        hotelButton.textContent = "Details"; 

        hotelButton.addEventListener('click', function() {            
            buttonDetailsClicked(id, false); 
        });

        //Append all to div 
        newHotelDiv.append(hotelName); 
        newHotelDiv.append(hotelLocation);
        newHotelDiv.append(hotelUserRating); 
        newHotelDiv.append(hotelButton); 

        // Add hotel div to list 
        newHotelListElement.append(newHotelDiv); 

        // Add new list element to unordered list 
        hotelList.appendChild(newHotelListElement);     
    }
}

function addAndCreateNewHotel(data)
{   
    data.forEach(hotel => {
        // Create hotel html representation
        // we get name, location and user rating 
        createNewHotel(hotel.name, hotel.location, hotel.userRating, hotel.id); 

    });
}

// Begin script 
fetch("http://localhost:8080/api/v1/catalog")
.then(response => {
    if (!response.ok)
    {
        throw new Error("Error trying to send API call to server.")
    }
    return response.json(); 
})
.then(data => {
    addAndCreateNewHotel(data); 
})
.catch(error => {
    console.error("Fetch error:", error);
});