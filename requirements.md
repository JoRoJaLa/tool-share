# System Requirements

## Vision

### What is the vision of this product?

- The vision of Tool Share is to create an app which people can use to borrow tools that they need from others who are not currently using them. Users will be able to search for specific tools within a specified city (within the USA).

### What pain point does this project solve?

- The pain point addressed by the project is the necessity of buying or renting tools everytime you need them, even if you are only going to use the tool once or rarely. The app saves users space and money as they don’t need to store tools permanently in their homes that they would have otherwise bought or rented.

### Why should we care about your product?

- Tool Share simultaneously solves pain points experienced regularly by everyday people. Users will be able to avoid buying and storing tools they will only use once, and allow those who have tools collecting dust to be used by those who actually need them. In doing so, Tool Share saves users money and storage space, and promotes environmental sustainability, as the same tools can be shared communally as opposed to each home having its own set of tools.

## Scope (In/Out)

### IN - What will your product do

- Users will be able to create a profile and login into it
- Users will be able to search for different kinds of tools in a specified city and view them by distance in relation to their current location
- Users can create a loaner tool listing
- Users will be able borrow tools from other users
  
### OUT - What will your product not do

- It’s not a marketplace
- Instructions to use the tools will not be provided
- Services will not be provided

## Minimum Viable Product vs

### What will your MVP functionality be?

- User can create an account
- Users can search for a preset list of tools and be provided with a list of current tools which are filtered by the closest location to the user.
- User can create a loaner tool listing
- Users can borrow tools from other users.
- Users will be provided the distance between each user in order to establish a meeting point.

## Stretch

### What stretch goals are you going to aim for?

- Test implementation to populate a database with users and tools.
- Users can chat with each other to ask questions or find a meeting location.
- Users are provided with suggestions on meetup locations.
- Users can leave reviews and ratings on different users accounts.
- Users can upload images of their tools.
- Filter by other parameters for tool sort.
- Transaction exchange between objects.
  
## Functional Requirements

- List the functionality of your product. This will consist of tasks such as the following:
  
1. An admin can create and delete user accounts
2. A user can update their profile information
3. A user can search all of the products in the inventory

### Data Flow

- Describe the flow of data in your application. Write out what happens from the time the user begins using the app to the time the user is done with the app. Think about the “Happy Path” of the application. Describe through visuals and text what requests are made, and what data is processed, in addition to any other details about how the user moves through the site.
  
- When a user creates a new user profile, a new user object is instantiated and saved to the database. When a user logs into their profile, a GET request is performed and the user profile data is pulled from the database. After retrieving the requested data, the user is redirected to the listings page where tools are listed. Users can fill out a search form and provide a tool name and current location. This will create a GET request which will access the database and pull all of the associated tool object instances into an arraylist. The arraylist will then be sorted by distance to the user’s input location. When a user loans a tool, a shallow copy of the tool’s id will be added to the user’s property arraylist, and upon returning the loaned tool the copy of the id will be removed from the user’s arraylist of loaned tools.
  
## Non-Functional Requirements (301 & 401 only)

- Tool Share will be tested for usability requirements: This includes an intuitive UI where the user can interact with the application easily and the application has expected exceptions in the case of bad input. The user will be able to navigate and access all functionalities easily, including creating and signing into their account, searching for tools, creating listings for tools and borrowing tools from others.

- Tool Share will also have a testability requirement. Edge cases with user inputs will need to be tested and planned for, along with edge cases within lending the same tool object instances to different/same user objects. Moreover, the system of tracking many to one and vice versa relationships will need to be tested with object instances.

Links:

https://excalidraw.com/#json=U5qevwc_vdYs6gbXnA-x5,XbvMc1eOKD6Pchc2oPiy3A
