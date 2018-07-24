# Nudge Me

## App Description
Nudge-me is a location-based reminder, you can speacify a patricular location from the map, at which a notification can prompt.
A nudge simply refers to a note or a notice.

## Project Structure
The project is designed using Mvp design pattern along with dependency injection using Dagger2.
It consists of
- 3 Activities
- 1 Background service
- 1 Broadcast reciever



**MainActivity**

Is used to display the saved nudges with their details

**InputActivity**

Is where you can input the details of a new nudge except the location

**MapsActivity**

Is where you can input the location of a nudge from the map

**LocationService**

Is a background service to request location updates and prompt a notification when approaching a place where a nudge is selected at

**NotifReciever**

Is a broadcast reciever to handle the click on notifications


## Special features in the app
- RecyclerView swipe-to-delete with undo option
- Animation for adding and removing item
- Animation of floating action button and other buttons

## Screenshots of the app

### MainActivity

![Alt text](/app/src/main/res/drawable/screen1.jpg?raw=true "MainActivity")


### InputActivity

![Alt text](/app/src/main/res/drawable/screen2.jpg?raw=true "InputActivity")

### MapsActivity

![Alt text](/app/src/main/res/drawable/screen3.jpg?raw=true "MapsActivity")
![Alt text](/app/src/main/res/drawable/screen4.jpg?raw=true "MapsActivity")
