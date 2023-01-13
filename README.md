# SpaceX
The app shows information about SpaceX company, its rocket launches, news, etc. Uses open-source API: https://github.com/r-spacex/SpaceX-API

## Features
* Information about the company in expandable cards
* List of recent news. Click opens its full text in a browser
* List of launches with pagination. Click opens the bottom sheet with detailed information
* Filter launches by time (all, past, or future)
* Search launches by name
* Deep links + shortcuts
* Splash screen

## Implementation
Application consist of 2 modules:
* `app` - UI layer, designed as MVI architecture pattern
* `core` - data layer, designed as Repository pattern


![image](https://user-images.githubusercontent.com/44781809/212377110-c18531bc-b31a-4cc7-a144-1c30d1616a8d.png)

### Techonologies
* Dagger Hilt
* Coroutines
* Retrofit2
* Room
* Coil
* Navigation component
* Data and view binding

### Demo


https://user-images.githubusercontent.com/44781809/212374778-6e0ddcff-e42b-4f48-bc85-bb01e12c403a.mp4

