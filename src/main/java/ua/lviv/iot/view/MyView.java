package ua.lviv.iot.view;

import ua.lviv.iot.controller.*;
import ua.lviv.iot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {
    @Autowired
    private AirportController airportController;

    @Autowired
    private UserController userController;

    @Autowired
    private PlaneController planeController;

    @Autowired
    private AirlineController airlineController;

    @Autowired
    private FlightController flightController;

    final Map<String, String> menu;
    final Map<String, Printable> methodsMenu;
    final Scanner input = new Scanner(System.in);
    final Airport nullAirport = new Airport(null, null);
    final User nullUser = new User(null, null, null, null);
    final Plane nullPlane = new Plane(null, null, null);
    final Airline nullAirline = new Airline(null, null, null);
    final Flight nullFlight = new Flight(null, null, null, null);

    public MyView() {
        menu = new LinkedHashMap<>();

        menu.put("a", "  a - Select all tables");

        menu.put("1", "   1 - Table: Airports");
        menu.put("11", "  11 - Create airport");
        menu.put("12", "  12 - Update airport");
        menu.put("13", "  13 - Delete from airports");
        menu.put("14", "  14 - Find all airports");
        menu.put("15", "  15 - Find airport by ID");
        menu.put("16", "  16 - Find airport by name");

        menu.put("2", "   2 - Table: Users");
        menu.put("21", "  21 - Create user");
        menu.put("22", "  22 - Update user");
        menu.put("23", "  23 - Delete from users");
        menu.put("24", "  24 - Find all users");
        menu.put("25", "  25 - Find user by ID");
        menu.put("26", "  26 - Find user by name");
        menu.put("27", "  27 - Find user by email");

        menu.put("3", "   3 - Table: Planes");
        menu.put("31", "  31 - Create plane");
        menu.put("32", "  32 - Update plane");
        menu.put("33", "  33 - Delete from planes");
        menu.put("34", "  34 - Find all planes");
        menu.put("35", "  35 - Find plane by ID");

        menu.put("4", "   4 - Table: Airlines");
        menu.put("41", "  41 - Create airline");
        menu.put("42", "  42 - Update airline");
        menu.put("43", "  43 - Delete from airlines");
        menu.put("44", "  44 - Find all airlines");
        menu.put("45", "  45 - Find airline by ID");
        menu.put("46", "  46 - Find airline by name");
        menu.put("47", "  47 - Find all planes of airline by airlineID");

        menu.put("5", "   5 - Table: Flights");
        menu.put("51", "  51 - Create flight");
        menu.put("52", "  52 - Update flight");
        menu.put("53", "  53 - Delete from flights");
        menu.put("54", "  54 - Find all flights");
        menu.put("55", "  55 - Find flight by ID");
        menu.put("56", "  56 - Find flight by name");

        menu.put("q", "  q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("a", this::selectAllTables);

        methodsMenu.put("11", this::createAirport);
        methodsMenu.put("12", this::updateAirport);
        methodsMenu.put("13", this::deleteFromAirports);
        methodsMenu.put("14", this::findAllAirports);
        methodsMenu.put("15", this::findAirportById);
        methodsMenu.put("16", this::findAirportByName);

        methodsMenu.put("21", this::createUser);
        methodsMenu.put("22", this::updateUser);
        methodsMenu.put("23", this::deleteFromUsers);
        methodsMenu.put("24", this::findAllUsers);
        methodsMenu.put("25", this::findUserById);
        methodsMenu.put("26", this::findUserByName);
        methodsMenu.put("27", this::findUserByEmail);

        methodsMenu.put("31", this::createPlane);
        methodsMenu.put("32", this::updatePlane);
        methodsMenu.put("33", this::deleteFromPlanes);
        methodsMenu.put("34", this::findAllPlanes);
        methodsMenu.put("35", this::findPlaneById);

        methodsMenu.put("41", this::createAirline);
        methodsMenu.put("42", this::updateAirline);
        methodsMenu.put("43", this::deleteFromAirlines);
        methodsMenu.put("44", this::findAllAirlines);
        methodsMenu.put("45", this::findAirlineById);
        methodsMenu.put("46", this::findAirlineByName);
        methodsMenu.put("47", this::findAllPlanesByAirlineId);

        methodsMenu.put("51", this::createFlight);
        methodsMenu.put("52", this::updateFlight);
        methodsMenu.put("53", this::deleteFromFlights);
        methodsMenu.put("54", this::findAllFlights);
        methodsMenu.put("55", this::findFlightById);
    }

    private void selectAllTables() {
        findAllAirports();
        findAllUsers();
        findAllPlanes();
        findAllAirlines();
        findAllFlights();
    }

    // AIRPORT
    private void createAirport() {
        System.out.println("Input 'airport name': ");
        String name = input.nextLine();
        Airport airport = new Airport(null, name);

        int count = airportController.create(airport);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateAirport() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        Airport airport = new Airport(null, name);

        int count = airportController.update(id, airport);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromAirports() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = airportController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllAirports() {
        System.out.println("\nTable: AIRPORTS");
        List<Airport> categories = airportController.findAll();
        for (Airport airport : categories) {
            System.out.println(airport);
        }
    }

    private void findAirportById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Airport> airport = airportController.findById(id);
        System.out.println(airport.orElse(nullAirport));
    }

    private void findAirportByName() {
        System.out.println("Input 'airport name': ");
        String name = input.nextLine();

        Optional<Airport> airport = airportController.findByAirportName(name);
        System.out.println(airport.orElse(nullAirport));
    }

    // AIRPORT


    // USER
    private void createUser() {
        System.out.println("Input 'user name': ");
        String name = input.nextLine();
        System.out.println("Input 'user email': ");
        String email = input.nextLine();
        System.out.println("Input 'user password': ");
        String password = input.nextLine();

        User user = new User(null, name, email, password);

        int count = userController.create(user);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateUser() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input user new name: ");
        String name = input.nextLine();

        System.out.println("Input user new email: ");
        String email = input.nextLine();

        System.out.println("Input user new password: ");
        String password = input.nextLine();

        User user = new User(null, name, email, password);

        int count = userController.update(id, user);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromUsers() {
        System.out.println("Input 'user id': ");
        Integer id = Integer.valueOf(input.nextLine());

        int count = userController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllUsers() {
        System.out.println("\nTable: USERS");
        List<User> users = userController.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void findUserById() {
        System.out.println("Input 'User id': ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<User> user = userController.findById(id);
        System.out.println(user.orElse(nullUser));
    }

    private void findUserByName() {
        System.out.println("Input 'User name': ");
        String name = input.nextLine();

        Optional<User> user = userController.findByUserName(name);
        System.out.println(user.orElse(nullUser));
    }

    private void findUserByEmail() {
        System.out.println("Input 'User email': ");
        String email = input.nextLine();

        Optional<User> user = userController.findByUserEmail(email);
        System.out.println(user.orElse(nullUser));
    }
    // USER


    // PLANE
    private void createPlane() {
        System.out.println("Input 'airlineId': ");
        Integer airlineId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'max number of passengers': ");
        Integer max_passengers = Integer.valueOf(input.nextLine());

        Plane plane = new Plane(null, airlineId, max_passengers);

        int count = planeController.create(plane);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updatePlane() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'airlineId': ");
        Integer airlineId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'max number of passengers': ");
        Integer max_passengers = Integer.valueOf(input.nextLine());

        Plane plane = new Plane(null, airlineId, max_passengers);

        int count = planeController.update(id, plane);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromPlanes() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = planeController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllPlanes() {
        System.out.println("\nTable: PLANES");
        List<Plane> planePlural = planeController.findAll();
        for (Plane plane : planePlural) {
            System.out.println(plane);
        }
    }

    private void findPlaneById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Plane> planes = planeController.findById(id);
        System.out.println(planes.orElse(nullPlane));
    }
    // PLANE


    // AIRLINE
    private void createAirline() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'foundation year': ");
        Integer foundationYear = Integer.valueOf(input.nextLine());

        Airline airline = new Airline(null, name, foundationYear);

        int count = airlineController.create(airline);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateAirline() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'foundation year': ");
        Integer foundationYear = Integer.valueOf(input.nextLine());

        Airline airline = new Airline(null, name, foundationYear);


        int count = airlineController.update(id, airline);
        System.out.printf("There are created %d rows\n", count);
    }

    private void deleteFromAirlines() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = airlineController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllAirlines() {
        System.out.println("\nTable: AIRLINES");
        List<Airline> airlines = airlineController.findAll();
        for (Airline airline : airlines) {
            System.out.println(airline);
        }
    }

    private void findAirlineById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Airline> airline = airlineController.findById(id);
        System.out.println(airline.orElse(nullAirline));
    }

    private void findAirlineByName() {
        System.out.println("Input 'Airline name': ");
        String name = input.nextLine();

        Optional<Airline> airline = airlineController.findByAirlineName(name);
        System.out.println(airline.orElse(nullAirline));
    }

    private void findAllPlanesByAirlineId() {
        System.out.println("Input 'AIRLINE id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        List<Plane> planes = airlineController.findAllPlanesBy(id);
        for (Plane plane : planes) {
            System.out.println(plane);
        }
    }
    // AIRLINE

    // FLIGHT
    private void createFlight() {
        System.out.println("Input 'src airport ID': ");
        Integer srcAirportId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'dest airport ID': ");
        Integer destAirportId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'plane ID': ");
        Integer planeId = Integer.valueOf(input.nextLine());

        Flight flight = new Flight(null, srcAirportId, destAirportId, planeId);

        int count = flightController.create(flight);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateFlight() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'src airport ID': ");
        Integer srcAirportId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'dest airport ID': ");
        Integer destAirportId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'plane ID': ");
        Integer planeId = Integer.valueOf(input.nextLine());

        Flight flight = new Flight(null, srcAirportId, destAirportId, planeId);


        int count = flightController.update(id, flight);
        System.out.printf("There are created %d rows\n", count);
    }

    private void deleteFromFlights() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = flightController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findFlightById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Flight> flight = flightController.findById(id);
        System.out.println(flight.orElse(nullFlight));
    }

    private void findAllFlights() {
        System.out.println("\nTable: FLIGHTS");
        List<Flight> subCategories = flightController.findAll();
        for (Flight flight : subCategories) {
            System.out.println(flight);
        }
    }
        // FLIGHT

        private void outputMenu () {
            System.out.println("\nMENU:");
            for (String key : menu.keySet())
                if (key.length() == 1) System.out.println(menu.get(key));
        }

        private void outputSubMenu (String fig){
            System.out.println("\nSubMENU:");
            for (String key : menu.keySet())
                if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
        }

        public void show () {
            String keyMenu;
            do {
                outputMenu();
                System.out.println("Select option:");
                keyMenu = input.nextLine();

                if (keyMenu.matches("^\\d")) {
                    outputSubMenu(keyMenu);
                    System.out.println("Select option:");
                    keyMenu = input.nextLine();
                }

                try {
                    methodsMenu.get(keyMenu).print();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            } while (!keyMenu.equalsIgnoreCase("q"));
        }
}
